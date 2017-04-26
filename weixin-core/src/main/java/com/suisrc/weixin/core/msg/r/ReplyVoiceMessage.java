package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.media.MediaId;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 语音
 * <Voice>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Voice>
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyVoiceMessage extends BaseMessage {
	

	/**
	 * 语音信息
	 * 通过素材管理中的接口上传多媒体文件，得到的id
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Voice")
	@JsonProperty("voice")
	private MediaId voice;

	public ReplyVoiceMessage() {
		setMsgType(WxMsgType.voice.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public MediaId getVoice() {
		return voice;
	}

	public void setVoice(MediaId voice) {
		this.voice = voice;
	}
}
