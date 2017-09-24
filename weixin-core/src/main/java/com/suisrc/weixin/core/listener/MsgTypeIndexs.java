package com.suisrc.weixin.core.listener;

import java.util.Collection;

import com.suisrc.weixin.core.msg.AbstractMsgTypeIndexs;

/**
 * 使用@LinstenerMsgType注解标记的监听器索引
 * 
 * @author Y13
 *
 */
@SuppressWarnings("rawtypes")
class MsgTypeIndexs extends AbstractMsgTypeIndexs<MsgTypeInfo, Listener> {
    
    /**
     * 索引的构造方法
     * @param packages
     */
    public MsgTypeIndexs(Collection<Listener> listeners) {
        super(MsgTypeIndexs::newMsgTypeInfo, listeners);
    }
    
    /**
     * 根据类型创建消息描述对象
     * @param clazz
     * @return
     */
    private static MsgTypeInfo newMsgTypeInfo(Listener listener) {
        if (!listener.effect()) {
            return null;
        }
        ListenerMsgType msgtype = listener.getClass().getAnnotation(ListenerMsgType.class);
        if (msgtype == null) {
            return null; // 不存在解析类型或者失效，跳过
        }
        return new MsgTypeInfo(listener);
    }

}
