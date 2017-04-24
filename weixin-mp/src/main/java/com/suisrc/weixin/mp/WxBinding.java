package com.suisrc.weixin.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.WxConfig;
import com.qq.weixin.mp.bean.WxJsapiSignature;
import com.suisrc.weixin.mp.crypto.WxCrypt;

/**
 * 跟微信服务器捆绑
 * @author Y13
 *
 */
@Path("wx")
@ApplicationScoped
public class WxBinding {

	/**
	 * 微信配置
	 */
	@Inject @Named("com.qq.weixin.api")
	private WxConfig config;
	
	
	/**
	 * 后台微信请求服务器运行状态
	 */
	@GET
	@Path("info")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServerInfo() {
		return "Server is Running!";
	}
	
	/**
	 * 微信回调URL绑定
	 * @throws AesException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String get(@BeanParam WxJsapiSignature jsapiSign) {
		String signature = WxCrypt.genSHA1(config.getToken(), jsapiSign.getTimestamp(), jsapiSign.getNonce());
		if( signature.equals(jsapiSign.getSignature()) ) {
			return jsapiSign.getEchostr();
		} else {
			return "";
		}
	}
	
	/**
	 * 微信回调请求绑定
	 * @throws AesException 
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String post(@BeanParam WxJsapiSignature jsapiSign) {
		return "";
	}

}
