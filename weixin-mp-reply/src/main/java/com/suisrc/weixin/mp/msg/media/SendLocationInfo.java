package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 发送的位置信息
 * 
 * <SendLocationInfo>
 *   <Location_X><![CDATA[23]]></Location_X>
 *   <Location_Y><![CDATA[113]]></Location_Y>
 *   <Scale><![CDATA[15]]></Scale>
 *   <Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>
 *   <Poiname><![CDATA[]]></Poiname>
 * </SendLocationInfo>
 * @author Y13
 *
 */
public class SendLocationInfo {
    
    /**
     * X坐标信息
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName="Location_X")
    @JsonProperty("Location_X")
    private String locationX;

    /**
     * Y坐标信息
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName="Location_Y")
    @JsonProperty("Location_Y")
    private String locationY;

    /**
     * 精度，可理解为精度或者比例尺、越精细的话 scale越高
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName="Scale")
    @JsonProperty("Scale")
    private String scale;

    /**
     * 地理位置的字符串信息
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName="Label")
    @JsonProperty("Label")
    private String label;

    /**
     * 朋友圈POI的名字，可能为空
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName="Poiname")
    @JsonProperty("Poiname")
    private String poiname;

    /**
     * 获取X坐标信息
     * @return the locationX
     */
    public String getLocationX() {
        return locationX;
    }

    /**
     * 设定X坐标信息
     * @param locationX the locationX to set
     */
    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    /**
     * 获取Y坐标信息
     * @return the locationY
     */
    public String getLocationY() {
        return locationY;
    }

    /**
     * 设定Y坐标信息
     * @param locationY the locationY to set
     */
    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    /**
     * 获取精度，可理解为精度或者比例尺、越精细的话 scale越高
     * @return the scale
     */
    public String getScale() {
        return scale;
    }

    /**
     * 设定精度，可理解为精度或者比例尺、越精细的话 scale越高
     * @param scale the scale to set
     */
    public void setScale(String scale) {
        this.scale = scale;
    }

    /**
     * 获取地理位置的字符串信息
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设定地理位置的字符串信息
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 获取朋友圈POI的名字，可能为空
     * @return the poiname
     */
    public String getPoiname() {
        return poiname;
    }

    /**
     * 设定朋友圈POI的名字，可能为空
     * @param poiname the poiname to set
     */
    public void setPoiname(String poiname) {
        this.poiname = poiname;
    }

}
