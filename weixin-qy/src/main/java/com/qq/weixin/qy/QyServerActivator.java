package com.qq.weixin.qy;

import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Sets;
import com.qq.weixin.qy.api.AccessTokenRest;
import com.qq.weixin.qy.api.UserRest;
import com.qq.weixin.qy.api.WxServerInfoRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.bean.WxAccessToken;

/**
 * 程序入口配置
 * @author Y13
 * https://api.weixin.qq.com/cgi-bin
 */
@Named(QyWxConsts.NAMED)
@ApplicationScoped
public class QyServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {
	
	/**
	 * 暴露给外部远程访问接口
	 * 这里保护了系统访问的两个接口AccessToken接口
	 * 如何企业号和公众号同时使用的时候，接口可能出现问题，请注意
	 */
	public Set<Class<?>> getClasses() {
//		return Utils.getRemoteApiClasses(null, false, 
//				UserRest.class.getPackage().getName(),
//				AccessTokenRest.class.getPackage().getName());
		return Sets.newHashSet(UserRest.class, AccessTokenRest.class, WxServerInfoRest.class);
		
	}
	
	/**
	 * 注入远程获取AccessTokenRest接口
	 */
	private AccessTokenRest accessTokenRest;
	
	/**
	 * 设定access token api接口
	 * 默认使用自己AccessToken，如果需要使用统一的接口，需要单独主动调用该方法，替换系统原来的接口实现
	 * 如果需要主动修改，请使用setAdapter方法进行修改。
	 * @param atr
	 */
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject @Named(QyWxConsts.NAMED + "/AccessTokenRest")
	protected void setAccessTokenRest(AccessTokenRest atr) {
		setAdapter(AccessTokenRest.class, atr);
	}

	@Override
	protected WxAccessToken getWxAccessToken() {
		return accessTokenRest.getToken(getAppId(), getAppSecret());
	}
	
	/**
	 * 构造后被系统调用
	 * 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		String value =  System.getProperty(QyWxConsts.KEY_APP_ID);
		if( value != null ) { appId = value; }
		value =  System.getProperty(QyWxConsts.KEY_APP_SECRET);
		if( value != null ) { appSecret = value; }
		value =  System.getProperty(QyWxConsts.KEY_TOKEN);
		if( value != null ) { token = value; }
		value =  System.getProperty(QyWxConsts.KEY_ENCODING_AES_KEY);
		if( value != null ) { encodingAesKey = value; }
		baseUrl = System.getProperty(QyWxConsts.BASE_URL, "https://qyapi.weixin.qq.com");
		// 构建缓存线程池
		executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(QyWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));
		
		super.initialized();
	}
	
	/**
	 * 万能接口
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(String key) {
		if (QyWxConsts.BASE_URL.equals(key)) {
			return (T) getBaseUrl();
		} else {
			return super.getAdapter(key);
		}
	}
	
	/**
	 * 主要是为了防止不支持javaee7.0标准的反向内容注入
	 */
	public <T> void setAdapter(Class<T> type, T value) {
		if( type == AccessTokenRest.class ) {
			accessTokenRest = (AccessTokenRest) value;
		} else {
			super.setAdapter(type, value);
		}
	}

	@Override
	protected String getTempFileName() {
		return "qy-13.obj";
	}

}
