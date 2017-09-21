package com.suisrc.weixin.mp.msg;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.check.TypeAssert;
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
public class MsgTypeInfo {
    
    /**
     * 消息类型, 该字段是必须字段
     */
    private String msgType;
    
    /**
     * 事件消息类型
     */
    private String event = null;
    
    /**
     * 事件消息的KEY
     */
    private String eventKey = null;
    
    /**
     * 消息类型匹配器
     */
    private TypeAssert msgTypeAssert = null;
    
    /**
     * 事件类型匹配器
     */
    private TypeAssert eventAssert = null;
    
    /**
     * 事件KEY匹配器
     */
    private TypeAssert eventKeyAssert = null;
    
    /**
     * 匹配顺序
     */
    private String priority;
    
    /**
     * 绑定的类型
     */
    private Class<? extends IMessage> targetClass;
    
    /**
     * 匹配map
     */
    private ArrayList<SimpleEntry<String, TypeAssert>> asserts;
    /**
     * 构造方法
     */
    public MsgTypeInfo(MpMsgType msgType, Class<? extends IMessage> targetClass) {
        initialized(msgType, targetClass);
        
        initAssertList();
    }

    /**
     * 构造匹配器
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initAssertList() {
        asserts = new ArrayList<>(3);
        asserts.add(new SimpleEntry(msgType, msgTypeAssert));
        asserts.add(new SimpleEntry(event, eventAssert));
        asserts.add(new SimpleEntry(eventKey, eventKeyAssert));
    }

    /**
     * 初始化
     * @param msgType
     * @param targetClass
     */
    private void initialized(MpMsgType msgType, Class<? extends IMessage> targetClass) {
        this.targetClass = targetClass;
        
        this.msgType = msgType.value();
        this.priority = msgType.priority();
        if (msgType.handler() != TypeAssert.class) {
            // TypeAssert ta = JaxrsapiUtils.newInstance(type.handler());
            // msgTypeAssert = v -> ta.apply(this.msgType, v);
            msgTypeAssert = JaxrsapiUtils.newInstance(msgType.handler());
        }
        MpEvent event = targetClass.getAnnotation(MpEvent.class);
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
        MpEventKey eventKey = targetClass.getAnnotation(MpEventKey.class);
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

    /**
     * 排序，作用优先级 @MpEvenKye > @MpEvent > @MpMsgType
     * 如果优先级属性为空，取下一级为优先级字段。
     * @return
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 
     * @param msgtype
     * @param event
     * @param eventkey
     * @return
     */
    public boolean match(String msgtype, String event, String eventkey) {
        return match2(msgtype, event, eventkey);
    }
    
    /**
     * 执行匹配
     * @param msgtype
     * @param event
     * @param eventkey
     * @return
     */
    public boolean match2(String msgtype, String event, String eventkey) {
        for (Entry<String, TypeAssert> e : asserts) {
            if (e.getKey() == null) {
                // 匹配结束，结果复合要求
                return true;
            }
            if (e.getValue() == null) {
                if (!e.getKey().equals(msgtype)) {
                    // 匹配结束，结果不复合要求
                    return false;
                }
            } else {
                if (!e.getValue().apply(e.getKey(), msgtype)) {
                    // 匹配结束，结果不复合要求
                    return false;
                }
            }
        }
        // 匹配结束，结果复合要求
        return true;
    }

    /**
     * 
     * @return
     */
    public Class<? extends IMessage> getTargetClass() {
        return targetClass;
    }


}
