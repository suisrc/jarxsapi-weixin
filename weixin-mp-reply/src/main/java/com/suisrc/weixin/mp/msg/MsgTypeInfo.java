package com.suisrc.weixin.mp.msg;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.check.TypeAssert;
import com.suisrc.weixin.core.msg.AbstractMsgTypeInfo;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.annotation.MpEventKey;
import com.suisrc.weixin.mp.annotation.MpMsgType;

/**
 * 事件中类型的描述信息
 * 
 * 用于存储描述了响应时间的描述信息 @MpEvent, @MpEvenKye @MpMsgType
 * 
 * @author Y13
 *
 */
public class MsgTypeInfo extends AbstractMsgTypeInfo<MsgTypeInfo, Class<? extends IMessage>> {
    
    /**
     * 构造方法
     */
    public MsgTypeInfo(Class<? extends IMessage> target) {
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
    protected void initialize(Class<? extends IMessage> target) {
        this.target = target;
        
        MpMsgType msgType = target.getAnnotation(MpMsgType.class);
        this.msgType = msgType.value();
        this.priority = msgType.priority();
        if (msgType.handler() != TypeAssert.class) {
            // TypeAssert ta = JaxrsapiUtils.newInstance(type.handler());
            // msgTypeAssert = v -> ta.apply(this.msgType, v);
            msgTypeAssert = JaxrsapiUtils.newInstance(msgType.handler());
        }
        MpEvent event = target.getAnnotation(MpEvent.class);
        if (event == null || !event.effect()) {
            // 结束构建
            return;
        }
        this.event = event.value();
        if (!event.priority().isEmpty()) {
            this.priority = event.priority();
        }
        if (event.handler() != TypeAssert.class) {
            eventAssert = JaxrsapiUtils.newInstance(event.handler());
        }
        MpEventKey eventKey = target.getAnnotation(MpEventKey.class);
        if (eventKey == null || !eventKey.effect()) {
            // 结束构建
            return;
        }
        this.eventKey = eventKey.value();
        if (!eventKey.priority().isEmpty()) {
            this.priority = eventKey.priority();
        }
        if (eventKey.handler() != TypeAssert.class) {
            eventKeyAssert = JaxrsapiUtils.newInstance(eventKey.handler());
        }
    }

}
