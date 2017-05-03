package com.qq.weixin.open;

import com.suisrc.weixin.core.WxConsts;

/**
 * 常数
 * @author Y13
 *
 */
public interface OpenWxConsts extends WxConsts {
	
	/**
	 * 当前登录用于的open id
	 */
	String OPEN_ID = "OPEN_ID";
	
	//------------------------------key--------------------------//

	/**
	 * app id
	 */
	String KEY_APP_ID = "com.qq.weixin.open.endpoint.app-id";
	
	/**
	 * app secret
	 */
	String KEY_APP_SECRET = "com.qq.weixin.open.endpoint.app-secret";
	
	//----------------------------open----------------------------//
	
	/**
	 * 激活器key
	 */
	String NAMED = "com.qq.weixin.open.api";
	
	/**
	 * base url
	 */
	String BASE_URL = "com.qq.weixin.open.endpoint.base-url";
	
	/**
	 * 激活器中私有的线程数量
	 */
	String KEY_ACTIVATOR_THREAD_COUNT = "com.qq.weixin.open.activator.thread-count";
	
	//----------------------------open-api----------------------------//
	
	/**
	 * 激活器key
	 */
	String NAMED_API = "com.qq.weixin.open-api.api";
	
	/**
	 * base url
	 */
	String BASE_URL_API = "com.qq.weixin.open-api.endpoint.base-url";
	
	/**
	 * 激活器中私有的线程数量
	 */
	String KEY_ACTIVATOR_THREAD_COUNT_API = "com.qq.weixin.open-api.activator.thread-count";
	
	//----------------------------consts------------------------------------//
	
	/**
	 * OAuth跳转最后字符串
	 */
	String WECHAT_REDIRECT = "#wechat_redirect";

}
