package com.suisrc.weixin.core.msg.pe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 二维码扫描
 * 用户已关注时的事件推送
 * <Event><![CDATA[SCAN]]></Event>
 * 
 * <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * 
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class QrsceneEvent extends WxEventMessage {

	/**
	 * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="EventKey")
	private String eventKey;

	/**
	 * 二维码的ticket，可用来换取二维码图片
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Ticket")
	private String ticket;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
}
