package com.suisrc.weixin.core.msg.pm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 视频消息
 * <MsgType><![CDATA[video]]></MsgType>
 * 
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class VideoMessage extends WxMessage {

	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MediaId")
	private String mediaId;
	
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="ThumbMediaId")
	private String thumbMediaId;
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
}
