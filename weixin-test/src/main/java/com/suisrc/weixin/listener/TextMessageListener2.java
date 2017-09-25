package com.suisrc.weixin.listener;

import javax.enterprise.context.ApplicationScoped;

import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.listener.ListenerInclude;
import com.suisrc.weixin.core.listener.ListenerRest;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.msg.msg.TextMessage;
import com.suisrc.weixin.mp.msg.reply.ReplyTextMessage;

/**
 * 监听文本消息
 * 
 * @author Y13
 *
 */
@ApplicationScoped
@ListenerRest(WxBindingRest.class)
@ListenerInclude(TextMessage.class)
public class TextMessageListener2 implements Listener<TextMessage> {

    /**
     * 文本消息处理
     */
    @Override
    public Object accept(TextMessage message) {
        ReplyTextMessage msg = message.reverse(new ReplyTextMessage());
        msg.setContent("TextMessageListener[@Include]->" + message.getContent());
        msg.setJson(message.isJson());
        return msg;
    }
    
    @Override
    public String priority() {
        return "M";
    }
    
    @Override
    public boolean effect() {
        return false;
    }
}
