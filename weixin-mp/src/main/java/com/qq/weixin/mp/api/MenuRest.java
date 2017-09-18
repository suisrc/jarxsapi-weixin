package com.qq.weixin.mp.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.param.menu.ConditionalMenuInfoParam;
import com.qq.weixin.mp.param.menu.ConditionalMenuOpParam;
import com.qq.weixin.mp.param.menu.ConditionalMenuUserParam;
import com.qq.weixin.mp.param.menu.MenuInfoParam;
import com.qq.weixin.mp.result.menu.ConditionalMenuOpResult;
import com.qq.weixin.mp.result.menu.MenuInfoListResult;
import com.qq.weixin.mp.result.menu.MenuInfoResult;
import com.qq.weixin.mp.result.menu.SelfMenuConfigInfoResult;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.Value;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 自定义菜单接口
 * 
 * 自定义菜单能够帮助公众号丰富界面，让用户更好更快地理解公众号的功能。
 * 
 * 请注意： 
 * 1、自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。 
 * 2、一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“...”代替。
 * 3、创建自定义菜单后，菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，
 *    就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果。
 * 
 * 自定义菜单接口可实现多种类型按钮，如下：
 * 1、click：点击推事件用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，
 *    开发者可以通过自定义的key值与用户进行交互；
 * 2、view：跳转URL用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。
 * 3、scancode_push：扫码推事件用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
 * 4、scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，
 *    然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
 * 5、pic_sysphoto：弹出系统拍照发图用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，
 *    随后可能会收到开发者下发的消息。
 * 6、pic_photo_or_album：弹出拍照或者相册发图用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
 * 7、pic_weixin：弹出微信相册发图器用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，
 *    随后可能会收到开发者下发的消息。
 * 8、location_select：弹出地理位置选择器用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，
 *    随后可能会收到开发者下发的消息。
 * 9、media_id：下发消息（除文本消息）用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，永久素材类型可以是图片、音频、视频、图文消息。
 *    请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
 * 10、view_limited：跳转图文消息URL用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。
 *    请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
 *    
 * 请注意，
 * 3到8的所有事件，仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应，开发者也不能正常接收到事件推送。
 * 9和10，是专门给第三方平台旗下未微信认证（具体而言，是资质认证未通过）的订阅号准备的事件类型，它们是没有事件推送的，能力相对受限，其他类型的公众号不必使用。
 * --------------------------------------------------------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------------------------------------------------------
 * 个性化菜单接口
 * 为了帮助公众号实现灵活的业务运营，微信公众平台新增了个性化菜单接口，开发者可以通过该接口，让公众号的不同用户群体看到不一样的自定义菜单。该接口开放给已认证订阅号和已认证服务号。
 * 开发者可以通过以下条件来设置用户看到的菜单：
 * 1、用户标签（开发者的业务需求可以借助用户标签来完成）
 * 2、性别
 * 3、手机操作系统
 * 4、地区（用户在微信客户端设置的地区）
 * 5、语言（用户在微信客户端设置的语言）
 * 个性化菜单接口说明：
 * 1、个性化菜单要求用户的微信客户端版本在iPhone6.2.2，Android 6.2.4以上，暂时不支持其他版本微信
 * 2、菜单的刷新策略是，在用户进入公众号会话页或公众号profile页时，如果发现上一次拉取菜单的请求在5分钟以前，就会拉取一下菜单，如果菜单有更新，就会刷新客户端的菜单。
 *   测试时可以尝试取消关注公众账号后再次关注，则可以看到创建后的效果
 * 3、普通公众号的个性化菜单的新增接口每日限制次数为2000次，删除接口也是2000次，测试个性化菜单匹配结果接口为20000次
 * 4、出于安全考虑，一个公众号的所有个性化菜单，最多只能设置为跳转到3个域名下的链接
 * 5、创建个性化菜单之前必须先创建默认菜单（默认菜单是指使用普通自定义菜单创建接口创建的菜单）。如果删除默认菜单，个性化菜单也会全部删除
 * 6、个性化菜单接口支持用户标签，请开发者注意，当用户身上的标签超过1个时，以最后打上的标签为匹配
 * 个性化菜单匹配规则说明：
 * 个性化菜单的更新是会被覆盖的。
 * 例如公众号先后发布了默认菜单，个性化菜单1，个性化菜单2，个性化菜单3。那么当用户进入公众号页面时，将从个性化菜单3开始匹配，如果个性化菜单3匹配成功，则直接返回个性化菜单3，
 * 否则继续尝试匹配个性化菜单2，直到成功匹配到一个菜单。
 * 根据上述匹配规则，为了避免菜单生效时间的混淆，决定不予提供个性化菜单编辑API，开发者需要更新菜单时，需将完整配置重新发布一轮。
 * @author Y13
 *
 */
@RemoteApi
public interface MenuRest {
    
    /**
     * 自定义菜单创建接口
     * 接口调用请求说明 
     * http请求方式：POST（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN click和view的请求示例
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013
     */
    @POST
    @Path("cgi-bin/menu/create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode createMenu(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, MenuInfoParam param);
    
    default WxErrCode createMenu(MenuInfoParam param) {
        return createMenu(null, param);
    }

    /**
     * 自定义菜单查询接口
     * 
     * 使用接口创建自定义菜单后，开发者还可使用接口查询自定义菜单的结构。另外请注意，在设置了个性化菜单后，使用本自定义菜单查询接口可以获取默认菜单和全部个性化菜单信息。
     * 
     * 接口调用请求说明 
     * http请求方式：GET
     * https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141014
     */
    @GET
    @Path("cgi-bin/menu/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    MenuInfoListResult getMenu(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken);

    default MenuInfoListResult getMenu() {
        return getMenu(null);
    }
    
    /**
     * 自定义菜单删除接口 
     * 
     * 使用接口创建自定义菜单后，开发者还可使用接口删除当前使用的自定义菜单。
     * 另请注意，在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单。 
     * 请求说明
     * http请求方式：GET 
     * https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN 
     * 返回说明
     * 对应创建接口，正确的Json返回结果: {"errcode":0,"errmsg":"ok"}
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141015
     */
    @GET
    @Path("cgi-bin/menu/delete")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode deleteMenu(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken);

    default WxErrCode deleteMenu() {
        return deleteMenu(null);
    }
    
    /**
     * 创建个性化菜单
     * 
     * http请求方式：POST（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=ACCESS_TOKEN
     * 
     * 返回结果 正确时的返回JSON数据包如下，错误时的返回码请见接口返回码说明。 { "menuid":"208379533" }
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296
     */
    @POST
    @Path("cgi-bin/menu/addconditional")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    ConditionalMenuOpResult addConditionalMenu(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, ConditionalMenuInfoParam param);
    
    default ConditionalMenuOpResult addConditionalMenu(ConditionalMenuInfoParam param) {
        return addConditionalMenu(null, param);
    }
    
    /**
     * 删除个性化菜单
     * 
     * http请求方式：POST（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/menu/delconditional?access_token=ACCESS_TOKEN
     * 请求示例
     * {
     *     "menuid":"208379533"
     * 
     * }
     * menuid为菜单id，可以通过自定义菜单查询接口获取。
     * 正确时的返回JSON数据包如下，错误时的返回码请见接口返回码说明。：
     * {"errcode":0,"errmsg":"ok"}
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296
     */
    @POST
    @Path("cgi-bin/menu/delconditional")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    WxErrCode delConditionalMenu(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, ConditionalMenuOpParam param);
    
    default WxErrCode delConditionalMenu(ConditionalMenuOpParam param) {
        return delConditionalMenu(null, param);
    }
    
    /**
     * 查询个性化菜单
     * 使用普通自定义菜单查询接口可以获取默认菜单和全部个性化菜单信息，请见自定义菜单查询接口的说明。
     */
    default MenuInfoListResult getConditionalMenu() {
        return getMenu();
    }
    
    /**
     * 删除所有菜单
     * 使用普通自定义菜单删除接口可以删除所有自定义菜单（包括默认菜单和全部个性化菜单），请见自定义菜单删除接口的说明。
     */
    default WxErrCode delAllMenu() {
        return deleteMenu();
    }
    
    /**
     * 测试个性化菜单匹配结果
     * http请求方式：POST（请使用https协议）
     * https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN
     * 请求示例
     * {
     *     "user_id":"weixin"
     * }
     * user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296
     */
    MenuInfoResult tryMatchConditionalMenu(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken, ConditionalMenuUserParam param);

    default MenuInfoResult tryMatchConditionalMenu(ConditionalMenuUserParam param) {
        return tryMatchConditionalMenu(null, param);
    }
    
    /**
     * 获取自定义菜单配置接口
     * 
     * 本接口将会提供公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置。
     * 请注意：
     * 1、第三方平台开发者可以通过本接口，在旗下公众号将业务授权给你后，立即通过本接口检测公众号的自定义菜单配置，并通过接口再次给公众号设置好自动回复规则，以提升公众号运营者的业务体验。
     * 2、本接口与自定义菜单查询接口的不同之处在于，本接口无论公众号的接口是如何设置的，都能查询到接口，而自定义菜单查询接口则仅能查询到使用API设置的菜单配置。
     * 3、认证/未认证的服务号/订阅号，以及接口测试号，均拥有该接口权限。
     * 4、从第三方平台的公众号登录授权机制上来说，该接口从属于消息与菜单权限集。
     * 5、本接口中返回的图片/语音/视频为临时素材（临时素材每次获取都不同，3天内有效，通过素材管理-获取临时素材接口来获取这些素材），本接口返回的图文消息为永久素材素材（通过素材管理-获取永久素材接口来获取这些素材）。
     * 
     * 接口调用请求说明
     * http请求方式: GET（请使用https协议）https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN
     * 
     * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1434698695
     */
    SelfMenuConfigInfoResult getCurrentSelfMenuInfo(@QueryParam("access_token")@Value(MpWxConsts.ACCESS_TOKEN) String accessToken);

    default SelfMenuConfigInfoResult getCurrentSelfMenuInfo() {
        return getCurrentSelfMenuInfo(null);
    }
}
