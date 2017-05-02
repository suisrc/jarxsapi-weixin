package com.suisrc.weixin.filter;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import com.suisrc.jaxrsapi.core.Global;

/**
 * 释放一些系统访问资源
 * @author Y13
 */
@Provider
@ApplicationScoped
public class ResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
//		responseContext.getCookies().put(key, value);
		// 清除想成上的所有内容
		Global.removeThreadCache();
	}

}
