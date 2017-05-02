package com.qq.weixin.open.bean;

import javax.ws.rs.CookieParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * 默认验证的时候，通过code和openid进行验证，
 * 所以在默认情况下，使用拦截器的方法需要继承CodeBean
 * 
 * 系统中目前使用Global的线程缓存中存放需要的内容，
 * 但是该系统需要javaee7.0支持
 * @author Y13
 *
 */
@Deprecated
public class CodeBean {
	
	@Context UriInfo uriInfo;
	
	@QueryParam("code")
	private String code;
	
	@CookieParam("openid")
	private String openid;
	
	public String getOpenid() {
		return openid;
	}
	
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public UriInfo getUriInfo() {
		return uriInfo;
	}
	
	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

}
