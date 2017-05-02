package com.qq.weixin.open.intercept;

import java.lang.reflect.Method;
import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

import com.qq.weixin.open.OpenWxConsts;
import com.qq.weixin.open.api.OpenOAuth2Rest;
import com.qq.weixin.open.api.SnsOAuth2Rest;
import com.qq.weixin.open.exception.ResponseException;
import com.qq.weixin.open.result.OAuth2AccessToken;
import com.suisrc.jaxrsapi.core.Consts;
import com.suisrc.jaxrsapi.core.Global;

/**
 * 执行默认的OAuth2.0验证方法
 * @author Y13
 *
 */
@ApplicationScoped
public class DefaultWxOAuth2Handler implements WxOAuth2Handler {
	
	/**
	 * 获取oauth认证连接信息
	 */
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject private OpenOAuth2Rest openOAuth2Rest;
	/**
	 * 用户信息获取
	 */
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject private SnsOAuth2Rest snsOAuth2Rest;

	@Override
	public void intercept(InvocationContext ctx) {
		ContainerRequestContext request = Global.getThreadCache(Consts.CONTAINER_REQUEST_CONTEXT);
		if( request == null ) { throw new RuntimeException("can't found container request context!"); }
		
		Cookie cookie = request.getCookies().get(OpenWxConsts.COOKIE_OPEN_ID);
		if( cookie != null ) {
			String openid = cookie.getValue();
			String accessToken = ""; // 查找系统中的访问access token
			Global.putThreadCache(OpenWxConsts.OPEN_ID, openid);
			Global.putThreadCache(OpenWxConsts.ACCESS_TOKEN, accessToken);
			return;
		}
		Method method = ctx.getMethod();
		WxOAuth2 anno = method.getAnnotation(WxOAuth2.class);
		if( anno == null ) {
			anno = method.getDeclaringClass().getAnnotation(WxOAuth2.class);
			if( anno == null ) {
				throw new RuntimeException("can't found weixin oauth 2.0 annotation!");
			}
		}
		String code = request.getUriInfo().getQueryParameters().getFirst("code");
		if( code == null ) {
			String locationUri = request.getUriInfo().getAbsolutePath().toString();
			String redirectUri = openOAuth2Rest.authorize(locationUri, anno.scope(), anno.state());
			// 重定向
			throw new ResponseException(re  -> Response.seeOther(URI.create(redirectUri)).build());
		}
		String state = request.getUriInfo().getQueryParameters().getFirst("state");
		if( !anno.state().equals(state) ) {
			throw new RuntimeException("annotatioin state isn't equals request state!");
		}
		OAuth2AccessToken token = snsOAuth2Rest.getAccessToken(code);
		// 保存token
		Global.putThreadCache(OpenWxConsts.OPEN_ID, token.getOpenid());
		Global.putThreadCache(OpenWxConsts.ACCESS_TOKEN, token.getAccessToken());
	}


}
