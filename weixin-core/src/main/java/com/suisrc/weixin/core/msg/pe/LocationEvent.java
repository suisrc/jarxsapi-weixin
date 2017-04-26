package com.suisrc.weixin.core.msg.pe;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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
@JacksonXmlRootElement(localName="xml")
public class LocationEvent extends WxEventMessage {

	/**
	 * 地理位置纬度
	 */
	@JacksonXmlProperty(localName="Latitude")
	private String latitude;

	/**
	 * 地理位置经度
	 */
	@JacksonXmlProperty(localName="Longitude")
	private String longitude;

	/**
	 * 地理位置精度
	 */
	@JacksonXmlProperty(localName="Precision")
	private String precision;

}
