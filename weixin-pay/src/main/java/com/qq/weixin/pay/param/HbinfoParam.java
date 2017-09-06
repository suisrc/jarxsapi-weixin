package com.qq.weixin.pay.param;

import javax.ws.rs.DefaultValue;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.qq.weixin.pay.PayWxConsts;
import com.suisrc.jaxrsapi.core.annotation.Value;

/**
 * 查询红包记录 请求参数
 * <xml>
 * <sign><![CDATA[E1EE61A91C8E90F299DE6AE075D60A2D]]></sign>
 * <mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>
 * <mch_id><![CDATA[10000097]]></mch_id>
 * <appid><![CDATA[wxe062425f740c30d8]]></appid>
 * <bill_type><![CDATA[MCHT]]></ bill_type> 
 * <nonce_str><![CDATA[50780e0cca98c8c8e814883e5caa672e]]></nonce_str>
 * </xml>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class HbinfoParam {

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
	 * 说明:商户发放红包的商户订单号
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="mch_billno")
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
	 * 字段名:Appid
	 * 必填:是
	 * 示例值:wxe062425f740d30d8
	 * 类型:String(32)
	 * 说明:微信分配的公众账号ID（企业号corpid即为此appId），接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的），不能为APP的appid（在open.weixin.qq.com申请的）。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="appid")
	@Value(PayWxConsts.APP_ID)
	private String appid;

	/**
	 * 字段名:订单类型
	 * 必填:是
	 * 示例值:MCHT
	 * 类型:String(32)
	 * 说明:MCHT:通过商户订单号获取红包信息。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="bill_type")
	@DefaultValue("MCHT")
	private String billType;
	

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

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

}
