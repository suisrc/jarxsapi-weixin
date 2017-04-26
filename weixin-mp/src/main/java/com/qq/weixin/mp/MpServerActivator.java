package com.qq.weixin.mp;

import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.qq.weixin.mp.api.UserRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.util.Utils;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.api.AccessTokenRest;

/**
 * 程序入口配置
 * @author Y13
 * https://api.weixin.qq.com/cgi-bin
 */
@Named("com.qq.weixin.mp.api")
@ApplicationScoped
public class MpServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {

	/**
	 * 构造后被系统调用
	 * 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		appId = System.getProperty(MpWxConsts.KEY_APP_ID);
		appSecret = System.getProperty(MpWxConsts.KEY_APP_SECRET);
		token = System.getProperty(MpWxConsts.KEY_TOKEN);
		encodingAesKey = System.getProperty(MpWxConsts.KEY_ENCODING_AES_KEY);
		baseUrl = System.getProperty(MpWxConsts.BASE_URL, "https://api.weixin.qq.com");
		// 构建缓存线程池
		executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(MpWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));
		
		super.initialized();
	}
	
	/**
	 * 暴露给外部远程访问接口
	 * 这里保护了系统访问的两个接口AccessToken接口
	 * 如何企业号和公众号同时使用的时候，接口可能出现问题，请注意
	 */
	public Set<Class<?>> getClasses() {
		return Utils.getRemoteApiClasses(null, false, 
				UserRest.class.getPackage().getName(),
				AccessTokenRest.class.getPackage().getName());
		
	}

	@Override
	protected String getTempFileName() {
		return "mp-13.obj";
	}

}
