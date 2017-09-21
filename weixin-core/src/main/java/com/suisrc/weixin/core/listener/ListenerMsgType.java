package com.suisrc.weixin.core.listener;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.suisrc.weixin.core.check.TypeAssert;

/**
 * 
 * @author Y13
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface ListenerMsgType {

    /**
     * 消息的类型
     */
    String value();
    
    /**
     * 消息类型断言器
     */
    Class<? extends TypeAssert> assertType() default TypeAssert.class;
    
    /**
     * 只有在为事件时候有效
     * 事件的名字
     */
    String event() default "";
    
    /**
     * 事件类型断言器
     */
    Class<? extends TypeAssert> assertEvent() default TypeAssert.class;
    
    /**
     * 事件KEY    
     * 只有在为事件时候有效
     */
    String eventKey() default "";
    
    /**
     * 事件KEY断言器
     */
    Class<? extends TypeAssert> assertEventType() default TypeAssert.class;

}
