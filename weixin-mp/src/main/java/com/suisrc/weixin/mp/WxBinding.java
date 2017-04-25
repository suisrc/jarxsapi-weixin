package com.suisrc.weixin.mp;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.suisrc.weixin.core.MessageFactory;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.bean.WxJsapiSignature;
import com.suisrc.weixin.core.bean.WxJsapiSignatureStream;
import com.suisrc.weixin.core.crypto.WxCrypt;
import com.suisrc.weixin.core.listener.ListenerManager;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 跟微信服务器捆绑
 * @author Y13
 *
 */
@Path("wx")
@ApplicationScoped
public class WxBinding {
	
	/**
	 * 监听器
	 */
	private ListenerManager listenerManager;

	/**
	 * 微信配置
	 */
	@Inject @Named("com.qq.weixin.mp.api")
	private WxConfig config;
	
	/**
	 * 构造
	 */
	@PostConstruct
	public void initialized() {
		listenerManager = new ListenerManager();
	}
	
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
	public Response post(@BeanParam WxJsapiSignatureStream sign) {
		// 服务器验证
		
		
		
		// 获取消息内容
		String content = MessageFactory.getContent(sign.getInputStream()); // 获取xml内容
		BaseMessage message = MessageFactory.xmlToWxMessage(content); // 转换为bean
		Object bean = listenerManager.accept(message); // 得到处理的结构
		// 转换返回结果
		if( bean instanceof String ) {
			content = bean.toString();
		} else {
			content = MessageFactory.beanToXml(bean);
		}
		return Response.ok().entity(content).type(MediaType.TEXT_PLAIN).build();
	}

}
