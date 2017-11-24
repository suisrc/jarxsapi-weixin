package com.qq.weixin.open.intercept;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * 执行weixin的web oauth认证
 * 
 * 微信oauth认证，作用在GET请求上，用于在页面加载过程中验证当前用户请求是否进行过oauth认证
 * 
 * 与WxOath2Assert结合使用，不同之处在于，前者是页面进行对令牌进行获取和验证，后者仅仅对令牌进行验证
 * 
 * 这样设计的目前是在前后端分离设计中，避免跨域访问的问题，如果服务器使用的是集群模式，并且配置的跨域访问，
 * 这个使用，仅仅使用本注解就可以了，但是如果没有配置跨域访问，当使用ajax获取数据适用男，如果没有oauth
 * 认证，因此在获取数据数据的时候，没有认证，会重归定向到微信服务器，这样就造成了跨域访问的问题，因此，这个
 * 时候，需要换一个思考角度，即首页访问的时候，使用后端服务器从定向到前端，在从定向前需要对用于的访问的数据
 * 前期的Oauth认证处理。WxOauth2Assert用于ajax数据获取过程中的oauth2验证，如果没有微信访问令牌，即立即
 * 返回失败，所以WxOauth2用于首页记性oauth2认证令牌的获取，WxOauth2Assert用于用于其他请求的合法性记性验证
 * 
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
	 * 
	 * 保留"{}", 如果如果使用"{}"进行标记，就表示该内容为动态内容，需要向缓冲区获取，
	 * 由于系统使用并发处理，所以这里使用的标识符应该具有全局性 
	 * 
	 * 推荐使用接口定制化处理，即一个接口由于固定的表示，这样即减少了数据的缓冲处理，有有效防止服务器接受虚假ddos攻击的风险
	 * @return
	 */
	String state() default "weixin-web-oauth-2.0";
}
