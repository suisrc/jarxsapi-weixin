package com.qq.weixin.pay.bean;

/**
 * 红包发送状态
 * @author Y13
 *
 */
public enum HbSendStatus {

	SENDING("发放中"),
	SENT("已发放待领取 "),
	FAILED("发放失败 "),
	RECEIVED("已领取 "),
	RFUND_ING("退款中 "),
	REFUND("已退款");
	
	public final String value;
	private HbSendStatus(String value) {
		this.value = value;
	}
}
