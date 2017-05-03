package com.qq.weixin.open.handler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.qq.weixin.open.api.SnsOAuth2Rest;
import com.qq.weixin.open.result.OAuth2AccessToken;

/**
 * 默认的网页Access Token管理器
 * 
 * 使用内存进行管理，定期清理无效数据
 * 
 * 打算使用h2数据库进行管理
 * @author Y13
 *
 */
@ApplicationScoped
public class DefaultAccessTokenHandler implements AccessTokenHandler {
	
	/**
	 * token 缓存
	 */
	private Map<String, OAuth2AccessToken> tokens;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject protected SnsOAuth2Rest snsOAuth2Rest;
	
	/**
	 * 初始化
	 */
	@PostConstruct
	protected void initialized() {
		tokens = new ConcurrentHashMap<>();
	}
	
	/**
	 * 通过openid 获取token
	 */
	@Override
	public OAuth2AccessToken getByOpenid(String openid) {
		OAuth2AccessToken token = find(openid);
		if( token == null ) { return null; }
		return check(token);
	}

	/**
	 * 通过code 获取token
	 */
	@Override
	public OAuth2AccessToken getByCode(String code) {
		OAuth2AccessToken token = snsOAuth2Rest.getAccessToken(code);
		if( token.getOpenid() != null ) { update(token); }
		return token;
	}

	/**
	 * 判断token是否过期，如果过期，刷新，如果刷新后还是过期，就直接删除
	 * @param token
	 * @return
	 */
	protected OAuth2AccessToken check(OAuth2AccessToken token) {
		switch (token.checkValid()) {
		case NONE: // 无效
			return null;
		case EXPIRED: // 过期
			return refreshToken(token);
		case VALID: // 有效
		case WILL_EXPIRE: // 将要过期
			return token;
		default:
			return null;
		}
	}
	
	/**
	 * 刷新token
	 * @param token
	 * @return
	 */
	protected OAuth2AccessToken refreshToken(OAuth2AccessToken token) {
		if( token.getOpenid() == null ) { return null; } // 正常系统不会有该异常，因为openid为数据主键
		if( token.getRefreshToken() == null || token.getAccessToken() == null ) {
			remove(token); // 删除系统token
			return null; // 验证有效性失败
		}
		synchronized (token) { // 同一个token进行同步
			OAuth2AccessToken newToken = find(token.getOpenid());
			if( newToken == null ) { return null; }
			if( !token.getAccessToken().equals(newToken.getAccessToken()) ) { 
				return newToken; // 已经被刷新过，直接跳过
			}
			// 远程获取token
			newToken = snsOAuth2Rest.refreshToken(token.getRefreshToken());
			if( newToken.getErrcode() != null ) { 
				remove(token); // 远程获取异常
			} else {
				update(newToken); // 更新
			}
			return newToken;
		}
	}
	
	/**
	 * 查找
	 * @param openid
	 * @return
	 */
	protected OAuth2AccessToken find(String openid) {
		return tokens.get(openid);
	}
	
	/**
	 * 保存
	 * @param token
	 * @return
	 */
	protected OAuth2AccessToken save(OAuth2AccessToken token) {
		tokens.put(token.getOpenid(), token);
		return token;
	}
	
	/**
	 * 更新
	 * @param token
	 * @return
	 */
	protected OAuth2AccessToken update(OAuth2AccessToken token) {
		tokens.put(token.getOpenid(), token);
		return token;
	}
	
	/**
	 * 删除
	 * @param token
	 * @return
	 */
	protected OAuth2AccessToken remove(OAuth2AccessToken token) {
		return tokens.remove(token.getOpenid());
	}

}
