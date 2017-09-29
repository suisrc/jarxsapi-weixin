package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.param.template.IndustrySetParam;
import com.qq.weixin.mp.result.template.IndustryGetResult;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.Value;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 模板消息接口
 * 
 * 模板消息仅用于公众号向用户发送重要的服务通知，只能用于符合其要求的服务场景中，如信用卡刷卡通知，商品购买成功通知等。不支持广告等营销类消息以及其它所有可能对用户造成骚扰的消息。
 * 
 * 关于使用规则，请注意：
 * 1、所有服务号都可以在功能->添加功能插件处看到申请模板消息功能的入口，但只有认证后的服务号才可以申请模板消息的使用权限并获得该权限；
 * 2、需要选择公众账号服务所处的2个行业，每月可更改1次所选行业；
 * 3、在所选择行业的模板库中选用已有的模板进行调用；
 * 4、每个账号可以同时使用25个模板。
 * 5、当前每个账号的模板消息的日调用上限为10万次，单个模板没有特殊限制。【2014年11月18日将接口调用频率从默认的日1万次提升为日10万次，可在MP登录后的开发者中心查看】。
 *   当账号粉丝数超过10W/100W/1000W时，模板消息的日调用上限会相应提升，以公众号MP后台开发者中心页面中标明的数字为准。
 *   
 * 关于接口文档，请注意：
 * 1、模板消息调用时主要需要模板ID和模板中各参数的赋值内容；
 * 2、模板中参数内容必须以".DATA"结尾，否则视为保留字；
 * 3、模板保留符号"{{ }}"
 * 
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277
 * 
 * @author Y13
 *
 */
@RemoteApi
public interface TemplateMessageRest {

    /**
     * 设置所属行业
     * 
     * 设置行业可在微信公众平台后台完成，每月可修改行业1次，帐号仅可使用所属行业中相关的模板，为方便第三方开发者，提供通过接口调用的方式来修改账号所属行业，具体如下：
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN
     * POST数据说明
     * POST数据示例如下：
     *       {
     *           "industry_id1":"1",
     *           "industry_id2":"4"
     *        }
     * 参数说明
     * 参数.            是否必须.    说明
     * access_token    是.         接口调用凭证
     * industry_id1    是.         公众号模板消息所属行业编号
     * industry_id2    是.         公众号模板消息所属行业编号
     * 
     */
    @POST
    @Path("cgi-bin/template/api_set_industry")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode setIndustry(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, IndustrySetParam data);
    
    default WxErrCode setIndustry(IndustrySetParam data) {
        return setIndustry(null, data);
    }
    
    /**
     * 获取设置的行业信息
     * 
     * 获取帐号设置的行业信息。可登录微信公众平台，在公众号后台中查看行业信息。为方便第三方开发者，提供通过接口调用的方式来获取帐号所设置的行业信息，具体如下:
     * 接口调用请求说明
     * http请求方式：GET
     * https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN
     * 参数说明
     * 参数.          是否必须 .   说明
     * access_token    是.       接口调用凭证
     * 返回说明
     * 正确调用后的返回示例：
     * {
     * "primary_industry":{"first_class":"运输与仓储","second_class":"快递"},
     * "secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"}
     * }
     * 返回参数说明
     * 参数  是否必填    说明
     * access_token    是   接口调用凭证
     * primary_industry    是   帐号设置的主营行业
     * secondary_industry  是   帐号设置的副营行业
     */
    @GET
    @Path("cgi-bin/template/get_industry")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    IndustryGetResult getIndustry(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken);
    
    default IndustryGetResult getIndustry() {
        return getIndustry(null);
    }
}
