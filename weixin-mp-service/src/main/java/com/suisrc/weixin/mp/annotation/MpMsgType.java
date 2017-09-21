package com.suisrc.weixin.mp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.suisrc.weixin.core.check.TypeAssert;

/**
 * 
 * @author Y13
 */
@Target({TYPE})
@Retention(RUNTIME)
@Inherited
public @interface MpMsgType {

    /**
     * 事件的类型
     */
    String value();
    
    /**
     * 事件的名字
     */
    String name() default "unefined";
    
    /**
     * 配置状态
     * 可以禁用某些内容的解析
     */
    boolean effect() default true;
    
    /**
     * 匹配的顺序
     * 该字段只有在handler字段不为TypeAssert.class时候有效
     * 如果排序有效，其作用于默认排序之后，即默认认为是"n"
     */
    String priority() default DEFAULT_PRIORITY;
    
    /**
     * 适配方式,默认使用接口，表示使用相等方式匹配，同TypeEqualsAssert方式
     */
    Class<? extends TypeAssert> handler() default TypeAssert.class;
    

    /**
     * 默认顺序,事件的响应顺序
     */
    final String DEFAULT_PRIORITY = "N";

}
