package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;
import com.suisrc.weixin.mp.msg.media.ScanCodeInfo;

/**
 * 扫码推事件的事件推送
 * 
 * <Event><![CDATA[scancode_push]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <ScanCodeInfo>
 *   <ScanType><![CDATA[qrcode]]></ScanType>
 *   <ScanResult><![CDATA[1]]></ScanResult>
 * </ScanCodeInfo>
 * 
 * @author Y13
 *
 */
@MpEvent("scancode_push")
@JacksonXmlRootElement(localName="xml")
public class ScancodePushEvent extends WxEventMessage {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    @JsonProperty("EventKey")
    private String eventKey;
    
    /**
     * 扫描信息
     */
    @JacksonXmlProperty(localName = "ScanCodeInfo")
    @JsonProperty("ScanCodeInfo")
    private ScanCodeInfo scanCodeInfo;

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
     * 获取扫描信息
     * @return the scanCodeInfo
     */
    public ScanCodeInfo getScanCodeInfo() {
        return scanCodeInfo;
    }

    /**
     * 设定扫描信息
     * @param scanCodeInfo the scanCodeInfo to set
     */
    public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
        this.scanCodeInfo = scanCodeInfo;
    }
    
}
