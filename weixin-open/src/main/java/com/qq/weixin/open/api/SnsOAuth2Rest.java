package com.qq.weixin.open.api;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.open.OpenWxConsts;
import com.qq.weixin.open.result.OAuth2AccessToken;
import com.qq.weixin.open.result.UserInfoResult;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;
import com.suisrc.jaxrsapi.core.annotation.ThreadValue;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 1 第一步：用户同意授权，获取code
 * 2 第二步：通过code换取网页授权access_token
 * 3 第三步：刷新access_token（如果需要）
 * 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
 * 5 附：检验授权凭证（access_token）是否有效
 * 
 * code说明 ： code作为换取access_token的票据，每次用户授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。 
 * @author Y13
 *
 */
@RemoteApi
public interface SnsOAuth2Rest {

	/**
	 * 通过code换取网页授权access_token
	 * 首先请注意，这里通过code换取的是一个特殊的网页授权access_token,与基础支持中的access_token（该access_token用于调用其他接口）不同。
	 * 公众号可通过下述接口来获取网页授权access_token。如果网页授权的作用域为snsapi_base，则本步骤中获取到网页授权access_token的同时，
	 * 也获取到了openid，snsapi_base式的网页授权流程即到此为止。
	 * 
	 * 获取code后，请求以下链接获取access_token：  
	 * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code 
	 * 
	 * @param appid          公众号的唯一标识
	 * @param redirectUri    公众号的appsecret
	 * @param code           填写第一步获取的code参数
	 * @param grantType      填写为authorization_code 
	 * @return
	 */
	@GET
	@Path("sns/oauth2/access_token")
	@Produces(MediaType.APPLICATION_JSON)
	OAuth2AccessToken getAccessToken(
			@QueryParam("appid")     @SystemValue(OpenWxConsts.APP_ID)     String appid, 
			@QueryParam("secret")    @SystemValue(OpenWxConsts.APP_SECRET) String secret, 
			@QueryParam("code")                                            String code, 
			@QueryParam("grant_type")@DefaultValue("authorization_code")   String grantType);
	
	default OAuth2AccessToken getAccessToken(String code) {
		return getAccessToken(null, null, code, null);
	}
	
	/**
	 * 刷新access_token（如果需要）
	 * 
	 * 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
	 * refresh_token有效期为30天，当refresh_token失效之后，需要用户重新授权。
	 * 
	 * 获取第二步的refresh_token后，请求以下链接获取access_token：  
	 * https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN 
	 * @param appid      公众号的唯一标识
	 * @param grantType  填写为refresh_token
	 * @param code       填写通过access_token获取到的refresh_token参数  
	 * @return
	 */
	@GET
	@Path("sns/oauth2/refresh_token")
	@Produces(MediaType.APPLICATION_JSON)
	OAuth2AccessToken refreshToken(
			@QueryParam("appid")        @SystemValue(OpenWxConsts.APP_ID) String appid, 
			@QueryParam("grant_type")   @DefaultValue("refresh_token")    String grantType,
			@QueryParam("refresh_token")                                  String refreshToken);
	
	default OAuth2AccessToken refreshToken(String refreshTokn) {
		return refreshToken(null, null, refreshTokn);
	}
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * 如果网页授权作用域为snsapi_userinfo，则此时开发者可以通过access_token和openid拉取用户信息了。
	 * 
	 * http：GET（请使用https协议） https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	 * 
	 * @param accessToken  网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param openid       用户的唯一标识
	 * @param lang         返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return
	 */
	@GET
	@Path("sns/oauth2/userinfo")
	@Produces(MediaType.APPLICATION_JSON)
	UserInfoResult getUserinfo(
			@QueryParam("access_token")@SystemValue(OpenWxConsts.ACCESS_TOKEN) String accessToken, 
			@QueryParam("openid")      @SystemValue(OpenWxConsts.OPEN_ID)      String openid,
			@QueryParam("lang")        @DefaultValue("zh_CN")                  String lang);
	
	default UserInfoResult getUserinfo() {
		return getUserinfo(null, null, null);
	}
	/**
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * http：GET（请使用https协议） https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID 
	 * 
	 * @param accessToken  网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param openid       用户的唯一标识
	 * @return
	 */
	@GET
	@Path("sns/oauth2/userinfo")
	@Produces(MediaType.APPLICATION_JSON)
	WxErrCode auth(
			@QueryParam("access_token")@SystemValue(OpenWxConsts.ACCESS_TOKEN) String accessToken, 
			@QueryParam("openid")      @ThreadValue("openid")       String openid);
}
