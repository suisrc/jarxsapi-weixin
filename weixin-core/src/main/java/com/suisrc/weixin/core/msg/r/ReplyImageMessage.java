package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.media.MediaId;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 图片
 * <Image>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Image>
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyImageMessage extends BaseMessage {
	
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Image")
	@JsonProperty("image")
	private MediaId image;

	public ReplyImageMessage() {
		setMsgType(WxMsgType.image.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public MediaId getImage() {
		return image;
	}

	public void setImage(MediaId image) {
		this.image = image;
	}
	
}
