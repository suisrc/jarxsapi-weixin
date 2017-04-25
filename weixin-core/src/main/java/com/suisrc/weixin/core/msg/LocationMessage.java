package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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
@JacksonXmlRootElement(localName="xml")
public class LocationMessage extends WxMessage {

	/**
	 * 地理位置维度
	 */
	@JacksonXmlProperty(localName="Location_X")
	private String locationX;
	
	/**
	 * 地理位置经度
	 */
	@JacksonXmlProperty(localName="Location_Y")
	private String locationY;

	/**
	 * 地图缩放大小
	 */
	@JacksonXmlProperty(localName="Scale")
	private String scale;
	
	/**
	 * 地理位置信息
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Label")
	private String label;

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
