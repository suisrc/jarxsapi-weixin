package com.qq.weixin.qy.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.qy.QyWxConsts;
import com.qq.weixin.qy.result.UserInfoResult;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.SystemValue;

/**
 * 微信公众号接口
 * 用户管理
 * @author Y13
 *
 */
@RemoteApi
public interface UserRest {

	/**
	 * 获取成员
	 * 
	 * http请求方式: GET https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&userid=USERID
	 * 
	 * @param userInfo 成员UserID。对应管理端的帐号
	 * @return
	 */
	@GET
	@Path("cgi-bin/user/get")
	@Produces(MediaType.APPLICATION_JSON)
	UserInfoResult getUserInfo(
			@QueryParam("access_token")@SystemValue(QyWxConsts.ACCESS_TOKEN) String accessToken, 
			@QueryParam("userid")                                            String userid);
	
	default UserInfoResult getUserInfo( String userid ) {
		return getUserInfo(null, userid);
	}

}
