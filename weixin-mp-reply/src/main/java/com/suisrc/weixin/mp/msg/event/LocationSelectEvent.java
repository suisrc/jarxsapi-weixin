package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;
import com.suisrc.weixin.mp.msg.media.SendLocationInfo;

/**
 * 弹出地理位置选择器的事件推送
 * 
 * <Event><![CDATA[location_select]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <SendLocationInfo>
 *   <Location_X><![CDATA[23]]></Location_X>
 *   <Location_Y><![CDATA[113]]></Location_Y>
 *   <Scale><![CDATA[15]]></Scale>
 *   <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>
 *   <Poiname><![CDATA[]]></Poiname>
 * </SendLocationInfo>
 * 
 * @author Y13
 *
 */
@MpEvent("location_select")
@JacksonXmlRootElement(localName="xml")
public class LocationSelectEvent extends WxEventMessage {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    @JsonProperty("EventKey")
    private String eventKey;
    
    /**
     * 发送的位置信息
     */
    @JacksonXmlProperty(localName = "SendLocationInfo")
    @JsonProperty("SendLocationInfo")
    private SendLocationInfo sendLocationInfo;

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

    /**
     * 获取发送的位置信息
     * @return the sendLocationInfo
     */
    public SendLocationInfo getSendLocationInfo() {
        return sendLocationInfo;
    }

    /**
     * 设定发送的位置信息
     * @param sendLocationInfo the sendLocationInfo to set
     */
    public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
        this.sendLocationInfo = sendLocationInfo;
    }

}
