package com.qq.weixin.pay.result;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 查询红包记录 返回参数
 * 
 * 成功示例：
 * <xml>
 * <return_code><![CDATA[SUCCESS]]></return_code>
 * <return_msg><![CDATA[OK]]></return_msg>
 * <result_code><![CDATA[SUCCESS]]></result_code>
 * <err_code><![CDATA[SUCCESS]]></err_code>
 * <err_code_des><![CDATA[OK]]></err_code_des>
 * <mch_billno><![CDATA[9010080799701411170000046603]]></mch_billno>
 * <mch_id><![CDATA[11475856]]></mch_id>
 * <detail_id><![CDATA[10000417012016080830956240040]]></detail_id>
 * <status><![CDATA[RECEIVED]]></status>
 * <send_type><![CDATA[ACTIVITY]]></send_type>
 * <hb_type><![CDATA[NORMAL]]></hb_type>
 * <total_num>1</total_num>
 * <total_amount>100</total_amount>
 * <send_time><![CDATA[2016-08-08 21:49:22]]></send_time>
 * <hblist>
 * <hbinfo>
 * <openid><![CDATA[oHkLxtzmyHXX6FW_cAWo_orTSRXs]]></openid>
 * <amount>100</amount>
 * <rcv_time><![CDATA[2016-08-08 21:49:46]]></rcv_time>
 * </hbinfo>
 * </hblist>
 * </xml>
 * 失败示例：
 * <xml>
 * <return_code><![CDATA[FAIL]]></return_code>
 * <return_msg><![CDATA[指定单号数据不存在]]></return_msg>
 * <result_code><![CDATA[FAIL]]></result_code>
 * <err_code><![CDATA[SYSTEMERROR]]></err_code>
 * <err_code_des><![CDATA[指定单号数据不存在]]></err_code_des>
 * <mch_id>666</mch_id>
 * <mch_billno><![CDATA[1000005901201407261446939688]]></mch_billno>
 * </xml>
 * 
 * @author Y13
 *
 */
public class HbinfoResult {

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
	 * 说明:返回信息，如非空，为错误原因 签名失败 参数格式校验错误
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="return_msg")
	private String returnMsg;
	
	//-----------以下字段在return_code为SUCCESS的时候有返回-----------------//

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
	
	//----------以下字段在return_code 和result_code都为SUCCESS的时候有返回-----------//

	/**
	 * 字段名:商户订单号
	 * 必填:是
	 * 示例值:10000098201411111234567890
	 * 类型:String(28)
	 * 说明:商户使用查询API填写的商户单号的原路返回
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
	private String mchId;

	/**
	 * 字段名:红包单号
	 * 必填:是
	 * 示例值:1000000000201503283103439304
	 * 类型:String(32)
	 * 说明:使用API发放现金红包时返回的红包单号
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="detail_id")
	private String detailId;

	/**
	 * 字段名:红包状态
	 * 必填:是
	 * 示例值:RECEIVED
	 * 类型:string(16)
	 * 说明:
	 * SENDING:发放中 
	 * SENT:已发放待领取 
	 * FAILED：发放失败 
	 * RECEIVED:已领取 
	 * RFUND_ING:退款中 
	 * REFUND:已退款
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="status")
	private String status;

	/**
	 * 字段名:发放类型
	 * 必填:是
	 * 示例值:API
	 * 类型:String(32)
	 * 说明:API:通过API接口发放 UPLOAD:通过上传文件方式发放 ACTIVITY:通过活动方式发放
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="send_type")
	private String sendType;

	/**
	 * 字段名:红包类型
	 * 必填:是
	 * 示例值:GROUP
	 * 类型:String(32)
	 * 说明:GROUP:裂变红包 NORMAL:普通红包
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="hb_type")
	private String hbType;

	/**
	 * 字段名:红包个数
	 * 必填:是
	 * 示例值:1
	 * 类型:int
	 * 说明:红包个数
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="total_num")
	private String totalNum;

	/**
	 * 字段名:红包金额
	 * 必填:是
	 * 示例值:5000
	 * 类型:int
	 * 说明:红包总金额（单位分）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="total_amount")
	private String totalAmount;

	/**
	 * 字段名:失败原因
	 * 必填:否
	 * 示例值:余额不足
	 * 类型:String(32)
	 * 说明:发送失败原因
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="reason")
	private String reason;

	/**
	 * 字段名:红包发送时间
	 * 必填:是
	 * 示例值:2015-04-21 20:00:00
	 * 类型:String(32)
	 * 说明: 
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="send_time")
	private String sendTime;

	/**
	 * 字段名:红包退款时间
	 * 必填:否
	 * 示例值:2015-04-21 23:03:00
	 * 类型:String(32)
	 * 说明:红包的退款时间（如果其未领取的退款）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="refund_time")
	private String refundTime;

	/**
	 * 字段名:红包退款金额
	 * 必填:否
	 * 示例值:8000
	 * 类型:Int
	 * 说明:红包退款金额
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="refund_amount")
	private String refundAmount;

	/**
	 * 字段名:祝福语
	 * 必填:否
	 * 示例值:新年快乐
	 * 类型:String(128)
	 * 说明:祝福语
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="wishing")
	private String wishing;

	/**
	 * 字段名:活动描述
	 * 必填:否
	 * 示例值:新年红包
	 * 类型:String(256)
	 * 说明:活动描述，低版本微信可见
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="remark")
	private String remark;

	/**
	 * 字段名:活动名称
	 * 必填:否
	 * 示例值:新年红包
	 * 类型:String(32)
	 * 说明:发红包的活动名称
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="act_name")
	private String actName;

	/**
	 * 字段名:裂变红包领取列表
	 * 必填:否
	 * 示例值:内容如下表
	 * 类型: 
	 * 说明:裂变红包的领取列表
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="hblist")
	private String hblist;

	/**
	 * 字段名:领取红包的Openid
	 * 必填:是
	 * 示例值:ohO4GtzOAAYMp2yapORH3dQB3W18
	 * 类型:String(32)
	 * 说明:领取红包的openid
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="openid")
	private String openid;

	/**
	 * 字段名:金额
	 * 必填:是
	 * 示例值:100
	 * 类型:int
	 * 说明:领取金额
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="amount")
	private String amount;

	/**
	 * 字段名:接收时间
	 * 必填:是
	 * 示例值:2015-04-21 20:00:00
	 * 类型:String(32)
	 * 说明:领取红包的时间
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="rcv_time")
	private String rcvTime;

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

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getHbType() {
		return hbType;
	}

	public void setHbType(String hbType) {
		this.hbType = hbType;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getHblist() {
		return hblist;
	}

	public void setHblist(String hblist) {
		this.hblist = hblist;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRcvTime() {
		return rcvTime;
	}

	public void setRcvTime(String rcvTime) {
		this.rcvTime = rcvTime;
	}

}
