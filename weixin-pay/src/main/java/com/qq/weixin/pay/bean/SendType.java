package com.qq.weixin.pay.bean;

/**
 * 发放类型
 * @author Y13
 *
 */
public enum SendType {

	API("通过API接口发放"),
	UPLOAD("通过上传文件方式发放"),
	ACTIVITY("通过活动方式发放");

	public final String value;
	private SendType(String value) {
		this.value = value;
	}
}
