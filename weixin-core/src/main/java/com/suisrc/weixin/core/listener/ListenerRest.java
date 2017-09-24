package com.suisrc.weixin.core.listener;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author Y13
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface ListenerRest {

    /**
     * 监听的接口的类型
     */
    Class<?> value();

}
