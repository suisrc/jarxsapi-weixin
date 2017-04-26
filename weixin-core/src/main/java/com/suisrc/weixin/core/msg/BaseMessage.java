package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信消息
 * 抽象接口
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * </xml>
 * @author Y13
 */
public abstract class BaseMessage {
	
	/**
	 * 微信回调地址
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="URL")
	private String baseUrl;
	
	/**
	 * 开发者微信号
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="ToUserName")
	@JsonProperty("touser")
	private String toUserName;
	
	/**
	 * 发送方帐号（一个OpenID）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="FromUserName")
	private String fromUserName;

	/**
	 * 消息创建时间 （整型）
	 */
	@JacksonXmlProperty(localName="CreateTime")
	private Integer createTime;

	/**
	 * 消息类型
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MsgType")
	@JsonProperty("msgtype")
	private String msgType;
	
	public String getBaseUrl() {
		return baseUrl;
	}
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	/**
	 * 发送和接受用户反转
	 * @param msg
	 */
	public <T extends BaseMessage> T reverse(T msg) {
		msg.setToUserName(this.getFromUserName());
		msg.setFromUserName(this.toUserName);
		return msg;
	}
}
