package com.suisrc.weixin.core;

/**
 * 常数
 * @author Y13
 *
 */
public interface WxConsts {
	
	/**
	 * 测试表示
	 */
	boolean DEBUG = true;
	/**
	 * 微信公众号的appid
	 */
	String APP_ID = "APP_ID";
	
	/**
	 * 微信公众号的app corpSecret
	 */
	String APP_SECRET = "APP_SECRET";
	
	/**
	 * 微信公众号的 access token
	 */
	String ACCESS_TOKEN = "ACCESS_TOKEN";
	
	/**
	 * 微信公众号的 token
	 */
	String TOKEN = "TOKEN";
	
	/**
	 * 消息加解密密钥
	 */
	String ENCODING_AES_KEY = "ENCODING_AES_KEY";
	
	/**
	 * 
	 */
	String GRANT_TYPE = "client_credential";

//---------------------------------KEY---------------------------------//
	
	/**
	 * access token 提前更新时间，该字段范围为0~7200
	 */
	String KEY_ADVANCE_TIME = "com.qq.weixin.access_token.advance_time";

}
