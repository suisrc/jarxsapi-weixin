package com.qq.weixin.open;

import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.google.common.collect.Sets;
import com.qq.weixin.open.api.SnsOAuth2Rest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;

/**
 * 程序入口配置
 * https://api.weixin.qq.com
 * @author Y13 
 */
@Named(OpenWxConsts.NAMED_API)
@ApplicationScoped
public class OpenApiServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {

	/**
	 * 暴露给外部远程访问接口 这里保护了系统访问的两个接口AccessToken接口 如何企业号和公众号同时使用的时候，接口可能出现问题，请注意
	 */
	public Set<Class<?>> getClasses() {
		return Sets.newHashSet(SnsOAuth2Rest.class);

	}

	/**
	 * 构造后被系统调用 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		appId = System.getProperty(OpenWxConsts.KEY_APP_ID);
		appSecret = System.getProperty(OpenWxConsts.KEY_APP_SECRET);
		baseUrl = System.getProperty(OpenWxConsts.BASE_URL, "https://api.weixin.qq.com");
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
}
