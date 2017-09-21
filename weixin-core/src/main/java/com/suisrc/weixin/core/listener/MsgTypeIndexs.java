package com.suisrc.weixin.core.listener;

import java.util.List;

import com.suisrc.weixin.core.msg.IMessage;

/**
 * 监听器的创建器
 * @author Y13
 *
 */
@FunctionalInterface
public interface MsgTypeIndexs {

    /**
     * 获取监听的消息类型
     * @param clazz
     * @return
     */
    List<Class<? extends IMessage>> searchMsgType(String type, String event, String eventKey);
    
}
