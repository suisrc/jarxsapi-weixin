package com.suisrc.weixin.core.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.suisrc.weixin.core.bean.WxAccessToken;

/**
 * 公众号可以使用AppID和AppSecret调用本接口来获取access_token。
 * AppID和AppSecret可在微信公众平台官网-开发页中获得（需要已经成为开发者，且帐号没有异常状态）。
 * 注意调用所有微信接口时均需使用https协议。如果第三方不使用中控服务器，而是选择各个业务逻辑点各
 * 自去刷新access_token，那么就可能会产生冲突，导致服务不稳定。
 * @author Y13
 *
 */
@Path("token")
public interface AccessTokenRest {

	/**
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	 * @param grantType   是必须, 获取access_token填写client_credential
	 * @param appid       是必须, 第三方用户唯一凭证
	 * @param secret      是必须, 第三方用户唯一凭证密钥，即appsecret
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	WxAccessToken getToken(@QueryParam("grant_type") String grantType, @QueryParam("appid") String appid, @QueryParam("secret") String secret);
}
