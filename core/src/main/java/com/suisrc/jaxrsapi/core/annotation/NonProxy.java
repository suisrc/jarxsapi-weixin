package com.suisrc.jaxrsapi.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 执行的方法是不需要构建代理的
 * 
 * 常用于标记不需要执行代理和集成的方法
 *
 */
@Target({ METHOD })
@Retention(RUNTIME)
public @interface NonProxy {
}
