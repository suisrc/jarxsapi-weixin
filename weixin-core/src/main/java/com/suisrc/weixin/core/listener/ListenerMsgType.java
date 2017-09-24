package com.suisrc.weixin.core.listener;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.suisrc.weixin.core.check.TypeAssert;

/**
 * 该注解使用说明
 * 
 * 注解不能和@Include同时使用，如果发现@Include，该注解会被无视，但是执行系统遍历的执行顺序会高于Include的方式
 * 
 * ListenerMsgType强调有限匹配型，即如果找到匹配监听器，无论该监听器是否能够正常返回结果，都不会进行再次处理
 * 
 * 而@Inlcude无法发型处理后的结果为null,则会继续匹配处理
 * 
 * ListenerMsgType匹配过程中，发现监听的消息类型和匹配的消息类型不匹配时候，不会记性强制验证，所以这个时候，系统会出现异常
 * 
 * @author Y13
 */
@Target({TYPE})
@Retention(RUNTIME)
@Inherited
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
    Class<? extends TypeAssert> assertEventKey() default TypeAssert.class;

}
