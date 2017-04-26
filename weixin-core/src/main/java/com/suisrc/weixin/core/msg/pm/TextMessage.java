package com.suisrc.weixin.core.msg.pm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 文本消息
 * <MsgType><![CDATA[text]]></MsgType>
 * 
 * <Content><![CDATA[this is a test]]></Content>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class TextMessage extends WxMessage {

	/**
	 * 文本消息内容
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Content")
	private String content;
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
