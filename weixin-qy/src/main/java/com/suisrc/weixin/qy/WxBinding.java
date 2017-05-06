package com.suisrc.weixin.qy;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.qy.QyWxConsts;
import com.suisrc.weixin.core.AbstractWxBinding;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.listener.ListenerManager;

/**
 * 跟微信服务器捆绑
 * @author Y13
 *
 */
@Path("wx/qy")
@ApplicationScoped
public class WxBinding extends AbstractWxBinding {
	
	/**
	 * 构造
	 */
	@PostConstruct
	protected void initialized() {
		// 初始化监听管理器
		listenerManager = new ListenerManager(this);
		listenerManager.addClassesBySysProp(QyWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_CLASSES);
		listenerManager.addPackagesBySysProp(QyWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_PACKAGES);
		// 消息加密
		isEncrypt = Boolean.valueOf(System.getProperty(QyWxConsts.KEY_WEIXIN_CALLBACK_MESSAGE_ENCRYPT, "true")).booleanValue();
	}

	
	@Override
	@Inject @Named(QyWxConsts.NAMED)
	protected void setWxConfig(WxConfig config) {
		super.setWxConfig(config);
	}
	
	/**
	 * 后台微信请求服务器运行状态
	 */
	@GET
	@Path("info")
	@Produces(MediaType.TEXT_PLAIN)
	public String getServerInfo() {
		return "qy server is running!";
	}
	
}
