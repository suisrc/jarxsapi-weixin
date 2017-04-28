package com.qq.weixin.pay.bean;

/**
 * 错误码
 * @author Y13
 *
 */
public enum ErrCode {
	
	CA_ERROR("请求未携带证书，或请求携带的证书出错", "到商户平台下载证书，请求带上证书后重"),
	SIGN_ERROR("商户签名错误", "按文档要求重新生成签名后再重试"),
	NO_AUTH("没有权限", "请联系微信支付开通api权限"),
	NOT_FOUND("指定单号数据不存在", "查询单号对应的数据不存在，请使用正确的商户订单号查询"),
	FREQ_LIMIT("受频率限制", "请对请求做频率控制"),
	XML_ERROR("请求的xml格式错误，或者post的数据为空", "检查请求串，确认无误后重试"),
	PARAM_ERROR("参数错误", "请查看err_code_des，修改设置错误的参数"),
	SYSTEMERROR("系统繁忙，请再试", "红包系统繁忙");

	public final String description;
	public final String solution;
	private ErrCode( String description, String solution ) {
		this.description = description;
		this.solution = solution;
	}
}
