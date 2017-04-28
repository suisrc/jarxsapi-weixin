package com.qq.weixin.mp.param;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;

/**
 * 开发者可通过OpenID来获取用户基本信息
 * @author Y13
 *
 */
public class UserInfoParam {
	
	/**
	 * 调用接口凭证
	 * 不需要给出，null代理
	 */
	@QueryParam("access_token")
	@SystemValue(MpWxConsts.ACCESS_TOKEN) 
	private String accessToken;
	
	/**
	 * 普通用户的标识，对当前公众号唯一
	 */
	@QueryParam("openid")
	private String openid;
	
	/**
	 * 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 */
	@QueryParam("lang") 
	@DefaultValue("zh_CN")
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
