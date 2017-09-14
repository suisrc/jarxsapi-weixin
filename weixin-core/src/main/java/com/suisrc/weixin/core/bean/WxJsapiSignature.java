package com.suisrc.weixin.core.bean;

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

    /**
     * 判断签名是否有效
     * @param sign
     * @return
     */
    public boolean isValid() {
        return this.getNonce() != null
            && this.getSignature() != null
            && this.getTimestamp() != null;
    }
}
