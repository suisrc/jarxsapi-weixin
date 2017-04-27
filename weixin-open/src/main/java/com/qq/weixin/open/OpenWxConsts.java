package com.qq.weixin.open;

import com.suisrc.weixin.core.WxConsts;

/**
 * 常数
 * @author Y13
 *
 */
public interface OpenWxConsts extends WxConsts {
	
	/**
	 * 激活器key
	 */
	String NAMED = "com.qq.weixin.open.api";


	String KEY_APP_ID = "com.qq.weixin.open.endpoint.app-id";
	
	/**
	 * base url
	 */
	String BASE_URL = "com.qq.weixin.open.endpoint.base-url";

	/**
	 * pkcs12格式证书的位置
	 */
	String KEY_API_CLIENT_CERT_P12_PATH = "com.qq.weixin.open.endpoint.client-cert-p12";
	
	/**
	 * 激活器中私有的线程数量
	 */
	String KEY_ACTIVATOR_THREAD_COUNT = "com.qq.weixin.open.activator.thread-count";

}
