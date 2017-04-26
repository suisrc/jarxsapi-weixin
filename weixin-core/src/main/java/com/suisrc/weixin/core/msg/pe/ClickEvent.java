package com.suisrc.weixin.core.msg.pe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 点击菜单拉取消息时的事件推送
 * <Event><![CDATA[CLICK]]></Event>
 * 
 * <EventKey><![CDATA[EVENTKEY]]></EventKey>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class ClickEvent extends WxEventMessage {

	/**
	 * 事件KEY值，与自定义菜单接口中KEY值对应
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="EventKey")
	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	
}
