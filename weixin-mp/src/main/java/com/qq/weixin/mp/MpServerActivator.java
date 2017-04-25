package com.qq.weixin.mp;

import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.google.common.collect.Sets;
import com.qq.weixin.mp.api.UserRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.api.AccessTokenRest;
import com.suisrc.weixin.core.api.WxServerInfoRest;

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
		baseUrl = System.getProperty(MpWxConsts.BASE_URL, "https://api.weixin.qq.com/cgi-bin");
		// 构建缓存线程池
		executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(MpWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));
		
		super.initialized();
	}
	
	/**
	 * 暴露给外部远程访问接口
	 */
	public Set<Class<?>> getClasses() {
//		return Utils.getRestApiClasses(UserRest.class.getPackage().getName(), null, false);
		return Sets.newHashSet(
				WxServerInfoRest.class,
				AccessTokenRest.class,
				UserRest.class
				);
		
	}

	@Override
	protected String getTempFileName() {
		return "mp-13.obj";
	}

}
