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
public @interface MpEventKey {
    
    /**
     * 事件的类型
     * @return
     */
    String value();
    
    /**
     * 事件的名字
     * @return
     */
    String name() default "unefined";
    
    /**
     * 配置状态
     * 可以禁用某些内容的解析
     */
    boolean effect() default true;
    
    /**
     * 匹配的顺序
     */
    String priority() default "";
    
    /**
     * 适配方式,默认使用接口，表示使用相等方式匹配，同TypeEqualsAssert方式
     */
    Class<? extends TypeAssert> handler() default TypeAssert.class;

}
