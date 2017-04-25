package com.suisrc.weixin.core.bean;

import java.io.InputStream;

/**
 * jspai signature
 */
public class WxJsapiSignatureStream extends WxJsapiSignature {
	private static final long serialVersionUID = 6178170380018311930L;

	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
