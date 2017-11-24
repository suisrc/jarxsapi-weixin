package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.param.template.SubscribeTemplateMsgParam;
import com.qq.weixin.mp.proxy.SubscribemsgTemplateProxy;
import com.qq.weixin.mp.proxy.WeChatRedirectHandler;
import com.suisrc.jaxrsapi.core.Consts;
import com.suisrc.jaxrsapi.core.annotation.LocalProxy;
import com.suisrc.jaxrsapi.core.annotation.Reviser;
import com.suisrc.jaxrsapi.core.annotation.Value;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 一次性订阅消息
 * 开发者可以通过一次性订阅消息授权让微信用户授权第三方移动应用（接入说明）或公众号，获得发送一次订阅消息给到授权微信用户的机会。授权微信用户可以不需要关注公众号。微信用户每授权一次，开发者可获得一次下发消息的权限。对于已关注公众号的，消息将下发到公众号会话；未关注公众号的，将下发到服务通知。
 * 
 * 公众号或网页使用一次性订阅消息流程如下：
 * 
 * 第一步：需要用户同意授权，获取一次给用户推送一条订阅模板消息的机会
 * 
 *    通过subscribemsgUrl获取url让用户跳转授权
 * 
 * 第二步：通过API推送订阅模板消息给到授权微信用户
 * 
 *    通过subscribeMessage向用户发送消息
 * 
 * 
 * @author Y13
 *
 */
public interface TemplateSubscribeRest {
    
    /**
     * 默认跳转路径KEY, 暂时使用
     */
    public static final String REDIRECT_URI_KEY = MpWxConsts.PRE_SYSTEM + "system.weixin.mp.subscribemsg.redirect_uri";

    /**
     * 获取用户跳转地址
     * 该方法为本地代理方法，不会向远程反问服务器, 数据访问会从定向到SubscribemsgTemplateProxy类型的proxy方法中。
     * 
     * 
     * 在确保微信公众帐号拥有订阅消息授权的权限的前提下（已认证的公众号即有权限，可登陆公众平台在接口权限列表处查看），引导用户在微信客户端打开如下链接：
     * https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm&appid=wxaba38c7f163da69b&scene=1000&template_id=1uDxHNXwYQfBmXOfPJcjAS3FynHArD8aWMEFNRGSbCc&
     * redirect_url=http%3a%2f%2fsupport.qq.com&reserved=test#wechat_redirect
     * 
     * 参数说明
     * 参数.            是否必须.    说明
     * action           是.         直接填get_confirm即可
     * appid            是.         公众号的唯一标识
     * scene            是.         重定向后会带上scene参数，开发者可以填0-10000的整形值，用来标识订阅场景值
     * template_id      是.         订阅消息模板ID，登录公众平台后台，在接口权限列表处可查看订阅模板ID
     * redirect_url     是.         授权后重定向的回调地址，请使用UrlEncode对链接进行处理。注：要求redirect_url的域名要跟登记的业务域名一致，且业务域名不能带路径。业务域名需登录公众号，在设置-公众号设置-功能设置里面对业务域名设置。
     * reserved         否.         用于保持请求和回调的状态，授权请后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验，开发者可以填写a-zA-Z0-9的参数值，最多128字节，要求做urlencode
     * #wechat_redirect 是.         无论直接打开还是做页面302重定向时，必须带此参数
     * 
     * 用户同意或取消授权后会返回相关信息
     * 如果用户点击同意或取消授权，页面将跳转至：
     * redirect_url/?openid=OPENID&template_id=TEMPLATE_ID&action=ACTION&scene=SCENE
     * 参数说明
     * 参数.            说明
     * openid          用户唯一标识，只在用户确认授权时才会带上
     * template_id     订阅消息模板ID
     * action          用户点击动作，”confirm”代表用户确认授权，”cancel”代表用户取消授权
     * scene           订阅场景值
     * reserved        请求带入原样返回
     * @param appid
     * @param redirectUri
     * @param responseType
     * @param scope
     * @param state
     * @return
     */
    @GET
    @Path("non-path")
    @Produces(MediaType.APPLICATION_JSON)
    @LocalProxy(value=SubscribemsgTemplateProxy.class, master=Consts.FIELD_THIS)
    @Reviser(WeChatRedirectHandler.class)
    String subscribemsgUrl(
            @QueryParam("action")       @DefaultValue("get_confirm")                String action, 
            @QueryParam("appid")        @Value(MpWxConsts.APP_ID)                   String appid, 
            @QueryParam("scene")                                                    String scene,
            @QueryParam("template_id")                                              String templateId,
            @QueryParam("redirect_uri") @Value(REDIRECT_URI_KEY)                    String redirectUri,
            @QueryParam("reserved")                                                 String reserved);
    
    default String subscribemsgUrl( String scene, String templateId, String redirectUri, String reserved) {
        return subscribemsgUrl(null, null, scene, templateId, redirectUri, reserved);
    }
    
    /**
     * 通过API推送订阅模板消息给到授权微信用户
     * 接口请求说明
     * http请求方式: post
     * https://api.weixin.qq.com/cgi-bin/message/template/subscribe?access_token=ACCESS_TOKEN
     * post数据示例
     * {
     * “touser”:”OPENID”,
     * “template_id”:”TEMPLATE_ID”,
     * “url”:”URL”,
     * “scene”:”SCENE”,
     * “title”:”TITLE”,
     * “data”:{
     *     “content”:{
     *         “value”:”VALUE”,
     *         “color”:”COLOR”
     *      }
     * }
     * }
     * 返回说明
     * 在调用接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
     * {
     *   “errcode”:0,
     *   “errmsg”:”ok”
     * }
     */
    @POST
    @Path("cgi-bin/message/template/subscribe")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode subscribeMessage(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, SubscribeTemplateMsgParam param);
    
    default WxErrCode subscribeMessage(SubscribeTemplateMsgParam param) {
        return subscribeMessage(null, param);
    }
}