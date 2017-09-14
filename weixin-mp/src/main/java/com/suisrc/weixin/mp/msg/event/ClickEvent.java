package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 点击菜单拉取消息时的事件推送
 * 用户点击自定义菜单后，微信会把点击事件推送给开发者，请注意，点击菜单弹出子菜单，不会产生上报。
 * <Event><![CDATA[CLICK]]></Event>
 * 
 * <EventKey><![CDATA[EVENTKEY]]></EventKey>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName = "xml")
public class ClickEvent extends WxEventMessage {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    private String eventKey;

    /**
     * 获取事件KEY值，与自定义菜单接口中KEY值对应
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设定事件KEY值，与自定义菜单接口中KEY值对应
     * @param eventKey the eventKey to set
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

}
