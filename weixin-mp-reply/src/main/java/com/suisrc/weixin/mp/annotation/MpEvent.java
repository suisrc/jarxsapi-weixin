package com.suisrc.weixin.mp.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.suisrc.weixin.core.check.TypeAssert;

/**
 * 事件类型数据描述
 * @author Y13
 */
@Target({TYPE})
@Retention(RUNTIME)
@Inherited
public @interface MpEvent {
    
    /**
     * 事件的类型
     * @return
     */
    String value();
    
    /**
     * 事件的名字
     * 
     * 暂时用于描述，而不参与处理
     * @return
     */
    String name() default "unefined";
    
    /**
     * 配置状态
     * 
     * 禁用后，即使该类型被加载，但是也是被忽略
     */
    boolean effect() default true;
    
    /**
     * 匹配的顺序
     */
    String priority() default "";
    
    /**
     * 适配方式,默认使用接口，表示使用相等方式匹配，同TypeEqualsAssert方式
     * 用于断言是否符合该字段内容的断言器
     */
    Class<? extends TypeAssert> handler() default TypeAssert.class;

}
