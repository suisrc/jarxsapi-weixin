package com.suisrc.weixin.core;

import com.suisrc.jaxrsapi.core.Consts;

/**
 * 常数
 * @author Y13
 *
 */
public interface WxConsts extends Consts {
	
	/**
	 * 测试表示
	 */
	boolean DEBUG = Boolean.getBoolean("debug");
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
	 * 加密方式
	 */
	String ENCRYPT_TYPE_AES = "aes";

	/**
	 * 当前登录用户open_id保存在cookie中的key
	 */
	String COOKIE_OPEN_ID = "openid";

//---------------------------------KEY---------------------------------//
	
	/**
	 * access token 提前更新时间，该字段范围为0~7200
	 */
	String KEY_ADVANCE_TIME = "com.qq.weixin.access-token.advance-time";

	/**
	 * app id key
	 */
	String KEY_CORE_APP_ID = "com.qq.weixin.core.endpoint.app-id";

	/**
	 * app secret
	 */
	String KEY_CORE_APP_SECRET = "com.qq.weixin.core.endpoint.app-secret";

	/**
	 * token
	 */
	String KEY_CORE_TOKEN = "com.qq.weixin.core.endpoint.token";

	/**
	 * encoding aes key
	 */
	String KEY_CORE_ENCODING_AES_KEY = "com.qq.weixin.core.endpoint.encoding-aes-key";
	
}
