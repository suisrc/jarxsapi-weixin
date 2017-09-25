package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 二维码扫描
 * 
 * 用户已关注时的事件推送
 * 
 * <Event><![CDATA[SCAN]]></Event>
 * 
 * <EventKey><![CDATA[SCENE_VALUE]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * 
 * @author Y13
 *
 */
@MpEvent("SCAN")
@JacksonXmlRootElement(localName = "xml")
public class QrsceneEvent extends WxEventMessage {

    /**
     * 事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    @JsonProperty("EventKey")
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Ticket")
    @JsonProperty("Ticket")
    private String ticket;

    /**
     * 获取事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设定事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
     * @param eventKey the eventKey to set
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 获取二维码的ticket，可用来换取二维码图片
     * @return the ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * 设定二维码的ticket，可用来换取二维码图片
     * @param ticket the ticket to set
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

}
