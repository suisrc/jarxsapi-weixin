package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 点击菜单跳转链接时的事件推送
 * 
 * <Event><![CDATA[VIEW]]></Event>
 * 
 * <EventKey><![CDATA[www.qq.com]]></EventKey>
 * 
 * @author Y13
 *
 */
@MpEvent("VIEW")
@JacksonXmlRootElement(localName = "xml")
public class ViewEvent extends WxEventMessage {

    /**
     * 事件KEY值，设置的跳转URL
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    @JsonProperty("EventKey")
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

}
