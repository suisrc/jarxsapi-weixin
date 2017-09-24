package com.suisrc.weixin.listener;

import javax.enterprise.context.ApplicationScoped;

import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.listener.ListenerInclude;
import com.suisrc.weixin.core.listener.ListenerMsgType;
import com.suisrc.weixin.core.listener.ListenerRest;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.msg.TextMessage;
import com.suisrc.weixin.mp.msg.msg.VoiceMessage;

/**
 * 监听文本消息
 * 
 * @author Y13
 *
 */
@ApplicationScoped
@ListenerRest(WxBindingRest.class)
@ListenerInclude({TextMessage.class, VoiceMessage.class})
@ListenerMsgType("event")
public class BaseMessageListener implements Listener<BaseMessage> {

    /**
     * 文本消息处理
     */
    @Override
    public Object accept(BaseMessage message) {
        return "BaseMessageListener";
    }

    /**
     * 比正常加载顺序多一个加载等级
     */
    @Override
    public String priority() {
        return Listener.super.priority() + "0";
    }
}
