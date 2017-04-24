package com.qq.weixin.mp.api;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.WxConsts;
import com.qq.weixin.mp.param.UserInfoParam;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;

/**
 * 微信公众号接口
 * @author Y13
 *
 */
@Path("user")
public interface UserRest {

	/**
	 * http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	 */
	@GET
	@Path("info")
	@Produces(MediaType.APPLICATION_JSON)
	String getUserInfo(@BeanParam UserInfoParam userInfo);

	/**
	 * http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
	 */
	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	String getUserGet(@QueryParam("access_token")@SystemValue(WxConsts.ACCESS_TOKEN) String accessToken, @QueryParam("next_openid") String openid);

}
