package com.suisrc.weixin.core.msg;

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
	private String toUserName;
	
	/**
	 * 为接收的应用id，可在应用的设置页面获取
	 * 只有企业号，该字段才有值
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="AgentID")
	private String agentID;
	
	/**
	 * 密文
	 * encrypt为经过加密的密文（消息明文格式参见 接收普通消息 ，事件明文格式参见 接收事件）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Encrypt")
	private String encrypt;
	
	/**
	 * 密文
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MsgSignature")
	private String msgSignature;
	
	/**
	 * 密文
	 */
	@JacksonXmlProperty(localName="TimeStamp")
	private String timeStamp;
	
	/**
	 * 密文
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Nonce")
	private String nonce;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	
	public String getAgentID() {
		return agentID;
	}
	
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}

	public String getMsgSignature() {
		return msgSignature;
	}

	public void setMsgSignature(String msgSignature) {
		this.msgSignature = msgSignature;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setTimeStamp(long time) {
		this.timeStamp = String.valueOf( time / 1000l );
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	
}
