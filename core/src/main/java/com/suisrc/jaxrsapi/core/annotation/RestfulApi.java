package com.suisrc.jaxrsapi.core.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Restful 提供的注解，用户过滤远程接口还是本地接口使用
 * @author Y13
 *
 */
@Target({ TYPE/*, METHOD, FIELD*/ })
@Retention(RUNTIME)
public @interface RestfulApi {
}
