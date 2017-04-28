package com.qq.weixin.mp;

import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
@Named(MpWxConsts.NAMED)
@ApplicationScoped
public class MpServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {
	
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
	
	/**
	 * 构造后被系统调用
	 * 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		String value =  System.getProperty(MpWxConsts.KEY_APP_ID);
		if( value != null ) { appId = value; }
		value =  System.getProperty(MpWxConsts.KEY_APP_SECRET);
		if( value != null ) { appSecret = value; }
		value =  System.getProperty(MpWxConsts.KEY_TOKEN);
		if( value != null ) { token = value; }
		value =  System.getProperty(MpWxConsts.KEY_ENCODING_AES_KEY);
		if( value != null ) { encodingAesKey = value; }
		baseUrl = System.getProperty(MpWxConsts.BASE_URL, "https://api.weixin.qq.com");
		// 构建缓存线程池
		executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(MpWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));
		
		super.initialized();
	}
	
	/**
	 * 设定access token api接口
	 * 默认使用自己AccessToken，如果需要使用统一的接口，需要单独主动调用该方法，替换系统原来的接口实现
	 * 如果需要主动修改，请使用setAdapter方法进行修改。
	 * @param atr
	 */
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject @Named(MpWxConsts.NAMED + "/AccessTokenRest")
	protected void setAccessTokenRest(AccessTokenRest atr) {
		setAdapter(AccessTokenRest.class, atr);
	}

	@Override
	protected String getTempFileName() {
		return "mp-13.obj";
	}

}
