package com.suisrc.jaxrsapi.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.suisrc.jaxrsapi.core.func.InterceptHandler;

/**
 * 对参数进行拦截修正
 * @author Y13
 *
 */
@Target({ PARAMETER, FIELD })
@Retention(RUNTIME)
public @interface InterceptParam {

	/**
	 * 辅助修正参数的内容
	 */
	Class<? extends InterceptHandler<?>> value();
	
	/**
	 * Handler 构造的时候是否带有所有者信息
	 */
	String master() default NONE;

	/**
	 * 默认master内容
	 */
	String NONE = "";
}
