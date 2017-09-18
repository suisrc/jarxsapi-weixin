package com.suisrc.weixin.mp.msg.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpMsgType;

/**
 * 微信事件， 通用消息
 * <Event><![CDATA[subscribe]]></Event>
 * @author Y13
 *
 */
@MpMsgType("event")
@JacksonXmlRootElement(localName="xml")
public abstract class WxEventMessage extends BaseMessage {

	/**
	 * 事件类型
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Event")
    @JsonProperty("Event")
	private String event;
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
}
