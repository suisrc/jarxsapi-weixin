package com.suisrc.weixin.mp.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.weixin.core.AbstractWxBinding;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.listener.ListenerManager;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.msg.WxMsgFactory;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
@ApplicationScoped
public class WxBinding extends AbstractWxBinding<WxBindingRest> implements WxBindingRest {

    /**
     * 构造
     */
    @PostConstruct
    @Override
    protected void initialized() {
        // 初始化监听管理器
        listenerManager = new ListenerManager<>(this, WxBindingRest.class);
        listenerManager.addClassesBySysProp(MpWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_CLASSES);
        listenerManager.addPackagesBySysProp(MpWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_PACKAGES);
        // 消息加密
        isEncrypt = Boolean.valueOf(System.getProperty(MpWxConsts.KEY_WEIXIN_CALLBACK_MESSAGE_ENCRYPT, "true")).booleanValue();
    }

    @Inject
    @Named(MpWxConsts.NAMED)
    @Override
    protected void setWxConfig(WxConfig config) {
        super.setWxConfig(config);
    }

    @Override
    protected IMessage xml2Message(String xml) {
        return WxMsgFactory.xmlToWxMessage(xml);
    }

    /**
     * 后台微信请求服务器运行状态
     */
    @Override
    public String getServerInfo() {
        return "Mp " + super.getServerInfo();
    }

}
