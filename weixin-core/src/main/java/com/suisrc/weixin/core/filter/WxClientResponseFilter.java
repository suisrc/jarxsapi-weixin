package com.suisrc.weixin.core.filter;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * 微信某一些请求返回的时候content-type不正确修正
 * @author Y13
 *
 */
public class WxClientResponseFilter implements ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
//		if( WxConsts.DEBUG ) { System.out.println(requestContext.getUri().toString()); }
		MediaType mediaType = responseContext.getMediaType();
		if( !requestContext.getAcceptableMediaTypes().contains(mediaType) ) {
			mediaType = requestContext.getAcceptableMediaTypes().get(0); // 取第一个进行替换
			responseContext.getHeaders().addFirst(HttpHeaders.CONTENT_TYPE, mediaType.toString());
		}
	}

}
