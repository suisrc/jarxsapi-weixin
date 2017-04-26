package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 文本
 * <Content><![CDATA[你好]]></Content>
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyTextMessage extends BaseMessage {

	/**
	 * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Content")
	@JsonProperty("text")
	private String content;
	
	public ReplyTextMessage() {
		setMsgType(WxMsgType.text.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
