package com.qq.weixin.pay;

import com.suisrc.weixin.core.WxConsts;

/**
 * 常数
 * @author Y13
 *
 */
public interface PayWxConsts extends WxConsts {
	
	/**
	 * 商铺ID
	 */
	String MCH_ID = "MCH_ID";
	
	/**
	 * 商铺密钥
	 */
	String MCH_KEY = "MCH_KEY";
	
	/**
	 * 商铺名称
	 */
	String SEND_NAME = "SEND_NAME";
	
	/**
	 * 客户端IP
	 */
	String CLIENT_IP = "CLIENT_IP";
	
	/**
	 * 订单编号
	 */
	String AUTO_MCH_BILLNO = "AUTO_MCH_BILLNO";
	
	/**
	 * 获取随机数
	 */
	String AUTO_RANDOM_STR = "AUTO_RANDOM_STR";

//---------------------------------KEY---------------------------------//
	
	/**
	 * 激活器key
	 */
	String NAMED = "com.qq.weixin.pay.api";

	/**
	 * 应用主键
	 */
	String KEY_APP_ID = "com.qq.weixin.pay.endpoint.app-id";
	
	/**
	 * base url
	 */
	String BASE_URL = "com.qq.weixin.pay.endpoint.base-url";

	/**
	 * pkcs12格式证书的位置
	 */
	String KEY_API_CLIENT_CERT_P12_PATH = "com.qq.weixin.pay.endpoint.client-cert-p12";

	/**
	 * 商铺ID
	 */
	String KEY_MCH_ID = "com.qq.weixin.pay.endpoint.mch-id";
	
	/**
	 * 商铺密钥
	 */
	String KEY_MCH_KEY = "com.qq.weixin.pay.endpoint.mch-key";

	/**
	 * 商铺名称
	 */
	String KEY_SEND_NAME = "com.qq.weixin.pay.endpoint.send-name";

	/**
	 * 客户端ip
	 */
	String KEY_CLIENT_IP = "com.qq.weixin.pay.endpoint.client-ip";
	
	/**
	 * 激活器中私有的线程数量
	 */
	String KEY_ACTIVATOR_THREAD_COUNT = "com.qq.weixin.pay.activator.thread-count";


}
