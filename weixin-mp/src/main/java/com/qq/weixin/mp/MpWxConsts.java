package com.qq.weixin.mp;

import com.suisrc.weixin.core.WxConsts;

/**
 * 常数
 * @author Y13
 *
 */
public interface MpWxConsts extends WxConsts {

//---------------------------------KEY---------------------------------//
	
	/**
	 * 激活器key
	 */
	String NAMED = "com.qq.weixin.mp.api";
	
	/**
	 * app id key
	 */
	String KEY_APP_ID = "com.qq.weixin.mp.endpoint.app-id";

	/**
	 * app secret
	 */
	String KEY_APP_SECRET = "com.qq.weixin.mp.endpoint.app-secret";

	/**
	 * token
	 */
	String KEY_TOKEN = "com.qq.weixin.mp.endpoint.token";

	/**
	 * encoding aes key
	 */
	String KEY_ENCODING_AES_KEY = "com.qq.weixin.mp.endpoint.encoding-aes-key";

	/**
	 * base url
	 */
	String BASE_URL = "com.qq.weixin.mp.endpoint.base-url";
	
	/**
	 * 激活器中私有的线程数量
	 */
	String KEY_ACTIVATOR_THREAD_COUNT = "com.qq.weixin.mp.activator.thread-count";

	/**
	 * 回调监听器, 使用“,”分割, 指定单独的类型
	 */
	String KEY_WEIXIN_CALLBACK_LISTENER_CLASSES = "com.qq.weixin.mp.callback.listeners.classes";

	/**
	 * 回调监听器, 使用“,”分割, 指定扫描的范围
	 */
	String KEY_WEIXIN_CALLBACK_LISTENER_PACKAGES = "com.qq.weixin.mp.callback.listeners.packages";

	/**
	 * 消息是否加密
	 */
	String KEY_WEIXIN_CALLBACK_MESSAGE_ENCRYPT = "com.qq.weixin.mp.callback.message.encrypt";

}
