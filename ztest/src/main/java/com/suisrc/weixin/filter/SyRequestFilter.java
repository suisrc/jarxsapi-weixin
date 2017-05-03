package com.suisrc.weixin.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;


/**
 * 该对象拦截所有请求
 * 对登录凭证进行获取，放入登录缓存中。
 * @author Y13
 *
 */
@Priority(1)
@Provider
public class SyRequestFilter implements ContainerRequestFilter {
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// 把请求的内容放入线程缓存中，以备框架使用
		System.out.println(requestContext.getUriInfo().getAbsolutePath().toString());
	}


}
