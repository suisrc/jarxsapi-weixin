package com.suisrc.jaxrsapi.core.provider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.jaxrs.xml.JacksonXMLProvider;

/**
 * 用于解决某些系统返回的content-type不规范问题
 * @author Y13
 *
 */
@Provider
@Consumes(MediaType.WILDCARD) // NOTE: required to support "non-standard" JSON variants
@Produces(MediaType.WILDCARD)
public class JacksonXMLProvider2 extends JacksonXMLProvider {

	/**
	 * 微信服务器返回结果集是content-type text/html，但是结果确实xml
	 * 所以这里进行排除TEXT_HTML_TYPE的结果集进行处理
	 */
	@Override
	protected boolean hasMatchingMediaType(MediaType mediaType) {
		if (mediaType != null) {
			String subtype = mediaType.getSubtype();
			return "xml".equalsIgnoreCase(subtype) || subtype.endsWith("+xml")
					|| MediaType.TEXT_HTML_TYPE.getSubtype().equals(subtype); // 解决微信服务器返回结果集为text/html的情况
		}
		return true;
	}
}
