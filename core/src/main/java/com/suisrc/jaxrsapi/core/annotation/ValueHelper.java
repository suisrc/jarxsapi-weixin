package com.suisrc.jaxrsapi.core.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.suisrc.jaxrsapi.core.func.ValueHandler;

/**
 * 辅助修正参数的内容助手
 * @author Y13
 *
 */
@Target({ PARAMETER, FIELD })
@Retention(RUNTIME)
public @interface ValueHelper {

	/**
	 * 辅助修正参数的内容
	 */
	Class<? extends ValueHandler<?>> value();
}
