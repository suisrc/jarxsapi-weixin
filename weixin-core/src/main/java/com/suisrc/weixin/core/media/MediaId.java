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
public class MediaId {

	/**
	 * <MediaId><![CDATA[media_id]]></MediaId>
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 * 
	 * 必须字段
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MediaId")
	private String mediaId;

	@JsonProperty("media_id")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
