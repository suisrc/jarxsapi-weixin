package com.suisrc.weixin.core.listener;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 监听器可以同时指定多个监听类型，这里分配监听类型
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface Include {

    /**
     * 监听的内容集合
     */
    Class<?>[] value();
}
