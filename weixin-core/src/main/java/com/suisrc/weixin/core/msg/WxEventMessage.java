package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 微信事件
 * <Event><![CDATA[subscribe]]></Event>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class WxEventMessage extends BaseMessage {

	/**
	 * 事件类型
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Event")
	private String event;
	
	public String getEvent() {
		return event;
	}
	
	public void setEvent(String event) {
		this.event = event;
	}
}
