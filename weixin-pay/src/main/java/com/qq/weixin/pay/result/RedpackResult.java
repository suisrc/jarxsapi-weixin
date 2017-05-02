package com.qq.weixin.pay.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 发放普通红包 返回参数
 * 
 * 成功示例：
 * <xml>
 * <return_code><![CDATA[SUCCESS]]></return_code>
 * <return_msg><![CDATA[发放成功.]]></return_msg>
 * <result_code><![CDATA[SUCCESS]]></result_code>
 * <err_code><![CDATA[0]]></err_code>
 * <err_code_des><![CDATA[发放成功.]]></err_code_des>
 * <mch_billno><![CDATA[0010010404201411170000046545]]></mch_billno>
 * <mch_id>10010404</mch_id>
 * <wxappid><![CDATA[wx6fa7e3bab7e15415]]></wxappid>
 * <re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid>
 * <total_amount>1</total_amount>
 * </xml>
 * 失败示例：
 * <xml>
 * <return_code><![CDATA[FAIL]]></return_code>
 * <return_msg><![CDATA[系统繁忙,请稍后再试.]]></return_msg>
 * <result_code><![CDATA[FAIL]]></result_code>
 * <err_code><![CDATA[268458547]]></err_code>
 * <err_code_des><![CDATA[系统繁忙,请稍后再试.]]></err_code_des>
 * <mch_billno><![CDATA[0010010404201411170000046542]]></mch_billno>
 * <mch_id>10010404</mch_id>
 * <wxappid><![CDATA[wx6fa7e3bab7e15415]]></wxappid>
 * <re_openid><![CDATA[onqOjjmM1tad-3ROpncN-yUfa6uI]]></re_openid>
 * <total_amount>1</total_amount>
 * </xml>
 * 
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class RedpackResult {

	/**
	 * 字段名:返回状态码
	 * 必填:是
	 * 示例值:SUCCESS
	 * 类型:String(16)
	 * 说明:SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="return_code")
	private String returnCode;

	/**
	 * 字段名:返回信息
	 * 必填:否
	 * 示例值:签名失败
	 * 类型:String(128)
	 * 说明:返回信息，如非空，为错误原因签名失败参数格式校验错误
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="return_msg")
	private String returnMsg;
//-------------------以下字段在return_code为SUCCESS的时候有返回----------------------//
	/**
	 * 字段名:签名
	 * 必填:是
	 * 示例值:C380BEC2BFD727A4B6845133519F3AD6
	 * 类型:String(32)
	 * 说明:生成签名方式详见签名生成算法
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="sign")
	private String sign;

	/**
	 * 字段名:业务结果
	 * 必填:是
	 * 示例值:SUCCESS
	 * 类型:String(16)
	 * 说明:SUCCESS/FAIL
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="result_code")
	private String resultCode;

	/**
	 * 字段名:错误代码
	 * 必填:否
	 * 示例值:SYSTEMERROR
	 * 类型:String(32)
	 * 说明:错误码信息
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="err_code")
	private String errCode;

	/**
	 * 字段名:错误代码描述
	 * 必填:否
	 * 示例值:系统错误
	 * 类型:String(128)
	 * 说明:结果信息描述
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="err_code_des")
	private String errCodeDes;
	
//-------------以下字段在return_code和result_code都为SUCCESS的时候有返回-------------//

	/**
	 * 字段名:商户订单号
	 * 必填:是
	 * 示例值:10000098201411111234567890
	 * 类型:String(28)
	 * 说明:商户订单号（每个订单号必须唯一）组成：mch_id+yyyymmdd+10位一天内不能重复的数字
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
	@JacksonXmlProperty(localName="mch_id")
	private String mchId;

	/**
	 * 字段名:公众账号appid
	 * 必填:是
	 * 示例值:wx8888888888888888
	 * 类型:String(32)
	 * 说明:商户appid，接口传入的所有appid应该为公众号的appid（在mp.weixin.qq.com申请的），
	 * 不能为APP的appid（在open.weixin.qq.com申请的）。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="wxappid")
	private String wxappid;

	/**
	 * 字段名:用户openid
	 * 必填:是
	 * 示例值:oxTWIuGaIt6gTKsQRLau2M0yL16E
	 * 类型:String(32)
	 * 说明:接受收红包的用户用户在wxappid下的openid
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="re_openid")
	private String reOpenid;

	/**
	 * 字段名:付款金额
	 * 必填:是
	 * 示例值:1000
	 * 类型:int
	 * 说明:付款金额，单位分
	 */
	@JacksonXmlProperty(localName="total_amount")
	private Integer totalAmount;

	/**
	 * 字段名:微信单号
	 * 必填:是
	 * 示例值:100000000020150520314766074200
	 * 类型:String(32)
	 * 说明:红包订单的微信单号
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="send_listid")
	private String sendListid;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
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

	public String getSendListid() {
		return sendListid;
	}

	public void setSendListid(String sendListid) {
		this.sendListid = sendListid;
	}

}
