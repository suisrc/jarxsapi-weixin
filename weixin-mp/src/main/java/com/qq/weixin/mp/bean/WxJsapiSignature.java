package com.qq.weixin.mp.bean;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

/**
 * jspai signature
 */
public class WxJsapiSignature implements Serializable {
	private static final long serialVersionUID = -1116808193154384804L;

	@QueryParam("signature")
	private String signature;

	@QueryParam("timestamp")
	private String timestamp;

	@QueryParam("nonce")
	private String nonce;

	@QueryParam("echostr")
	private String echostr;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	
	
}
