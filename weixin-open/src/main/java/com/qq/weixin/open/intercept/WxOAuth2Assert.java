package com.qq.weixin.open.intercept;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * 执行weixin的web oauth认证验证
 * 
 * 只对请求过程中的令牌进行验证，不进行微信oauth认证令牌进行获取
 * @author Y13
 *
 */
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface WxOAuth2Assert {
}
