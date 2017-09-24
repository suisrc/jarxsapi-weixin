package com.suisrc.weixin.core.listener;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.check.TypeAssert;
import com.suisrc.weixin.core.msg.AbstractMsgTypeInfo;

/**
 * 使用@LinstenerMsgType注解标记的监听器内容
 * 
 * @author Y13
 *
 */
@SuppressWarnings("rawtypes")
class MsgTypeInfo extends AbstractMsgTypeInfo<MsgTypeInfo, Listener> {
    
    /**
     * 构造方法
     */
    public MsgTypeInfo(Listener target) {
        super(target);
    }

    /**
     * 初始化
     * 
     * 其中MpMsgType注解是必须有的内容
     * 
     * @param msgType
     * @param targetClass
     */
    @Override
    protected void initialize(Listener target) {
        this.target = target;
        this.priority = target.priority();
        
        ListenerMsgType msgType = target.getClass().getAnnotation(ListenerMsgType.class);
        this.msgType = msgType.value();
        if (msgType.assertType() != TypeAssert.class) {
            msgTypeAssert = JaxrsapiUtils.newInstance(msgType.assertType());
        }
        if (msgType.event().isEmpty() && msgType.assertEvent() == TypeAssert.class) {
            // 结束构建
            return;
        }
        this.event = msgType.event();
        if (msgType.assertEvent() != TypeAssert.class) {
            eventAssert = JaxrsapiUtils.newInstance(msgType.assertEvent());
        }
        if (msgType.eventKey().isEmpty() && msgType.assertEventKey() == TypeAssert.class) {
            // 结束构建
            return;
        }
        this.eventKey = msgType.eventKey();
        if (msgType.assertEventKey() != TypeAssert.class) {
            eventKeyAssert = JaxrsapiUtils.newInstance(msgType.assertEventKey());
        }
    }

}
