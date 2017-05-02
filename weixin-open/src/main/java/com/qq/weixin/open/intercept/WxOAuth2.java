package com.qq.weixin.open.intercept;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * 执行weixin的web oauth认证
 * @author Y13
 *
 */
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface WxOAuth2 {
	
	/**
	 * 应用授权作用域
	 * @return
	 */
	String scope() default "snsapi_base";
	
	/**
	 * 返回常量，可以用于服务器验证
	 * @return
	 */
	String state() default "weixin-web-oauth-2.0";
}
