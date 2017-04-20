package com.qq.weixin.mp.param;

import javax.ws.rs.QueryParam;

import com.qq.weixin.mp.WxConsts;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;

/**
 * 参数
 * @author Y13
 *
 */
public class UserInfoParam {
	
	@QueryParam("access_token")
	@SystemValue(WxConsts.ACCESS_TOKEN) 
	private String accessToken;
	
	@QueryParam("openid")
	private String openid;
	
	@QueryParam("lang") 
	private String lang;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
}
