package com.qq.weixin.mp;

public interface WxConfig {

	/**
	 * 微信公众号的appid
	 */
	String getAppId();

	/**
	 * 微信公众号的app corpSecret
	 */
	String getAppSecret();

	/**
	 * 微信公众号的token
	 */
	String getToken();

	/**
	 * 消息加解密密钥
	 */
	String getEncodingAesKey();

	/**
	 * 获取access token
	 */
	String getAccessToken();
	
	/**
	 * 设定access token
	 * @param accessToken
	 */
	void setAccessToken(String accessToken);
}
