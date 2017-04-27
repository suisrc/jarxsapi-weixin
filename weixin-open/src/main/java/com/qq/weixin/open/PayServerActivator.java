package com.qq.weixin.open;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.ClientBuilder;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;

/**
 * 程序入口配置
 * 
 * @author Y13 https://api.mch.weixin.qq.com
 */
@Named(OpenWxConsts.NAMED)
@ApplicationScoped
public class PayServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {

	/**
	 * 暴露给外部远程访问接口 这里保护了系统访问的两个接口AccessToken接口 如何企业号和公众号同时使用的时候，接口可能出现问题，请注意
	 */
	public Set<Class<?>> getClasses() {
//		return Sets.newHashSet(MmpaymkttransfersRest.class);
		return Collections.emptySet();

	}

	/**
	 * 构造后被系统调用 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		appId = System.getProperty(OpenWxConsts.KEY_APP_ID);
		// appSecret = System.getProperty(OpenWxConsts.KEY_APP_SECRET);
		// token = System.getProperty(OpenWxConsts.KEY_TOKEN);
		// encodingAesKey = System.getProperty(OpenWxConsts.KEY_ENCODING_AES_KEY);
		baseUrl = System.getProperty(OpenWxConsts.BASE_URL, "https://api.mch.weixin.qq.com");
		// 构建缓存线程池
		executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(OpenWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));

		super.initialized();
	}
	
	//------------------------access token---------------------------//
	/**
	 * 支付平台不需要access token
	 */
	@Override
	protected void initAccessToken() {
	}
	
	@Override
	public String getAccessToken() {
		// do nothing
		return null;
	}
	
	@Override
	public void clearAccessToken() {
		// do nothing
	}
	//------------------------access token---------------------------//

	/**
	 * 初始化远程访问的客户端
	 */
	protected ClientBuilder getClientBuilder() {
		ClientBuilder builder = super.getClientBuilder();// 配置网络通信内容
		return builder;
	}
	
}
