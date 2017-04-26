package com.suisrc.weixin.core.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
public class MediaInfo extends MediaId {

	/**
	 * <Title><![CDATA[title]]></Title>
	 * 消息的标题
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Title")
	private String title;
	
	/**
	 * <Description><![CDATA[description]]></Description>
	 * 消息的描述
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Description")
	private String description;

	/**
	 * <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
	 * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="ThumbMediaId")
	@JsonProperty("thumb_media_id")
	private String thumbMediaId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
}
