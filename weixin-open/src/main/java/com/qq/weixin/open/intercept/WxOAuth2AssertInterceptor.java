package com.qq.weixin.open.intercept;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.qq.weixin.open.handler.WxOAuth2Handler;

/**
 * OAuth2.0认证拦截
 * @author Y13
 */
@WxOAuth2Assert
@Interceptor
@Priority(value = Interceptor.Priority.APPLICATION)
public class WxOAuth2AssertInterceptor {
	/**
	 * 微信oauth2.0认证拦截实体
	 */
	@Inject private WxOAuth2Handler handler;

	/**
	 * 执行拦截操作
	 * @param ctx
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object oauth2(InvocationContext ctx) throws Exception {
		handler.checkOAuth2(ctx);
		return ctx.proceed();
	}
}
