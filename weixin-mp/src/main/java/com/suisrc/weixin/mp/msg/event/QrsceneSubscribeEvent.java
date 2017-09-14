package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 二维码扫描
 * 用户未关注时，进行关注后的事件推送
 * <Event><![CDATA[subscribe]]></Event>
 * 
 * <EventKey><![CDATA[qrscene_123123]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * 
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName = "xml")
public class QrsceneSubscribeEvent extends WxEventMessage {

    /**
     * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;

    /**
     * 二维码的ticket，可用来换取二维码图片
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Ticket")
    private String ticket;

    /**
     * 获取事件KEY值，qrscene_为前缀，后面为二维码的参数值
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设定事件KEY值，qrscene_为前缀，后面为二维码的参数值
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
