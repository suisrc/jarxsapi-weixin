package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.media.MediaInfo;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 视频
 * <Video>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * </Video>
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyVideoMessage extends BaseMessage {
	
	/**
	 * 视频信息
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Video")
	@JsonProperty("video")
	private MediaInfo video;
	
	public ReplyVideoMessage() {
		setMsgType(WxMsgType.video.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public MediaInfo getVideo() {
		return video;
	}

	public void setVideo(MediaInfo video) {
		this.video = video;
	}
	
	
}
