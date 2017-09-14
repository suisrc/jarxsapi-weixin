package com.suisrc.weixin.mp.msg.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.base.WxMessage;

/**
 * 地理位置消息
 * <MsgType><![CDATA[location]]></MsgType>
 * 
 * <Location_X>23.134521</Location_X>
 * <Location_Y>113.358803</Location_Y>
 * <Scale>20</Scale>
 * <Label><![CDATA[位置信息]]></Label>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName = "xml")
public class LocationMessage extends WxMessage {

    /**
     * 地理位置维度
     */
    @JacksonXmlProperty(localName = "Location_X")
    private String locationX;

    /**
     * 地理位置经度
     */
    @JacksonXmlProperty(localName = "Location_Y")
    private String locationY;

    /**
     * 地图缩放大小
     */
    @JacksonXmlProperty(localName = "Scale")
    private String scale;

    /**
     * 地理位置信息
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Label")
    private String label;

    /**
     * 获取地理位置维度
     * @return the locationX
     */
    public String getLocationX() {
        return locationX;
    }

    /**
     * 设定地理位置维度
     * @param locationX the locationX to set
     */
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    /**
     * 获取地理位置经度
     * @return the locationY
     */
    public String getLocationY() {
        return locationY;
    }

    /**
     * 设定地理位置经度
     * @param locationY the locationY to set
     */
    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    /**
     * 获取地图缩放大小
     * @return the scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * 设定地图缩放大小
     * @param scale the scale to set
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * 获取地理位置信息
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设定地理位置信息
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

}
