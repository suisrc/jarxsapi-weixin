package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.jaxrsapi.core.annotation.Value;

/**
 * 获取公众号的自动回复规则
 * 
 * 开发者可以通过该接口，获取公众号当前使用的自动回复规则，包括关注后自动回复、消息自动回复（60分钟内触发一次）、关键词自动回复。
 * 请注意：
 * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自动回复配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
 * 2、本接口仅能获取公众号在公众平台官网的自动回复功能中设置的自动回复规则，若公众号自行开发实现自动回复，或通过第三方平台开发者来实现，则无法获取。
 * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
 * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
 * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
 * 
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751299
 * @author Y13
 *
 */
public interface TemplateAutoreplyInfo {

    /**
     * http请求方式: GET（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=ACCESS_TOKEN
     * 
     * 目前该借口返回值不做格式化处理，如果需要处理，请参考https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751299
     * 自行格式化
     * @param accessToken
     * @return
     */
    @GET
    @Path("cgi-bin/message/template/subscribe")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    String getCurrentAutoreplyInfo(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken);
    
    default String getCurrentAutoreplyInfo() {
        return getCurrentAutoreplyInfo(null);
    }
}
