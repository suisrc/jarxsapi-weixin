package com.qq.weixin.open.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.open.OpenWxConsts;
import com.qq.weixin.open.handler.WeChatRedirectHandler;
import com.qq.weixin.open.proxy.Oauth2AuthorizeProxy;
import com.suisrc.jaxrsapi.core.annotation.LogicProxy;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;
import com.suisrc.jaxrsapi.core.annotation.ValueHelper;

/**
 * 网站应用微信登录是基于OAuth2.0协议标准构建的微信OAuth2.0授权登录系统。
 * 在进行微信OAuth2.在进行微信OAuth2.0授权登录接入之前，在微信开放平台
 * 注册开发者帐号，并拥有一个已审核通过的网站应用，并获得相应的AppID和AppSecret，
 * 申请微信登录且通过审核后，可开始接入流程。
 * 
 * 微信OAuth2.0授权登录目前支持authorization_code模式，适用于拥有server端的应用授权。该模式整体流程为：
 * 1. 第三方发起微信授权登录请求，微信用户允许授权第三方应用后，微信会拉起应用或重定向到第三方网站，并且带上授权临时票据code参数；
 * 2. 通过code参数加上AppID和AppSecret等，通过API换取access_token；
 * 3. 通过access_token进行接口调用，获取用户基本数据资源或帮助用户实现基本操作。
 * @author Y13
 *
 */
@RemoteApi
public interface OpenOAuth2Rest {

	/**
	 * https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
	 * 第三方使用网站应用授权登录前请注意已获取相应网页授权作用域（scope=snsapi_login），则可以通过在PC端打开以下链接：
	 * 若提示“该链接无法访问”，请检查参数是否填写错误，如redirect_uri的域名与审核时填写的授权域名不一致或scope不为snsapi_login。
	 * 
	 * 返回说明
	 * 用户允许授权后，将会重定向到redirect_uri的网址上，并且带上code和state参数
	 * redirect_uri?code=CODE&state=STATE
	 * 若用户禁止授权，则重定向后不会带上code参数，仅会带上state参数
	 * redirect_uri?state=STATE
	 * 
	 * 使用Oauth2AuthorizeProxy返回跳转的URL地址
	 * @param appid        公众号的唯一标识
	 * @param redirectUri  授权后重定向的回调链接地址，请使用urlEncode对链接进行处理
	 * @param responseType 返回类型，请填写code
	 * @param scope        应用授权作用域，
	 *                     snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
	 *                     snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
	 * @param state
	 * @return
	 */
	@GET
	@Path("connect/oauth2/authorize")
	@Produces(MediaType.APPLICATION_JSON)
	@LogicProxy(Oauth2AuthorizeProxy.class)
	String authorize(
			@QueryParam("appid")        @SystemValue(OpenWxConsts.APP_ID)        String appid, 
			@QueryParam("redirect_uri")                                          String redirectUri, 
			@QueryParam("response_type")@DefaultValue("code")                    String responseType, 
			@QueryParam("scope")        @DefaultValue("snsapi_base")             String scope, 
			@QueryParam("state")        @ValueHelper(WeChatRedirectHandler.class)String state);
	
	default String authorize( String redirectUri, String scope, String state ) {
		return authorize(null, redirectUri, null, scope, state);
	}
}
