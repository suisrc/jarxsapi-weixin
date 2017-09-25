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
    
    /**
     * 指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
     */
    @JacksonXmlProperty(localName = "MenuId")
    @JsonProperty("MenuId")
    private String menuId;

    /**
     * 获取事件KEY值，设置的跳转URL
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设定事件KEY值，设置的跳转URL
     * @param eventKey the eventKey to set
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 获取指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
     * @return the menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 设定指菜单ID，如果是个性化菜单，则可以通过这个字段，知道是哪个规则的菜单被点击了。
     * @param menuId the menuId to set
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    
}
