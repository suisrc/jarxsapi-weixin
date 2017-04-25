package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 图片消息
 * <MsgType><![CDATA[image]]></MsgType>
 * 
 * <PicUrl><![CDATA[this is a url]]></PicUrl>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class ImageMessage extends WxMessage {

	/**
	 * 图片链接（由系统生成）
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="PicUrl")
	private String picUrl;
	
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MediaId")
	private String mediaId;
	
	public String getPicUrl() {
		return picUrl;
	}
	
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
}
