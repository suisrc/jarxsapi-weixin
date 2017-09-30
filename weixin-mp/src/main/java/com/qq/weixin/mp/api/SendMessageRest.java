package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.param.msg.MassDeleteParam;
import com.qq.weixin.mp.param.msg.MassGetParam;
import com.qq.weixin.mp.param.msg.MassPreviewParam;
import com.qq.weixin.mp.param.msg.MassSendAllParam;
import com.qq.weixin.mp.param.msg.MassSpeedGetParam;
import com.qq.weixin.mp.param.msg.MassSpeedSetParam;
import com.qq.weixin.mp.param.msg.UploadnewsParam;
import com.qq.weixin.mp.result.FileMedia;
import com.qq.weixin.mp.result.msg.MassGetResult;
import com.qq.weixin.mp.result.msg.MassPreviewResult;
import com.qq.weixin.mp.result.msg.MassSendAllResult;
import com.qq.weixin.mp.result.msg.UploadimgResult;
import com.qq.weixin.mp.result.msg.UploadnewsResult;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.Value;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 发送消息-群发接口和原创校验
 * 
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1481187827_i0l21
 * @author Y13
 * 
 * 在公众平台网站上，为订阅号提供了每天一条的群发权限，为服务号提供每月（自然月）4条的群发权限。而对于某些具备开发能力的公众号运营者，可以通过高级群发接口，实现更灵活的群发能力。
 * 
 * 请注意：
 * 
 * 1、对于认证订阅号，群发接口每天可成功调用1次，此次群发可选择发送给全部用户或某个标签；
 * 2、对于认证服务号虽然开发者使用高级群发接口的每日调用限制为100次，但是用户每月只能接收4条，无论在公众平台网站上，还是使用接口群发，用户每月只能接收4条群发消息，多于4条的群发将对该用户发送失败；
 * 3、开发者可以使用预览接口校对消息样式和排版，通过预览接口可发送编辑好的消息给指定用户校验效果；
 * 4、群发过程中，微信后台会自动进行图文消息原创校验，请提前设置好相关参数(send_ignore等)；
 * 5、开发者可以主动设置 clientmsgid 来避免重复推送。
 * 6、群发接口每分钟限制请求60次，超过限制的请求会被拒绝。
 * 7、图文消息正文中插入自己帐号和其他公众号已群发文章链接的能力。
 * 
 * 群发图文消息的过程如下：
 * 
 * 1、首先，预先将图文消息中需要用到的图片，使用上传图文消息内图片接口，上传成功并获得图片 URL；
 * 2、上传图文消息素材，需要用到图片时，请使用上一步获取的图片 URL；
 * 3、使用对用户标签的群发，或对 OpenID 列表的群发，将图文消息群发出去，群发时微信会进行原创校验，并返回群发操作结果；
 * 4、在上述过程中，如果需要，还可以预览图文消息、查询群发状态，或删除已群发的消息等。
 * 
 * 群发图片、文本等其他消息类型的过程如下：
 * 
 * 1、如果是群发文本消息，则直接根据下面的接口说明进行群发即可；
 * 2、如果是群发图片、视频等消息，则需要预先通过素材管理接口准备好 mediaID。
 * 
 * 关于群发时使用is_to_all为true使其进入公众号在微信客户端的历史消息列表：
 * 
 * 1、使用is_to_all为true且成功群发，会使得此次群发进入历史消息列表。
 * 2、为防止异常，认证订阅号在一天内，只能使用is_to_all为true进行群发一次，或者在公众平台官网群发（不管本次群发是对全体还是对某个分组）一次。以避免一天内有2条群发进入历史消息列表。
 * 3、类似地，服务号在一个月内，使用is_to_all为true群发的次数，加上公众平台官网群发（不管本次群发是对全体还是对某个分组）的次数，最多只能是4次。
 * 4、设置is_to_all为false时是可以多次群发的，但每个用户只会收到最多4条，且这些群发不会进入历史消息列表。
 * 
 * 另外，请开发者注意，本接口中所有使用到media_id的地方，现在都可以使用素材管理中的永久素材media_id了。
 * 请但注意，使用同一个素材群发出去的链接是一样的，这意味着，删除某一次群发，会导致整个链接失效。
 * 
 * ---------------------------------------------------------------------------------------------------------------------------------------------------
 * 图文消息群发前将进行原创校验
 * 一、群发接口新增原创校验流程
 * 开发者调用群发接口进行图文消息的群发时，微信会将开发者准备群发的文章，与公众平台原创库中的文章进行比较，校验结果分为以下几种：
 * 1. 当前准备群发的文章，未命中原创库中的文章，则可以群发。
 * 2. 当前准备群发的文章，已命中原创库中的文章，则：
 *     2.1 若原创作者允许转载该文章，则可以进行群发。群发时，会自动替换成原文的样式，且会自动将文章注明为转载并显示来源。
 *         若希望修改原文内容或样式，或群发时不显示转载来源，可自行与原创公众号作者联系并获得授权之后再进行群发。
 *     2.2 若原创作者禁止转载该文章，则不能进行群发。
 *         若希望转载该篇文章，可自行与原创公众号作者联系并获得授权之后再进行群发。
 * 
 * 二、群发接口新增 send_ignore_reprint 参数
 * 群发接口新增 send_ignore_reprint 参数，开发者可以对群发接口的 send_ignore_reprint 参数进行设置，指定待群发的文章被判定为转载时，是否继续群发。
 * 当 send_ignore_reprint 参数设置为1时，文章被判定为转载时，且原创文允许转载时，将继续进行群发操作。
 * 当 send_ignore_reprint 参数设置为0时，文章被判定为转载时，将停止群发操作。
 * send_ignore_reprint 默认为0。
 * 群发操作的相关返回码，可以参考全局返回码说明文档。
 * 
 * 三、使用 clientmsgid 参数，避免重复推送
 * 群发接口新增 clientmsgid 参数，开发者调用群发接口时可以主动设置 clientmsgid 参数，避免重复推送。
 * 群发时，微信后台将对 24 小时内的群发记录进行检查，如果该 clientmsgid 已经存在一条群发记录，则会拒绝本次群发请求，返回已存在的群发msgid，开发者可以调用“查询群发消息发送状态”接口查看该条群发的状态。
 * 开发者侧群发msgid，长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid
 * 
 * 新增返回码
 * 返回码 结果
 * 45065       相同 clientmsgid 已存在群发记录，返回数据中带有已存在的群发任务的 msgid
 * 45066       相同 clientmsgid 重试速度过快，请间隔1分钟重试
 * 45067       clientmsgid 长度超过限制
 * 
 * clientmsgid 冲突时的返回示例：
 * {
 *    "errcode":45065,
 *    "errmsg":"clientmsgid exist",
 *    "msg_id":123456
 * }
 * 
 * 事件推送群发结果
 * MASSSENDJOBFINISH
 * @see com.suisrc.weixin.mp.msg.event.MasssendjobfinishEvent
 */
@RemoteApi
public interface SendMessageRest {
    
    /**
     * 上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】
     * 
     * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN
     * 调用示例（使用curl命令，用FORM表单方式上传一个图片）：
     * curl -F media=@test.jpg "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN"
     * 
     * 参数说明
     * 
     * 参数.            是否必须.    说明
     * access_token    是.         调用接口凭证
     * media           是.         form-data中媒体文件标识，有filename、filelength、content-type等信息
     * 
     * 返回说明 正常情况下的返回结果为：
     * {
     *     "url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"
     * }
     */
    @POST
    @Path("cgi-bin/media/uploadimg")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    UploadimgResult uploadimg(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, @MultipartForm FileMedia media);
    
    default UploadimgResult uploadimg(FileMedia media) {
        return uploadimg(null, media);
    }
    
    /**
     * 上传图文消息素材【订阅号与服务号认证后均可用】
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN
     * 
     * POST数据示例如下：
     * {
     *    "articles": [
     *          {
     *              "thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
     *              "author":"xxx",
     *              "title":"Happy Day",
     *              "content_source_url":"www.qq.com",
     *              "content":"content",
     *              "digest":"digest",
     *              "show_cover_pic":1
     *          },
     *          {
     *              "thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
     *              "author":"xxx",
     *              "title":"Happy Day",
     *              "content_source_url":"www.qq.com",
     *              "content":"content",
     *              "digest":"digest",
     *              "show_cover_pic":0
     *          }
     *    ]
     * }
     * 
     * 如果需要在群发图文中插入小程序，则在调用上传图文消息素材接口时，需在content字段中添加小程序跳转链接，有以下三种样式的可供选择。
     * 
     * 小程序卡片跳转小程序，代码示例：
     * <mp-miniprogram data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index/index" data-miniprogram-title="小程序示例" data-progarm-imageurl="http://mmbizqbic.cn/demo.jpg"></mp-miniprogram>
     * 文字跳转小程序，代码示例：
     * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href="">点击文字跳转小程序</a></p>
     * 图片跳转小程序，代码示例：
     * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href=""><img data-src="http://mmbiz.qpic.cn/mmbiz_jpg/demo/0?wx_fmt=jpg"></a></p>
     * 
     * 返回数据示例（正确时的JSON返回结果）：
     * {
     *    "type":"news",
     *    "media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ",
     *    "created_at":1391857799
     * }
     * 
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * 
     */
    @POST
    @Path("cgi-bin/media/uploadnews")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    UploadnewsResult uploadnews(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, UploadnewsParam data);
    
    default UploadnewsResult uploadnews(UploadnewsParam data) {
        return uploadnews(null, data);
    }
    
    /**
     * 根据标签进行群发【订阅号与服务号认证后均可用】
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN
     * 
     * POST数据示例如下：
     * 图文消息（注意图文消息的media_id需要通过上述方法来得到）：
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "mpnews":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"mpnews",
     *     "send_ignore_reprint":0
     * }
     * 文本：
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "text":{
     *       "content":"CONTENT"
     *    },
     *     "msgtype":"text"
     * }
     * 语音/音频（注意此处media_id需通过基础支持中的上传下载多媒体文件来得到）：
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "voice":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"voice"
     * }
     * 图片（注意此处media_id需通过基础支持中的上传下载多媒体文件来得到）：
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "image":{
     *       "media_id":"123dsdajkasd231jhksad"
     *    },
     *     "msgtype":"image"
     * }
     * 视频
     * 请注意，此处视频的media_id需通过POST请求到下述接口特别地得到：https://api.weixin.qq.com/cgi-bin/media/uploadvideo?access_token=ACCESS_TOKEN 
     * POST数据如下（此处media_id需通过基础支持中的上传下载多媒体文件来得到）：
     * {
     *   "media_id": "rF4UdIMfYK3efUfyoddYRMU50zMiRmmt_l0kszupYh_SzrcW5Gaheq05p_lHuOTQ",
     *   "title": "TITLE",
     *   "description": "Description"
     * }
     * 返回将为:
     * {
     *   "type":"video",
     *   "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc",
     *   "created_at":1398848981
     * }
     * 然后，POST下述数据（将media_id改为上一步中得到的media_id），即可进行发送
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":2
     *    },
     *    "mpvideo":{
     *       "media_id":"IhdaAQXuvJtGzwwc0abfXnzeezfO0NgPK6AQYShD8RQYMTtfzbLdBIQkQziv2XJc"
     *    },
     *     "msgtype":"mpvideo"
     * }
     * 卡券消息（注意图文消息的media_id需要通过上述方法来得到）：
     * {
     *    "filter":{
     *       "is_to_all":false,
     *       "tag_id":"2"
     *    },
     *   "wxcard":{              
     *            "card_id":"123dsdajkasd231jhksad"         
     *             },
     *    "msgtype":"wxcard"
     * }
     * 返回说明
     * 返回数据示例（正确时的JSON返回结果）：
     * {
     *    "errcode":0,
     *    "errmsg":"send job submission success",
     *    "msg_id":34182, 
     *    "msg_data_id": 206227730
     * }
     * 请注意：在返回成功时，意味着群发任务提交成功，并不意味着此时群发已经结束，所以，仍有可能在后续的发送过程中出现异常情况导致用户未收到消息，如消息有时会进行审核、服务器不稳定等。
     * 此外，群发任务一般需要较长的时间才能全部发送完毕，请耐心等待。
     * 
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     */
    @POST
    @Path("cgi-bin/message/mass/sendall")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    MassSendAllResult massSendAll(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MassSendAllParam mass);
    
    default MassSendAllResult massSendAll(MassSendAllParam mass) {
        return massSendAll(null, mass);
    }
    
    /**
     * 删除群发【订阅号与服务号认证后均可用】
     * 
     * 群发之后，随时可以通过该接口删除群发。
     * 
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN
     * 
     * POST数据说明
     * POST数据示例如下：
     * {
     *    "msg_id":30124,
     *    "article_idx":2
     * }
     * 
     * 参数.      是否必须.     说明
     * msg_id      是.        发送出去的消息ID
     * article_idx 否.        要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * 
     * 请注意：
     * 1、只有已经发送成功的消息才能删除
     * 2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
     * 3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
     * 4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
     * 
     * 返回说明
     * {
     *    "errcode":0,
     *    "errmsg":"ok"
     * }
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     */
    @POST
    @Path("cgi-bin/message/mass/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode massDelete(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MassDeleteParam mass);
    
    default WxErrCode massDelete(MassDeleteParam mass) {
        return massDelete(null, mass);
    }
    
    /**
     * 预览接口【订阅号与服务号认证后均可用】
     * 
     * 开发者可通过该接口发送消息给指定用户，在手机端查看消息的样式和排版。为了满足第三方平台开发者的需求，在保留对openID预览能力的同时，增加了对指定微信号发送预览的能力，但该能力每日调用次数有限制（100次），请勿滥用。
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN
     * POST数据说明
     * POST数据示例如下：
     * 图文消息（其中media_id与根据分组群发中的media_id相同）：
     * {
     *    "touser":"OPENID", 
     *    "mpnews":{              
     *             "media_id":"123dsdajkasd231jhksad"               
     *              },
     *    "msgtype":"mpnews" 
     * }
     * 文本：
     * {     
     *     "touser":"OPENID",
     *     "text":{           
     *            "content":"CONTENT"            
     *            },     
     *     "msgtype":"text"
     * }
     * 语音（其中media_id与根据分组群发中的media_id相同）：
     * {
     *     "touser":"OPENID",
     *     "voice":{              
     *             "media_id":"123dsdajkasd231jhksad"
     *             },
     *     "msgtype":"voice" 
     * }
     * 图片（其中media_id与根据分组群发中的media_id相同） image
     * 视频（其中media_id与根据分组群发中的media_id相同） mpvideo
     * 
     * 卡券：
     * { "touser":"OPENID", 
     *   "wxcard":{              
     *            "card_id":"123dsdajkasd231jhksad",
     *             "card_ext": "{\"code\":\"\",\"openid\":\"\",\"timestamp\":\"1402057159\",\"signature\":\"017bb17407c8e0058a66d72dcc61632b70f511ad\"}"
     *             }, 
     *   "msgtype":"wxcard" 
     * }
     * 
     * 请注意，上述JSON数据中的touser字段都可以改为towxname，这样就可以针对微信号进行预览（而非openID），towxname和touser同时赋值时，以towxname优先。修改后JSON数据如下（以图文消息为例）： 图文消息：
     * 
     * {
     *    "towxname":"示例的微信号", 
     *    "mpnews":{              
     *             "media_id":"123dsdajkasd231jhksad"
     *              },
     *    "msgtype":"mpnews" 
     * }
     * 
     * 参数.       说明
     * touser     接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * msgtype    群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * media_id   用于群发的消息的media_id
     * content    发送文本消息时文本的内容
     * 
     * 返回说明
     * 返回数据示例（正确时的JSON返回结果）：
     * {
     *    "errcode":0,
     *    "errmsg":"preview success",
     *    "msg_id":34182
     * }
     * 
     * 参数.    说明
     * errcode 错误码
     * errmsg  错误信息
     * msg_id  消息ID
     */
    @POST
    @Path("cgi-bin/message/mass/preview")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    MassPreviewResult massPreview(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MassPreviewParam mass);

    default MassPreviewResult massPreview(MassPreviewParam mass) {
        return massPreview(null, mass);
    }
    
    /**
     * 查询群发消息发送状态【订阅号与服务号认证后均可用】
     * 
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN
     * POST数据说明
     * POST数据示例如下：
     * {
     *    "msg_id": "201053012"
     * }
     * 参数  说明
     * msg_id  群发消息后返回的消息id
     * 返回说明
     * 返回数据示例（正确时的JSON返回结果）：
     * {
     *      "msg_id":201053012,
     *      "msg_status":"SEND_SUCCESS"
     * }
     * 参数.        说明
     * msg_id      群发消息后返回的消息id
     * msg_status  消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     * 
     */
    @POST
    @Path("cgi-bin/message/mass/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    MassGetResult massGet(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MassGetParam mass);

    default MassGetResult massGet(MassGetParam mass) {
        return massGet(null, mass);
    }
    
    /**
     * 控制群发速度
     * 
     * 开发者可以使用限速接口来控制群发速度。
     * 获取群发速度
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/mass/speed/get?access_token=ACCESS_TOKEN
     * 返回说明 正常情况下的返回结果为：
     * {
     *     "speed":3,
     *     "realspeed":15
     * }
     * 参数说明
     * 参数.       是否必须.    说明
     * speed       是.       群发速度的级别
     * realspeed   是.       群发速度的真实值,单位：万/分钟
     * 
     */
    @POST
    @Path("cgi-bin/message/mass/speed/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode massSpeedGet(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MassSpeedGetParam mass);

    default WxErrCode massSpeedGet(MassSpeedGetParam mass) {
        return massSpeedGet(null, mass);
    }
    
    /**
     * 设置群发速度
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/mass/speed/set?access_token=ACCESS_TOKEN
     * 请求示例
     * {
     *     "speed":1
     * }
     * 参数说明
     * 参数.  是否必须 .    说明
     * speed   是.        群发速度的级别群发速度的级别，是一个0到4的整数，数字越大表示群发速度越慢
     * 
     * speed 与 realspeed 的关系如下：
     * speed   realspeed
     * 0       80w/分钟
     * 1       60w/分钟
     * 2       45w/分钟
     * 3       30w/分钟
     * 4       10w/分钟
     * 
     * 返回码说明
     * 返回码 说明
     * 45083   设置的 speed 参数不在0到4的范围内
     * 45084   没有设置 speed 参数
     * 
     */
    @POST
    @Path("cgi-bin/message/mass/speed/set")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode massSpeedSet(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MassSpeedSetParam mass);

    default WxErrCode massSpeedSet(MassSpeedSetParam mass) {
        return massSpeedSet(null, mass);
    }
}
