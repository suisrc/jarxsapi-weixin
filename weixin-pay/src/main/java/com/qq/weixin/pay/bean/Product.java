package com.qq.weixin.pay.bean;

/**
 * 场景id
 * @author Y13
 *
 */
public enum Product {
	
	PRODUCT_1("商品促销"),
	PRODUCT_2("抽奖"),
	PRODUCT_3("虚拟物品兑奖"),
	PRODUCT_4("企业内部福利"),
	PRODUCT_5("渠道分润"),
	PRODUCT_6("保险回馈"),
	PRODUCT_7("彩票派奖"),
	PRODUCT_8("税务刮奖");
	
	/**
	 * 内容，类型
	 */
	public final String value;
	private Product(String value) {
		this.value = value;
	}

}
