package com.suisrc.weixin.core.listener;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 监听器可以同时指定多个监听类型，这里分配监听类型
 * 
 * 当一个监听器返回结果为null时候，会继续进行下一个匹配
 */
@Target({TYPE})
@Retention(RUNTIME)
@Inherited
public @interface ListenerInclude {

    /**
     * 监听的内容集合
     */
    Class<?>[] value();
}
