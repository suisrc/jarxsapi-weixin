package com.qq.weixin.open.handler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.qq.weixin.open.api.SnsOAuth2Rest;
import com.qq.weixin.open.result.OAuth2AccessToken;

/**
 * 默认的网页Access Token管理器
 * @author Y13
 *
 */
@ApplicationScoped
public class DefaultAccessTokenHandler implements AccessTokenHandler {
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject private SnsOAuth2Rest snsOAuth2Rest;

	@Override
	public OAuth2AccessToken getByOpenid(String openid) {
		return null;
	}

	@Override
	public OAuth2AccessToken getByCode(String code) {
		return snsOAuth2Rest.getAccessToken(code);
	}

}
