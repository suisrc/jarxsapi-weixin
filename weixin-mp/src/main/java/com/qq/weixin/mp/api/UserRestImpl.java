package com.qq.weixin.mp.api;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.WebTarget;

import com.qq.weixin.mp.param.UserInfoParam;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.proxy.ProxyBuilder;

/**
 * 测试
 * @author Y13
 */
public class UserRestImpl{
	
	/**
	 * 激活器
	 */
	@Inject @Named("com.qq.weixin.api")
	private ApiActivator activator;
	
	/**
	 * 代理
	 */
	private UserRest proxy;

	public ApiActivator getActivator() {
		return activator;
	}

	public void setActivator(ApiActivator activator) {
		this.activator = activator;
	}

	@PostConstruct
	public void initialized() {
		proxy = ProxyBuilder.builder(UserRest.class, activator.getAdapter(WebTarget.class)).build();
	}

	public String getUserInfo(UserInfoParam userInfo) {
		if( userInfo.getAccessToken() == null ) { userInfo.setAccessToken(activator.getAdapter("ACCESS_TOKEN")); }
		return proxy.getUserInfo(userInfo);
	}

	public String getUserGet(String accessToken, String openid) {
		if( accessToken == null ) { accessToken = activator.getAdapter("ACCESS_TOKEN"); }
//		return proxy.getUserGet(accessToken, openid);
		return null;
	}

}
