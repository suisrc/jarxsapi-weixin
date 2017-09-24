package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 上报地理位置事件
 * <Event><![CDATA[LOCATION]]></Event>
 * 
 * <Latitude>23.137466</Latitude>
 * <Longitude>113.352425</Longitude>
 * <Precision>119.385040</Precision>
 * 
 * @author Y13
 *
 */
@MpEvent("LOCATION")
@JacksonXmlRootElement(localName = "xml")
public class LocationEvent extends WxEventMessage {

    /**
     * 地理位置纬度
     */
    @JacksonXmlProperty(localName = "Latitude")
    @JsonProperty("Latitude")
    private String latitude;

    /**
     * 地理位置经度
     */
    @JacksonXmlProperty(localName = "Longitude")
    @JsonProperty("Longitude")
    private String longitude;

    /**
     * 地理位置精度
     */
    @JacksonXmlProperty(localName = "Precision")
    @JsonProperty("Precision")
    private String precision;

    /**
     * 获取地理位置纬度
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设定地理位置纬度
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * 获取地理位置经度
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设定地理位置经度
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 获取地理位置精度
     * @return the precision
     */
    public String getPrecision() {
        return precision;
    }

    /**
     * 设定地理位置精度
     * @param precision the precision to set
     */
    public void setPrecision(String precision) {
        this.precision = precision;
    }

}
