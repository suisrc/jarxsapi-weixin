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

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.weixin.core.WxMsgFactory;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.WxConsts;
import com.suisrc.weixin.core.bean.WxEncryptSignature;
import com.suisrc.weixin.core.bean.WxJsapiSignature;
import com.suisrc.weixin.core.crypto.WxCrypto;
import com.suisrc.weixin.core.listener.ListenerManager;
import com.suisrc.weixin.core.msg.BaseMessage;
import com.suisrc.weixin.core.msg.EncryptMessage;

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
	 * 是否使用信息加密
	 */
	private boolean isEncrypt = true;

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
		// 初始化监听管理器
		listenerManager = new ListenerManager(this);
		listenerManager.addClassesBySysProp(MpWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_CLASSES);
		listenerManager.addPackagesBySysProp(MpWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_PACKAGES);
		// 消息加密
		isEncrypt = Boolean.valueOf(System.getProperty(MpWxConsts.KEY_WEIXIN_CALLBACK_MESSAGE_ENCRYPT, "true")).booleanValue();
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
	public String doGet(@BeanParam WxJsapiSignature sign) {
		if( config.getToken() == null 
			|| sign.getTimestamp() == null 
			|| sign.getNonce() == null 
			|| sign.getSignature() == null ) {
			return "非法请求";
		}
		// 进行验证
		String signature = WxCrypto.genSHA1(config.getToken(), sign.getTimestamp(), sign.getNonce());
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
	public Response doPost(@BeanParam WxEncryptSignature sign, String data) {
		//--------------------------------服务器验证------------------------------------//
		if( isEncrypt && sign.isValid() ) {
			// 没有签名信息
			return Response.ok().entity("非法请求").type(MediaType.TEXT_PLAIN).build();
		}
		if( sign.getSignature() != null ) {
			// 服务器验证
			String signature = WxCrypto.genSHA1(config.getToken(), sign.getTimestamp(), sign.getNonce());
			if( !signature.equals(sign.getSignature()) ) {
				// 消息签名不正确，说明不是公众平台发过来的消息
				return Response.ok().entity("非法请求").type(MediaType.TEXT_PLAIN).build();
			}
			if( sign.getEchostr() != null && !sign.getEchostr().isEmpty() ) {
				// 仅仅用来验证的请求，回显echostr
				return Response.ok().entity(sign.getEchostr()).type(MediaType.TEXT_PLAIN).build();
			}
		}
		//--------------------------------消息签名验证------------------------------------//
		// 处理消息内容
		WxCrypto wxCrypt = null;
		String xmlContent;
		if( WxConsts.ENCRYPT_TYPE_AES.equals(sign.getEncryptType()) ) {
			// 使用AES加密
			wxCrypt = new WxCrypto(config.getToken(), config.getEncodingAesKey(), config.getAppId());
			// 解析网络数据
			EncryptMessage encryptMsg= WxMsgFactory.xmlToBean(data, EncryptMessage.class);
			// 验证数据签名
			String signature = WxCrypto.genSHA1(wxCrypt.getToken(), sign.getTimestamp(), sign.getNonce(), encryptMsg.getEncrypt());
			if( !signature.equals(sign.getMsgSignature()) ) {
				return Response.ok().entity("数据签名异常").type(MediaType.TEXT_PLAIN).build();
			}
			xmlContent = wxCrypt.decrypt(encryptMsg.getEncrypt());
		} else {
			// raw 明文传输
			xmlContent = data;
		}
		//--------------------------------消息内容处理------------------------------------//
		// 解析消息内容
		BaseMessage message = WxMsgFactory.xmlToWxMessage(xmlContent); // 转换为bean
		if( message == null ) {
			return Response.ok().entity("消息内容无法解析").type(MediaType.TEXT_PLAIN).build();
		}
		// 通过监听器处理消息内容
		Object bean = listenerManager.accept(message); // 得到处理的结构
		if( bean == null ) {
			return Response.ok().entity("消息内容无法应答").type(MediaType.TEXT_PLAIN).build();
		}
		//--------------------------------响应结果解析------------------------------------//
		// 分析结果
		String reault = bean instanceof String ? bean.toString() : WxMsgFactory.beanToXml(bean);
		if( wxCrypt != null ) {
			// 消息内容需要加密返回
			String encryText = wxCrypt.encrypt(reault);
			// 构建返回对象
			EncryptMessage encryptMsg = new EncryptMessage();
			encryptMsg.setEncrypt(encryText);
			encryptMsg.setNonce(WxCrypto.genRandomStr());
			encryptMsg.setTimeStamp(System.currentTimeMillis());
			// 生成签名
			String signature = WxCrypto.genSHA1(wxCrypt.getToken(), encryptMsg.getTimeStamp(), encryptMsg.getNonce(), encryText);
			encryptMsg.setMsgSignature(signature);
			// 生成xml内容
			reault = WxMsgFactory.beanToXml(encryptMsg);
		}
		//--------------------------------返回处理的结果------------------------------------//
		return Response.ok().entity(reault).type(MediaType.TEXT_PLAIN).build();
	}

}
