package com.suisrc.weixin.core.bean;

import javax.ws.rs.QueryParam;

/**
 * jsapi signature
 */
public class WxEncryptSignature extends WxJsapiSignature {
	private static final long serialVersionUID = 6178170380018311930L;

	@QueryParam("msg_signature")
	private String msgSignature;
	
	@QueryParam("encrypt_type")
	private String encryptType;
	
	public String getMsgSignature() {
		return msgSignature;
	}
	
	public void setMsgSignature(String msgSignature) {
		this.msgSignature = msgSignature;
	}
	
	public String getEncryptType() {
		return encryptType;
	}
	
	public void setEncryptType(String encryptType) {
		this.encryptType = encryptType;
	}

	/**
	 * 判断签名是否有效
	 * @param sign
	 * @return
	 */
	public boolean isValid() {
		return this.getEncryptType() != null
			&& this.getMsgSignature() != null
			&& super.isValid();
	}
	
}
