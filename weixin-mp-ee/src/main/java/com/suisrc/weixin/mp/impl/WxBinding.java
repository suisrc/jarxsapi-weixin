package com.suisrc.weixin.mp.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.api.WxServerInfoRest;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.listener.ListenerManager;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.binding.AbstractWxMpBinding;
import com.suisrc.weixin.mp.msg.WxMsgTypeParser;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
@ApplicationScoped
public class WxBinding extends AbstractWxMpBinding<WxBindingRest> implements WxBindingRest {
    
    /**
     * 注入服务器配置信息
     */
    @Override
    @Inject
    @Named(MpWxConsts.NAMED)
    protected void setWxConfig(WxConfig config) {
        super.setWxConfig(config);
    }
    
    /**
     * 设定客户访问信息
     * @param request
     */
    @Override
    @Inject
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    /**
     * 设定微信服务器获取API接口
     * @param wxServerInfoRest
     */
    @Override
    @Inject
    @SuppressWarnings("cdi-ambiguous-dependency")
    public void setWxServerInfoRest(WxServerInfoRest wxServerInfoRest) {
        this.wxServerInfoRest = wxServerInfoRest;
    }
    
    /**
     * 构造
     */
    @Override
    @PostConstruct
    protected void initialized() {
        // 注入消息类型解析器
        wxMsgTypeParser = CDI.current().select(WxMsgTypeParser.class).get();
        // 初始化监听管理器
        listenerManager = new ListenerManager<>(this, WxBindingRest.class);
        // 设定构建器创建工具
        listenerManager.setListenerInstance(clazz -> CDI.current().select(clazz).get());
        
        super.initialized();
    }

}
