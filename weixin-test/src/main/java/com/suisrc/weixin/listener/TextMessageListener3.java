package com.suisrc.weixin.listener;

import javax.enterprise.context.ApplicationScoped;

import com.suisrc.weixin.core.listener.Listener;
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
public class TextMessageListener3 implements Listener<TextMessage> {

    /**
     * 文本消息处理
     */
    @Override
    public Object accept(TextMessage message) {
        ReplyTextMessage msg = message.reverse(new ReplyTextMessage());
        msg.setContent("TextMessageListener[default]->" + message.getContent());
        msg.setJson(message.isJson());
        return msg;
    }
    
    @Override
    public boolean effect() {
        return false;
    }
}
