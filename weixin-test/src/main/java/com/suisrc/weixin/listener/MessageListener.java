package com.suisrc.weixin.listener;

import javax.enterprise.context.ApplicationScoped;

import com.suisrc.weixin.core.check.TypeTrueAssert;
import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.listener.ListenerMsgType;
import com.suisrc.weixin.core.listener.ListenerRest;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.msg.reply.ReplyTextMessage;

/**
 * 监听文本消息
 * 
 * @author Y13
 *
 */
@ApplicationScoped
@ListenerRest(WxBindingRest.class)
@ListenerMsgType(value = "", assertType = TypeTrueAssert.class)
public class MessageListener implements Listener<IMessage> {

    /**
     * 所有消息处理
     */
    @SuppressWarnings("deprecation")
    @Override
    public Object accept(IMessage message) {
        ReplyTextMessage msg = new ReplyTextMessage();
        msg.setFromUserName("system");
        msg.setToUserName("user");
        msg.setCreateTime((int)(System.currentTimeMillis() / 1000));
        msg.setContent(message.getMsgType() + "->" + message.getClass().getName());
        System.out.println("---------------------------------------------------------");
        System.err.println("MessageType:" + message.getClass().getName());
        System.out.println("Content:" + message.getRawData2());
        
        return msg;
    }
    
    @Override
    public boolean effect() {
        return true;
    }
}
