package com.qq.weixin.open.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxAccessToken;

/**
 * 微信网页授权的Access token
 * 
 * { "access_token":"ACCESS_TOKEN",    
 *  "expires_in":7200,    
 *  "refresh_token":"REFRESH_TOKEN",    
 *  "openid":"OPENID",    
 *  "scope":"SCOPE" } 
 * @author Y13
 *
 */
public class OAuth2AccessToken extends WxAccessToken {
	private static final long serialVersionUID = 5125197622619178049L;

	/**
	 * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 */
//	@JsonProperty("access_token")
//	private String accessToken;

	/**
	 * access_token接口调用凭证超时时间，单位（秒）
	 */
//	@JsonProperty("expires_in")
//	private long expiresIn;

	/**
	 * 用户刷新access_token
	 */
	@JsonProperty("refresh_token")
	private String refreshToken;

	/**
	 * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
	 */
	@JsonProperty("openid")
	private String openid;

	/**
	 * 用户授权的作用域，使用逗号（,）分隔
	 */
	@JsonProperty("scope")
	private String scope;

	public String getAccessToken() {
		return super.getAccessToken();
	}

	public void setAccessToken(String accessToken) {
		super.setAccessToken(accessToken);
	}

	public long getExpiresIn() {
		return super.getExpiresIn();
	}

	public void setExpiresIn(long expiresIn) {
		super.setExpiresIn(expiresIn);
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
}
