package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.result.UserInfoResult;
import com.qq.weixin.mp.result.UserListResult;
import com.suisrc.jaxrsapi.core.Consts;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.Reviser;
import com.suisrc.jaxrsapi.core.annotation.Value;
import com.suisrc.weixin.core.filter.WxTokenCheck;

/**
 * 微信公众号接口 用户管理
 * 
 * @author Y13
 *
 */
@RemoteApi
public interface UserRest {

    /**
     * 公众号可通过本接口来获取帐号的关注者列表，关注者列表由一串OpenID（加密后的微信号，每个用户对每个公众号的OpenID是唯一的）组成。
     * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求。
     * 
     * http请求方式: GET
     * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
     * 
     * @param accessToken 调用接口凭证, 当赋值为null时候，系统会通过SystemValue标识在对应的激活器中找到该常量
     * @param openid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    @GET
    @Path("cgi-bin/user/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    UserListResult getUserGet(
            @QueryParam("access_token") @Value(MpWxConsts.ACCESS_TOKEN) String accessToken,
            @QueryParam("next_openid")                                  String openid);

    default UserListResult getUserGet(String openid) {
        return getUserGet(null, openid);
    }

    /**
     * 开发者可通过OpenID来获取用户基本信息
     * 
     * http请求方式: GET
     * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     * 
     * @param userInfo 开发者可通过OpenID来获取用户基本信息
     * @return
     */
    @GET
    @Path("cgi-bin/user/info")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Reviser(value = WxTokenCheck.class, master = Consts.FIELD_ACTIVATOR)
    UserInfoResult getUserInfo(
            @QueryParam("access_token") @Value(MpWxConsts.ACCESS_TOKEN) String accessToken,
            @QueryParam("openid")                                       String openid,
            @QueryParam("lang") @DefaultValue("zh_CN")                  String lang);

    default UserInfoResult getUserInfo(String openid) {
        return getUserInfo(null, openid, null);
    }

}
