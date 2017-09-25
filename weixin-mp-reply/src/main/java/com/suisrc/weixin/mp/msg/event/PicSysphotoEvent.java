package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;
import com.suisrc.weixin.mp.msg.media.SendPicsInfo;

/**
 * 弹出系统拍照发图的事件推送
 * 
 * <Event><![CDATA[pic_sysphoto]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <SendPicsInfo>
 *   <Count>1</Count>
 *   <PicList>
 *     <item>
 *       <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
 *     </item>
 *   </PicList>
 * </SendPicsInfo>
 * 
 * @author Y13
 *
 */
@MpEvent("pic_sysphoto")
@JacksonXmlRootElement(localName="xml")
public class PicSysphotoEvent extends WxEventMessage {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    @JsonProperty("EventKey")
    private String eventKey;
    
    /**
     * 发送的图片信息
     */
    @JacksonXmlProperty(localName = "SendPicsInfo")
    @JsonProperty("SendPicsInfo")
    private SendPicsInfo sendPicsInfo;

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
     * 获取发送的图片信息
     * @return the sendPicsInfo
     */
    public SendPicsInfo getSendPicsInfo() {
        return sendPicsInfo;
    }

    /**
     * 设定发送的图片信息
     * @param sendPicsInfo the sendPicsInfo to set
     */
    public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
        this.sendPicsInfo = sendPicsInfo;
    }
}
