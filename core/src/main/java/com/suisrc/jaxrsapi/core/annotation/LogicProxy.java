package com.suisrc.jaxrsapi.core.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * value对应的类型必须实现一个proxy方法， 并且第一个参数是String型，接受远程访问的url地址
 * 后面的其他参数为接口参数
 * 使用该方法标记的代理不会去访问远程服务器，回事通过本地方法覆盖远程访问
 * @author Y13
 *
 */
@Target({ METHOD })
@Retention(RUNTIME)
public @interface LogicProxy {

	/**
	 * 辅助修正参数的内容
	 */
	Class<?> value();
}
