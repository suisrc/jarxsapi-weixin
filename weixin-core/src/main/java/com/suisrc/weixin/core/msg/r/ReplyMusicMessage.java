package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.media.MusicMedia;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 音乐
 * <Music>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
 * <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
 * <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
 * </Music>
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyMusicMessage extends BaseMessage {
	
	/**
	 * 音乐内容
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Music")
	@JsonProperty("music")
	private MusicMedia music;

	public ReplyMusicMessage() {
		setMsgType(WxMsgType.music.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public MusicMedia getMusic() {
		return music;
	}

	public void setMusic(MusicMedia music) {
		this.music = music;
	}
	
	
}
