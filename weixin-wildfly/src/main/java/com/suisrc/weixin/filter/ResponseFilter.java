package com.suisrc.weixin.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.ext.Provider;

import com.suisrc.jaxrsapi.core.Global;
import com.suisrc.weixin.core.WxConsts;

import io.undertow.util.Headers;

/**
 * 释放一些系统访问资源
 * 
 * @author Y13
 */
@Provider
@Priority(999)
@ApplicationScoped
public class ResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        String openid = Global.getThreadCache(WxConsts.COOKIE_OPEN_ID);
        if (openid != null) { // 保存cookies
            String domain = requestContext.getUriInfo().getBaseUri().getHost();
            int maxAge = 30 * 24 * 60 * 60;
            NewCookie cookie = new NewCookie(WxConsts.COOKIE_OPEN_ID, openid, "/", domain, 1, null, maxAge, false);
            responseContext.getHeaders().addFirst(Headers.SET_COOKIE.toString(), cookie);
        }
        // 清除想成上的所有内容
        Global.removeThreadCache();
    }

}
