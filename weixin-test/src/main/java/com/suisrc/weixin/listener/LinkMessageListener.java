package com.suisrc.weixin.listener;

import javax.enterprise.context.ApplicationScoped;

import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.listener.ListenerRest;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.msg.msg.LinkMessage;

/**
 * 监听文本消息
 * 
 * @author Y13
 *
 */
@ApplicationScoped
@ListenerRest(WxBindingRest.class)
public class LinkMessageListener implements Listener<LinkMessage> {

    /**
     * 链接消息处理
     */
    @Override
    public Object accept(LinkMessage message) {
        return message.getUrl();
    }

}
