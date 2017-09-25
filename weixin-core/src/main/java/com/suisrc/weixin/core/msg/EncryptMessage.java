package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 加密文件存储
 * 公众号
 * <xml>
 *    <ToUserName><![CDATA[123]]></ToUserName>
 *    <Encrypt><![CDATA[DPqPNL5x3Xp+X+yLkwjkbE8Vl+R5CJch8DZeISOTq1A]]></Encrypt>
 * </xml>
 * 企业号
 * <xml> 
 *    <ToUserName><![CDATA[toUser]]</ToUserName>
 *    <AgentID><![CDATA[toAgentID]]</AgentID>
 *    <Encrypt><![CDATA[msg_encrypt]]</Encrypt>
 * </xml>
 * @author Y13
 *
 */
public class EncryptMessage {
	
	/**
	 * ToUserName为公众号AppId或者企业号的CorpID
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="ToUserName")
	@JsonProperty("ToUserName")
	private String toUserName;
	
	/**
	 * 为接收的应用id，可在应用的设置页面获取
	 * 只有企业号，该字段才有值
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="AgentID")
    @JsonProperty("AgentID")
	private String agentID;
	
	/**
	 * 密文
	 * encrypt为经过加密的密文（消息明文格式参见 接收普通消息 ，事件明文格式参见 接收事件）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Encrypt")
    @JsonProperty("Encrypt")
	private String encrypt;
	
	/**
	 * 密文签名
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MsgSignature")
    @JsonProperty("MsgSignature")
	private String msgSignature;
	
	/**
	 * 密文时间戳
	 */
	@JacksonXmlProperty(localName="TimeStamp")
    @JsonProperty("TimeStamp")
	private String timeStamp;
	
	/**
	 * 密文随机码
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Nonce")
    @JsonProperty("Nonce")
	private String nonce;
    
    /**
     * 获取ToUserName为公众号AppId或者企业号的CorpID
     * @return the toUserName
     */
    public String getToUserName() {
        return toUserName;
    }

    /**
     * 设定ToUserName为公众号AppId或者企业号的CorpID
     * @param toUserName the toUserName to set
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    /**
     * 获取为接收的应用id，可在应用的设置页面获取 只有企业号，该字段才有值
     * @return the agentID
     */
    public String getAgentID() {
        return agentID;
    }

    /**
     * 设定为接收的应用id，可在应用的设置页面获取 只有企业号，该字段才有值
     * @param agentID the agentID to set
     */
    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }

    /**
     * 获取密文 encrypt为经过加密的密文（消息明文格式参见 接收普通消息 ，事件明文格式参见 接收事件）
     * @return the encrypt
     */
    public String getEncrypt() {
        return encrypt;
    }

    /**
     * 设定密文 encrypt为经过加密的密文（消息明文格式参见 接收普通消息 ，事件明文格式参见 接收事件）
     * @param encrypt the encrypt to set
     */
    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    /**
     * 获取密文签名
     * @return the msgSignature
     */
    public String getMsgSignature() {
        return msgSignature;
    }

    /**
     * 设定密文签名
     * @param msgSignature the msgSignature to set
     */
    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    /**
     * 获取密文时间戳
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * 设定密文时间戳
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * 获取密文随机码
     * @return the nonce
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * 设定密文随机码
     * @param nonce the nonce to set
     */
    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    /**
     * 设定密文时间戳
     * 毫秒，字段会被转换为秒
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(long time) {
        this.timeStamp = String.valueOf( time / 1000l );
    }
	
}
