package com.qq.weixin.mp;

/**
 * 常数
 * @author Y13
 *
 */
public interface MpWxConsts extends com.suisrc.weixin.core.WxConsts {

//---------------------------------KEY---------------------------------//
	/**
	 * app id key
	 */
	String KEY_APP_ID = "com.qq.weixin.mp.endpoint.app_id";

	/**
	 * app secret
	 */
	String KEY_APP_SECRET = "com.qq.weixin.mp.endpoint.app_secret";

	/**
	 * token
	 */
	String KEY_TOKEN = "com.qq.weixin.mp.endpoint.token";

	/**
	 * encoding aes key
	 */
	String KEY_ENCODING_AES_KEY = "com.qq.weixin.mp.endpoint.encoding_aes_key";

	/**
	 * base url
	 */
	String BASE_URL = "com.qq.weixin.mp.endpoint.base_url";
	
	/**
	 * 激活器中私有的线程数量
	 */
	String KEY_ACTIVATOR_THREAD_COUNT = "com.qq.weixin.mp.activator.thread_count";

}
