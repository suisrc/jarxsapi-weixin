package com.suisrc.jaxrsapi.core.annotation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 获取服务器系统中的数据
 * @author Y13
 *
 */
@Target({ PARAMETER, FIELD })
@Retention(RUNTIME)
public @interface SystemValue {

	/**
	 * 获取服务器中的数据作为默认参数
	 */
	String value();
}
