package com.qq.weixin.open.handler;

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
import com.qq.weixin.open.intercept.WxOAuth2;
import com.qq.weixin.open.result.OAuth2AccessToken;
import com.suisrc.jaxrsapi.core.Consts;
import com.suisrc.jaxrsapi.core.Global;
import com.suisrc.weixin.core.exception.ResponseException;
import com.suisrc.weixin.message.ErrCodeMsg;

/**
 * 执行默认的OAuth2.0验证方法
 * 
 * @author Y13
 *
 */
@ApplicationScoped
public class DefaultWxOAuth2Handler implements WxOAuth2Handler {

    /**
     * 获取oauth认证连接信息
     */
    @SuppressWarnings("cdi-ambiguous-dependency")
    @Inject
    private OpenOAuth2Rest openOAuth2Rest;

    /**
     * 用于登录凭证管理
     */
    @Inject
    private AccessTokenHandler tokenHandler;


    @Override
    public void checkAndGetOAuth2(InvocationContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getOAuth2(InvocationContext ctx) {
        // TODO Auto-generated method stub

    }

    @Override
    public void checkOAuth2(InvocationContext ctx) {
        // TODO Auto-generated method stub

    }
    
    public void intercept(InvocationContext ctx) {
        ContainerRequestContext request = Global.getThreadCache(Consts.CONTAINER_REQUEST_CONTEXT);
        if (request == null) {
            throw new ResponseException("can't found container request context!", "服务器内部异常，无法通过OAuth2认证!");
        }

        Cookie cookie = request.getCookies().get(OpenWxConsts.COOKIE_OPEN_ID);
        if (cookie != null) {
            OAuth2AccessToken token = tokenHandler.getByOpenid(cookie.getValue()); // 查找系统中的访问access token
            if (token != null) {
                Global.putThreadCache(OpenWxConsts.ACCESS_TOKEN, token);
                return;
            }
        }
        OAuth2AccessToken token = getAccessToken(ctx, request);
        // 保存token, 正常返回
        Global.putThreadCache(OpenWxConsts.COOKIE_OPEN_ID, token.getOpenid()); // 用于结束访问后，保存到cookies中
        Global.putThreadCache(OpenWxConsts.ACCESS_TOKEN, token);
    }

    /**
     * 获取网银授权的access token
     * 
     * @param ctx
     * @param request
     * @return
     */
    private OAuth2AccessToken getAccessToken(InvocationContext ctx, ContainerRequestContext request) {
        WxOAuth2 anno = getWxOAuth2Annotation(ctx);
        String code = request.getUriInfo().getQueryParameters().getFirst("code");
        if (code == null) { // 确定是否是微信服务器的应答
            redirectWxOAuth2Server(request, anno);
        }
        String state = request.getUriInfo().getQueryParameters().getFirst("state");
        if (!anno.state().equals(state)) { // 确定是否为本服务器主动请求微信服务器回调
            throw new ResponseException("annotatioin state isn't equals request state!", "请求不是来自服务器请求微信服务器的回调!");
        }
        OAuth2AccessToken token = tokenHandler.getByCode(code);
        if (token == null) {
            throw new ResponseException("can't get oauth2.0 access token!", "通过微信CODE无法查询到Access Token!");
        }
        if (token.getErrcode() != null) {
            // if ( token.getErrcode().equals("40029") ) { // 不合法的code
            // redirectWxOAuth2Server(request, anno);
            // }
            // 暂时对不合法的code不做重定向处理
            throw new ResponseException("can't get oauth2.0 access token:" + token.getErrmsg(),
                    "通过微信CODE无法查询到Access Token:" + ErrCodeMsg.getErrCodeMsg(token.getErrcode()));
        }
        return token;
    }

    /**
     * 重定向到微信服务器
     * 
     * @param request
     * @param anno
     */
    private void redirectWxOAuth2Server(ContainerRequestContext request, WxOAuth2 anno) {
        String locationUri = request.getUriInfo().getAbsolutePath().toString();
        String redirectUri = openOAuth2Rest.authorize(locationUri, anno.scope(), anno.state());// 获取重定向地址
        // 执行重定向
        throw new ResponseException("request OAuth2 authentication to the weixin server",
                re -> Response.seeOther(URI.create(redirectUri)).build());
    }

    /**
     * 获取WxOAuth2注解
     * 
     * @param ctx
     * @return
     */
    private WxOAuth2 getWxOAuth2Annotation(InvocationContext ctx) {
        Method method = ctx.getMethod();
        WxOAuth2 anno = method.getAnnotation(WxOAuth2.class);
        if (anno == null) {
            anno = method.getDeclaringClass().getAnnotation(WxOAuth2.class);
            if (anno == null) {
                throw new RuntimeException("can't found weixin oauth 2.0 annotation!");
            }
        }
        return anno;
    }

}
