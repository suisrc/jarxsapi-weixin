package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.param.template.ApiAddTemplateParam;
import com.qq.weixin.mp.param.template.DelPrivateTemplateParam;
import com.qq.weixin.mp.param.template.IndustrySetParam;
import com.qq.weixin.mp.param.template.TemplateMessageParam;
import com.qq.weixin.mp.result.template.ApiAddTemplateResult;
import com.qq.weixin.mp.result.template.AppPrivateTemplateListResult;
import com.qq.weixin.mp.result.template.IndustryGetResult;
import com.qq.weixin.mp.result.template.TemplateMessageResult;
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
 * 事件推送
 * TEMPLATESENDJOBFINISH
 * 
 * @see com.suisrc.weixin.mp.msg.event.TemplatesendjobfinishEvent
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
     * 行业代码查询详见： @see com.qq.weixin.mp.common.IndustryCode
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
    
    /**
     * 获得模板ID
     * 
     * 从行业模板库选择模板到帐号后台，获得模板ID的过程可在微信公众平台后台完成。为方便第三方开发者，提供通过接口调用的方式来获取模板ID，具体如下：
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
     * 
     * POST数据说明
     * POST数据示例如下：
     *       {
     *            "template_id_short":"TM00015"
     *        }
     * 参数说明
     * 参数.              是否必须.    说明
     * access_token       是.        接口调用凭证
     * template_id_short  是.        模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     * 
     * 返回码说明
     * 在调用模板消息接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
     *     {
     *            "errcode":0,
     *            "errmsg":"ok",
     *            "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"
     *     }
     */
    @POST
    @Path("cgi-bin/template/api_add_template")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ApiAddTemplateResult getApiAddTemplate(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, ApiAddTemplateParam param);
    
    default ApiAddTemplateResult getApiAddTemplate(ApiAddTemplateParam param) {
        return getApiAddTemplate(null, param);
    }
    
    /**
     * 获取模板列表
     * 
     * 获取已添加至帐号下所有模板列表，可在微信公众平台后台中查看模板列表信息。为方便第三方开发者，提供通过接口调用的方式来获取帐号下所有模板信息，具体如下:
     * 接口调用请求说明
     * http请求方式：GET
     * https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
     * 
     * 参数说明
     * 参数.           是否必须.    说明
     * access_token    是.        接口调用凭证
     * 返回说明
     * 正确调用后的返回示例：
     * {   
     *  "template_list": [{
     *       "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
     *       "title": "领取奖金提醒",
     *       "primary_industry": "IT科技",
     *       "deputy_industry": "互联网|电子商务",
     *       "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
     *       "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
     *    }]
     * }
     */
    @GET
    @Path("cgi-bin/template/get_all_private_template")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    AppPrivateTemplateListResult getAppPrivateTemplate(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken);
    
    default AppPrivateTemplateListResult getAppPrivateTemplate() {
        return getAppPrivateTemplate(null);
    }
    
    /**
     * 删除模板
     * 
     * 删除模板可在微信公众平台后台完成，为方便第三方开发者，提供通过接口调用的方式来删除某帐号下的模板，具体如下：
     * 接口调用请求说明
     * http请求方式：POST
     * https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN
     * POST数据说明如下：
     *  {
     *      "template_id" : "Dyvp3-Ff0cnail_CDSzk1fIc6-9lOkxsQE7exTJbwUE"
     *  }
     * 参数说明
     * 参数.          是否必须.    说明
     * access_token    是.       接口调用凭证
     * template_id     是.       公众帐号下模板消息ID
     * 返回说明
     * 在调用接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
     * {
     *    "errcode" : 0,
     *    "errmsg" : "ok"
     * }
     */
    @POST
    @Path("cgi-bin/template/del_private_template")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode delPrivateTemplate(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, DelPrivateTemplateParam param);
    
    default WxErrCode delPrivateTemplate(DelPrivateTemplateParam param) {
        return delPrivateTemplate(null, param);
    }
    
    /**
     * 
     * 发送模板消息
     * 
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
     * POST数据说明
     * POST数据示例如下：
     *       {
     *            "touser":"OPENID",
     *            "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
     *            "url":"http://weixin.qq.com/download",  
     *            "miniprogram":{
     *              "appid":"xiaochengxuappid12345",
     *              "pagepath":"index?foo=bar"
     *            },          
     *            "data":{
     *                    "first": {
     *                        "value":"恭喜你购买成功！",
     *                        "color":"#173177"
     *                    },
     *                    "keynote1":{
     *                        "value":"巧克力",
     *                        "color":"#173177"
     *                    },
     *                    "keynote2": {
     *                        "value":"39.8元",
     *                        "color":"#173177"
     *                    },
     *                    "keynote3": {
     *                        "value":"2014年9月22日",
     *                        "color":"#173177"
     *                    },
     *                    "remark":{
     *                        "value":"欢迎再次购买！",
     *                        "color":"#173177"
     *                    }
     *            }
     *        }
     *        
     * 返回码说明
     * 在调用模板消息接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
     *     {
     *            "errcode":0,
     *            "errmsg":"ok",
     *            "msgid":200228332
     *     }
     */
    @POST
    @Path("cgi-bin/message/template/send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    TemplateMessageResult sendTemplateMessage(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, TemplateMessageParam param);
    
    default TemplateMessageResult sendTemplateMessage(TemplateMessageParam param) {
        return sendTemplateMessage(null, param);
    }
}
