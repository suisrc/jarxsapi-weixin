package com.qq.weixin.mp.api;

import java.io.InputStream;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.param.kf.KfAccount;
import com.qq.weixin.mp.param.kf.KfAccountList;
import com.qq.weixin.mp.param.kf.msg.KfReplyBaseMessage;
import com.qq.weixin.mp.param.kf.msg.KfReplyCustomTyping;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.Value;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 当用户和公众号产生特定动作的交互时（具体动作列表请见下方说明），微信将会把消息数据推送给开发者，开发者可以在一段时间内（目前修改为48小时）调用客服接口，通过POST一个JSON数据包来发送消息给普通用户。
 * 此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。
 * 目前允许的动作列表如下（公众平台会根据运营情况更新该列表，不同动作触发后，允许的客服接口下发消息条数不同，下发条数达到上限后，会遇到错误返回码，具体请见返回码说明页）：
 * 1、用户发送信息
 * 2、点击自定义菜单（仅有点击推事件、扫码推事件、扫码推事件且弹出“消息接收中”提示框这3种菜单类型是会触发客服接口的）
 * 3、关注公众号
 * 4、扫描二维码
 * 5、支付成功
 * 6、用户维权
 * 为了帮助公众号使用不同的客服身份服务不同的用户群体，客服接口进行了升级，开发者可以管理客服账号，并设置客服账号的头像和昵称。该能力针对所有拥有客服接口权限的公众号开放。
 * 另外，请开发者注意，本接口中所有使用到media_id的地方，现在都可以使用素材管理中的永久素材media_id了。
 * 
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * 客服帐号管理
 * 开发者在根据开发文档的要求完成开发后，使用6.0.2版及以上版本的微信用户在与公众号进行客服沟通，公众号使用不同的客服账号进行回复后，用户可以看到对应的客服头像和昵称。
 * 请注意，必须先在公众平台官网为公众号设置微信号后才能使用该能力。
 * @author Y13
 *
 */
@RemoteApi
public interface WxKfAccountRest {
    
    /**
     * 添加客服帐号
     * 
     * 开发者可以通过本接口为公众号添加客服账号，每个公众号最多添加10个客服账号。该接口调用请求如下：
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN
     * 
     * POST数据示例如下：
     * {
     *      "kf_account" : "test1@test",
     *      "nickname" : "客服1",
     *      "password" : "pswmd5",
     * }
     * 返回说明（正确时的JSON返回结果）：
     * {
     *      "errcode" : 0,
     *      "errmsg" : "ok",
     * }
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * 
     * @param accessToken
     * @param account
     * @return
     */
    @POST
    @Path("customservice/kfaccount/add")
    @Produces(MediaType.APPLICATION_JSON)
    WxErrCode addKfAccount(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken, KfAccount account);
    default
    WxErrCode addKfAccount(KfAccount account) {
        return addKfAccount(null, account);
    }
    
    /**
     * 修改客服帐号
     * 
     * 开发者可以通过本接口为公众号修改客服账号。该接口调用请求如下：
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN
     * 
     * POST数据示例如下：
     * {
     *      "kf_account" : "test1@test",
     *      "nickname" : "客服1",
     *      "password" : "pswmd5",
     * }
     * 返回说明（正确时的JSON返回结果）：
     * {
     *      "errcode" : 0,
     *      "errmsg" : "ok",
     * }
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * 
     * @param accessToken
     * @param account
     * @return
     */
    @POST
    @Path("customservice/kfaccount/update")
    @Produces(MediaType.APPLICATION_JSON)
    WxErrCode updateKfAccount(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken, KfAccount account);
    default
    WxErrCode updateKfAccount(KfAccount account) {
        return updateKfAccount(null, account);
    }
    
    /**
     * 删除客服帐号
     * 
     * 开发者可以通过该接口为公众号删除客服帐号。该接口调用请求如下：
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/customservice/kfaccount/del?access_token=ACCESS_TOKEN
     * 
     * POST数据示例如下：
     * {
     *      "kf_account" : "test1@test",
     *      "nickname" : "客服1",
     *      "password" : "pswmd5",
     * }
     * 返回说明（正确时的JSON返回结果）：
     * {
     *      "errcode" : 0,
     *      "errmsg" : "ok",
     * }
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     * 
     * @param accessToken
     * @param account
     * @return
     */
    @POST
    @Path("customservice/kfaccount/del")
    @Produces(MediaType.APPLICATION_JSON)
    WxErrCode delKfAccount(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken, KfAccount account);
    default
    WxErrCode delKfAccount(KfAccount account) {
        return delKfAccount(null, account);
    }
    
    /**
     * 设置客服帐号的头像
     * 
     * 开发者可调用本接口来上传图片作为客服人员的头像，头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果。该接口调用请求如下： 
     * 
     * http请求方式: POST/FORM
     * http://api.weixin.qq.com/customservice/kfaccount/uploadheadimg?access_token=ACCESS_TOKEN&kf_account=KFACCOUNT
     * 
     * 调用示例：使用curl命令，用FORM表单方式上传一个多媒体文件，curl命令的具体用法请自行了解 返回说明（正确时的JSON返回结果）： 
     * { 
     *      "errcode" : 0,
     *      "errmsg" : "ok", 
     * }
     *  
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     */
    @POST
    @Path("customservice/kfaccount/uploadheadimg")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    WxErrCode uploadKfAccountHeadimg(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken, @QueryParam("kf_account")String kfAccount, @FormParam("img") InputStream inStream);
    default
    WxErrCode uploadKfAccountHeadimg(String kfAccount, InputStream inStream) {
        return uploadKfAccountHeadimg(null, kfAccount, inStream);
    }
    
    /**
     * 获取所有客服账号
     * 
     * 开发者通过本接口，获取公众号中所设置的客服基本信息，包括客服工号、客服昵称、客服登录账号。
     * http请求方式: GET
     * https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN
     * 返回说明（正确时的JSON返回结果）：
     * {
     *     "kf_list": [
     *         {
     *             "kf_account": "test1@test", 
     *             "kf_nick": "ntest1", 
     *             "kf_id": "1001"
     *             "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"
     *         }, 
     *         {
     *             "kf_account": "test2@test", 
     *             "kf_nick": "ntest2", 
     *             "kf_id": "1002"
     *             "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0"
     *         }, 
     *         {
     *             "kf_account": "test3@test", 
     *             "kf_nick": "ntest3", 
     *             "kf_id": "1003"
     *             "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw /0"
     *         }
     *     ]
     * }
     * 
     * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
     */
    @POST
    @Path("cgi-bin/customservice/getkflist")
    @Produces(MediaType.APPLICATION_JSON)
    KfAccountList getKfList(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken);
    default
    KfAccountList getKfList() {
        return getKfList(null);
    }
    
    /**
     * 客服接口-发消息
     * 接口调用请求说明
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
     * 各消息类型所需的JSON数据包如下：
     * 发送文本消息
     * {
     *     "touser":"OPENID",
     *     "msgtype":"text",
     *     "text":
     *     {
     *          "content":"Hello World"
     *     }
     * }
     * 
     * 参数.           是否必须.    说明
     * access_token   是.         调用接口凭证
     * touser         是.         普通用户openid
     * msgtype        是.         消息类型，文本为text，图片为image，语音为voice，视频消息为video，音乐消息为music，
     *                            图文消息（点击跳转到外链）为news，图文消息（点击跳转到图文消息页面）为mpnews，卡券为wxcard，小程序为miniprogrampage
     * content        是.         文本消息内容
     * media_id       是.         发送的图片/语音/视频/图文消息（点击跳转到图文消息页）的媒体ID
     * thumb_media_id 是.         缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     * title          否.         图文消息/视频消息/音乐消息/小程序卡片的标题
     * description    否.         图文消息/视频消息/音乐消息的描述
     * musicurl       是.         音乐链接
     * hqmusicurl     是.         高品质音乐链接，wifi环境优先使用该链接播放音乐
     * url            否.         图文消息被点击后跳转的链接
     * picurl         否.         图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     * appid          是.         小程序的appid，要求小程序的appid需要与公众号有关联关系
     * pagepath       是.         小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
     * 
     * @see com.qq.weixin.mp.param.kf
     */
    @POST
    @Path("cgi-bin/message/custom/send")
    @Produces(MediaType.APPLICATION_JSON)
    WxErrCode sendKfMessage(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken, KfReplyBaseMessage msg);
    
    /**
     * 客服输入状态 开发者可通过调用“客服输入状态”接口，返回客服当前输入状态给用户。 微信客户端效果图如下：
     * 
     * 此接口需要客服消息接口权限。 
     * 1. 如果不满足发送客服消息的触发条件，则无法下发输入状态。 
     * 2. 下发输入状态，需要客服之前30秒内跟用户有过消息交互。 
     * 3. 在输入状态中（持续15s），不可重复下发输入态。 
     * 4. 在输入状态中，如果向用户下发消息，会同时取消输入状态。 接口调用请求说明
     * 
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/custom/typing?access_token=ACCESS_TOKEN
     * 
     * JSON数据包如下：
     * { "touser":"OPENID", "command":"Typing"} 
     * 预期返回：
     * { "errcode":0, "errmsg":"ok"} 参数说明
     * 
     * 参数.          是否必须.  说明 
     * access_token  是.       调用接口凭证 
     * touser        是.       普通用户（openid） 
     * command       是.       "Typing"：对用户下发“正在输入"状态,"CancelTyping"：取消对用户的”正在输入"状态 返回码说明
     * 
     * 参数.    说明 
     * 45072   command字段取值不对 
     * 45080   下发输入状态，需要之前30秒内跟用户有过消息交互 
     * 45081   已经在输入状态，不可重复下发
     * 
     */
    @POST
    @Path("cgi-bin/message/custom/typing")
    @Produces(MediaType.APPLICATION_JSON)
    WxErrCode customTyping(@QueryParam("ACCESS_TOKEN")@Value(MpWxConsts.ACCESS_TOKEN)String accessToken, KfReplyCustomTyping msg);

}
