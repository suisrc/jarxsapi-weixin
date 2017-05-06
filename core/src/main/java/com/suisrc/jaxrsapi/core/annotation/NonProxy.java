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
 * 通常情况下请求的方法必须通过GET, POST, PUT, DELETE进行标记，
 * 但是如果有这些标记，而且不想实现代理内容，可以通过该字段标记进行代理请求的忽略
 * 忽略后，需要保证该请求的可实现性，否则系统处理时候会发生错误
 *
 */
@Target({ METHOD })
@Retention(RUNTIME)
public @interface NonProxy {
}
