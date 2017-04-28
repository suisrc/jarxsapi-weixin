package com.qq.weixin.pay.bean;

/**
 * 红包类型
 * @author Y13
 *
 */
public enum HbType {
	
	GROUP("裂变红包"),
	NORMAL("普通红包");

	public final String value;
	private HbType(String value) {
		this.value = value;
	}
}
