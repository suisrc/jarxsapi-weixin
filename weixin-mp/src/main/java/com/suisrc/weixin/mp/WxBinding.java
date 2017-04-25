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

import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.bean.WxJsapiSignature;
import com.suisrc.weixin.core.bean.WxJsapiSignatureStream;
import com.suisrc.weixin.core.crypto.WxCrypt;

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
	@Inject @Named("com.qq.weixin.mp.api")
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
	public String get(@BeanParam WxJsapiSignature sign) {
		String signature = WxCrypt.genSHA1(config.getToken(), sign.getTimestamp(), sign.getNonce());
		if( signature.equals(sign.getSignature()) ) {
			return sign.getEchostr();
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
	public String post(@BeanParam WxJsapiSignatureStream sign) {
		return "";
	}

}
