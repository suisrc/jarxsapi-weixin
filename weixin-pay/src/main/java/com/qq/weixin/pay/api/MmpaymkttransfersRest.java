package com.qq.weixin.pay.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.qq.weixin.pay.handler.CheckSignHandler;
import com.qq.weixin.pay.param.GroupredpackParam;
import com.qq.weixin.pay.param.HbinfoParam;
import com.qq.weixin.pay.param.RedpackParam;
import com.qq.weixin.pay.result.HbinfoResult;
import com.qq.weixin.pay.result.RedpackResult;
import com.suisrc.jaxrsapi.core.Consts;
import com.suisrc.jaxrsapi.core.annotation.RemoteApi;
import com.suisrc.jaxrsapi.core.annotation.Reviser;

/**
 * 微信公众号接口
 * 用户管理
 * 
 * 商户接入微信支付，调用API必须遵循以下规则：

 * 传输方式	为保证交易安全性，采用HTTPS传输
 * 提交方式	采用POST方法提交
 * 数据格式	提交和返回数据都为XML格式，根节点名为xml
 * 字符编码	统一采用UTF-8字符编码
 * 签名算法	MD5，后续会兼容SHA1、SHA256、HMAC等。
 * 签名要求	请求和接收数据均需要校验签名，详细方法请参考安全规范-签名算法
 * 证书要求	调用申请退款、撤销订单接口需要商户证书
 * 判断逻辑	先判断协议字段返回，再判断业务返回，最后判断交易状态
 * 
 * 1、交易金额
 * 交易金额默认为人民币交易，接口中参数支付金额单位为【分】，参数值不能带小数。对账单中的交易金额单位为【元】。
 * 外币交易的支付金额精确到币种的最小单位，参数值不能带小数点。
 * 2、交易类型
 * JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付，统一下单接口trade_type的传参可参考这里
 * MICROPAY--刷卡支付，刷卡支付有单独的支付接口，不调用统一下单接口
 * 3、货币类型
 * 货币类型的取值列表：
 * CNY：人民币
 * 4、时间
 * 标准北京时间，时区为东八区；如果商户的系统时间为非标准北京时间。参数值必须根据商户系统所在时区先换算成标准北京时间， 
 * 例如商户所在地为0时区的伦敦，当地时间为2014年11月11日0时0分0秒，换算成北京时间为2014年11月11日8时0分0秒。
 * 5、时间戳
 * 标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数。注意：部分系统取到的值为毫秒级，需要转换成秒(10位数字)。
 * 6、商户订单号
 * 商户支付的订单号由商户自定义生成，微信支付要求商户订单号保持唯一性（建议根据当前系统时间加随机序列来生成订单号）。
 * 重新发起一笔支付要使用原订单号，避免重复支付；已支付过或已调用关单、撤销（请见后文的API列表）的订单号不能重新发起支付。
 * 
 * 操作指导
 * 
 * 一、开通现金红包权限
 * 在使用现金红包之前，请前往开通现金红包功能。操作路径：【登录微信支付商户平台——>产品中心——>现金红包——>开通】。
 * 说明：在开通时请如实选择你的使用场景，且在红包的发放过程中如实上报你的场景，如有作假，微信支付将有权根据
 * 《微信支付商户平台使用协议》对你的商户号做出处理。
 * 
 * 二、下载API证书
 * 商户调用微信红包接口时，服务器会进行证书验证，请在商户平台下载证书
 * 
 * 三、充值
 * 在发放现金红包之前，请确保你的资金充足。如若不足，请充值。操作路径：【登录商户平台——>账户中心——>资金管理——>充值】
 * 
 * 四、获取openid
 * 目前支持向指定微信用户的openid发放指定金额红包。（获取openid参见微信公众平台开发者文档：网页授权获取用户基本信息）
 * 
 * 五、相关参数设置
 * 和红包相关的参数，你可以在页面上自主设置和更改。操作路径如下：【登录商户平台——>产品中心——>现金红包——>产品设置】
 * （注：“产品设置”操作按钮仅当你开通现金红包功能之后才会出现）。
 * 
 * 说明：
 * ◆ 调用IP地址：设置之后，仅有已设置的IP地址可以调用，其余的IP调用会报错；
 * ◆ 用户领取上限：限制同一openid同一日领取的个数
 * ◆ 防刷等级：防刷是指微信风控针对微信小号、僵尸号、机器号等的拦截，你可以通过更改防刷等级控制防刷的强度。
 * ◆ 同时，你也可以申请更改红包额度。但是需要经过微信支付的审核，审核通过之后才会生效。
 * 
 * @author Y13
 *
 */
@RemoteApi
public interface MmpaymkttransfersRest {
	

	/**
	 * 发放普通红包
	 * 
	 * http请求方式: POST https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack
	 * 
	 * 1.发送频率限制------默认1800/min
	 * 2.发送个数上限------按照默认1800/min算
	 * 3.金额上限------根据传入场景id不同默认上限不同，可以在商户平台产品设置进行设置和申请，最大不大于4999元/个
	 * 4.其他的“量”上的限制还有哪些？------用户当天的领取上限次数,默认是10
	 * 5.如果量上满足不了我们的需求，如何提高各个上限？------金额上限和用户当天领取次数上限可以在商户平台进行设置
	 * 注1：如果你是服务商，希望代你的特约商户发红包，你可以申请获得你特约商户的“现金红包产品授权”。操作路径如下：【登录商户平台-产品中心-特约商户授权产品】（即将上线）
	 * 注2：红包金额大于200时，请求参数scene_id必传，参数说明见下文。
	 * 
	 * @param param 
	 * @return
	 */
	@POST
	@Path("mmpaymkttransfers/sendredpack")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	RedpackResult sendredpack(@Reviser(value=CheckSignHandler.class, master=Consts.FIELD_THIS)RedpackParam param);
	
	/**
	 * 发放裂变红包
	 * 
	 * http请求方式: POST https://api.mch.weixin.qq.com/mmpaymkttransfers/sendgroupredpack
	 * 
	 * 裂变红包：一次可以发放一组红包。首先领取的用户为种子用户，种子用户领取一组红包当中的一个，并可以通过社交分享将剩下的红包给其他用户。裂变红包充分利用了人际传播的优势。
	 * @param param 
	 * @return
	 */
	@POST
	@Path("mmpaymkttransfers/sendgroupredpack")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	RedpackResult sendgroupredpack(@Reviser(value=CheckSignHandler.class, master=Consts.FIELD_THIS)GroupredpackParam param);
	
	/**
	 * 查询红包记录
	 * 
	 * http请求方式: POST https://api.mch.weixin.qq.com/mmpaymkttransfers/gethbinfo
	 * 
	 * 用于商户对已发放的红包进行查询红包的具体信息，可支持普通红包和裂变包。
	 * 
	 * @param param 
	 * @return
	 */
	@POST
	@Path("mmpaymkttransfers/gethbinfo")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	HbinfoResult gethbinfo(@Reviser(value=CheckSignHandler.class, master=Consts.FIELD_THIS)HbinfoParam param);

}
