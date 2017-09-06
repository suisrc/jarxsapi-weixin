package com.qq.weixin.pay.param;

import javax.ws.rs.DefaultValue;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.qq.weixin.pay.PayWxConsts;
import com.suisrc.jaxrsapi.core.annotation.Value;

/**
 * 发放裂变红包 请求参数
 * 
 * <xml> 
 *    <sign><![CDATA[E1EE61A91C8E90F299DE6AE075D60A2D]]></sign>
 *    <mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>
 *    <mch_id><![CDATA[1000888888]]></mch_id>
 *    <wxappid><![CDATA[wxcbda96de0b165486]]></wxappid> 
 *    <send_name><![CDATA[send_name]]></send_name> 
 *    <re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid> 
 *    <total_amount><![CDATA[600]]></total_amount> 
 *    <amt_type><![CDATA[ALL_RAND]]></amt_type> 
 *    <total_num><![CDATA[3]]></total_num> 
 *    <wishing><![CDATA[恭喜发财]]></wishing>
 *    <act_name><![CDATA[新年红包]]></act_name> 
 *    <remark><![CDATA[新年红包]]></remark> 
 *    <scene_id><![CDATA[PRODUCT_2]]></scene_id>
 *    <nonce_str><![CDATA[50780e0cca98c8c8e814883e5caa672e]]></nonce_str> 
 *    <risk_info>posttime%3d123123412%26clientversion%3d234134%26mobile%3d122344545%26deviceid%3dIOS</risk_info>
 *    <consume_mch_id><![CDATA[10000097]]></consume_mch_id>
 * </xml>
 * 
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class GroupredpackParam {

	/**
	 * 字段名:随机字符串
	 * 必填:是
	 * 示例值:5K8264ILTKCH16CQ2502SI8ZNMTM67VS
	 * 类型:String(32)
	 * 说明:随机字符串，不长于32位
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="nonce_str")
	@Value(PayWxConsts.AUTO_RANDOM_STR)
	private String nonceStr;

	/**
	 * 字段名:签名
	 * 必填:是
	 * 示例值:C380BEC2BFD727A4B6845133519F3AD6
	 * 类型:String(32)
	 * 说明:详见签名生成算法
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="sign")
	private String sign;

	/**
	 * 字段名:商户订单号
	 * 必填:是
	 * 示例值:10000098201411111234567890
	 * 类型:String(28)
	 * 说明:商户订单号（每个订单号必须唯一）组成： mch_id+yyyymmdd+10位一天内不能重复的数字。接口根据商户订单号支持重入， 如出现超时可再调用。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="mch_billno")
	@Value(PayWxConsts.AUTO_MCH_BILLNO)
	private String mchBillno;

	/**
	 * 字段名:商户号
	 * 必填:是
	 * 示例值:10000098
	 * 类型:String(32)
	 * 说明:微信支付分配的商户号
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="mch_id")
	@Value(PayWxConsts.MCH_ID)
	private String mchId;

	/**
	 * 字段名:公众账号appid
	 * 必填:是
	 * 示例值:wx8888888888888888
	 * 类型:String(32)
	 * 说明:微信分配的公众账号ID（企业号corpid即为此appId），接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的），不能为APP的appid（在open.weixin.qq.com申请的）。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="wxappid")
	@Value(PayWxConsts.APP_ID)
	private String wxappid;

	/**
	 * 字段名:商户名称
	 * 必填:是
	 * 示例值:天虹百货
	 * 类型:String(32)
	 * 说明:红包发送者名称
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="send_name")
	@Value(PayWxConsts.SEND_NAME)
	private String sendName;

	/**
	 * 字段名:用户openid
	 * 必填:是
	 * 示例值:oxTWIuGaIt6gTKsQRLau2M0yL16E
	 * 类型:String(32)
	 * 说明:接收红包的种子用户（首个用户）用户在wxappid下的openid
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="re_openid")
	private String reOpenid;

	/**
	 * 字段名:总金额
	 * 必填:是
	 * 示例值:1000
	 * 类型:int
	 * 说明:红包发放总金额，即一组红包金额总和，包括分享者的红包和裂变的红包，单位分
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="total_amount")
	@DefaultValue("1")
	private Integer totalAmount;

	/**
	 * 字段名:红包发放总人数
	 * 必填:是
	 * 示例值:3
	 * 类型:int
	 * 说明:红包发放总人数，即总共有多少人可以领到该组红包（包括分享者）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="total_num")
	@DefaultValue("1")
	private Integer totalNum;

	/**
	 * 字段名:红包金额设置方式
	 * 必填:是
	 * 示例值:ALL_RAND
	 * 类型:String(32)
	 * 说明:红包金额设置方式ALL_RAND—全部随机,商户指定总金额和红包发放总人数，由微信支付随机计算出各红包金额
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="amt_type")
	@DefaultValue("ALL_RAND")
	private String amtType;

	/**
	 * 字段名:红包祝福语
	 * 必填:是
	 * 示例值:感谢您参加猜灯谜活动，祝您元宵节快乐！
	 * 类型:String(128)
	 * 说明:红包祝福语
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="wishing")
	@DefaultValue("恭喜发财")
	private String wishing;

	/**
	 * 字段名:活动名称
	 * 必填:是
	 * 示例值:猜灯谜抢红包活动
	 * 类型:String(32)
	 * 说明:活动名称
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="act_name")
	@DefaultValue("红包福利")
	private String actName;

	/**
	 * 字段名:备注
	 * 必填:是
	 * 示例值:猜越多得越多，快来抢！
	 * 类型:String(256)
	 * 说明:备注信息
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="remark")
	@DefaultValue("福利多多，恭喜发财")
	private String remark;

	/**
	 * 字段名:场景id
	 * 必填:否
	 * 示例值:PRODUCT_8
	 * 类型:String(32)
	 * 说明:
	 * PRODUCT_1:商品促销
	 * PRODUCT_2:抽奖
	 * PRODUCT_3:虚拟物品兑奖 
	 * PRODUCT_4:企业内部福利
	 * PRODUCT_5:渠道分润
	 * PRODUCT_6:保险回馈
	 * PRODUCT_7:彩票派奖
	 * PRODUCT_8:税务刮奖
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="scene_id")
	private String sceneId;

	/**
	 * 字段名:活动信息
	 * 必填:否
	 * 示例值:posttime%3d123123412%26clientversion%3d234134%26mobile%3d122344545%26deviceid%3dIOS
	 * 类型:String(128)
	 * 说明:
	 * posttime:用户操作的时间戳
	 * mobile:业务系统账号的手机号，国家代码-手机号。不需要+号
	 * deviceid :mac 地址或者设备唯一标识 
	 * clientversion :用户操作的客户端版本把值为非空的信息用key=value进行拼接，再进行urlencode(posttime=xx& mobile =xx&deviceid=xx)
	 */
	@JacksonXmlProperty(localName="risk_info")
	private String riskInfo;

	/**
	 * 字段名:资金授权商户号
	 * 必填:否
	 * 示例值:1222000096
	 * 类型:String(32)
	 * 说明:资金授权商户号服务商替特约商户发放时使用
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="consume_mch_id")
	private String consumeMchId;

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getMchBillno() {
		return mchBillno;
	}

	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getWxappid() {
		return wxappid;
	}

	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getReOpenid() {
		return reOpenid;
	}

	public void setReOpenid(String reOpenid) {
		this.reOpenid = reOpenid;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getAmtType() {
		return amtType;
	}

	public void setAmtType(String amtType) {
		this.amtType = amtType;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getRiskInfo() {
		return riskInfo;
	}

	public void setRiskInfo(String riskInfo) {
		this.riskInfo = riskInfo;
	}

	public String getConsumeMchId() {
		return consumeMchId;
	}

	public void setConsumeMchId(String consumeMchId) {
		this.consumeMchId = consumeMchId;
	}

}
