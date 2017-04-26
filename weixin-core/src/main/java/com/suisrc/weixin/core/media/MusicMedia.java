package com.suisrc.weixin.core.media;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
public class MusicMedia extends MediaInfo {

	/**
	 * <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
	 * 音乐链接
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MusicUrl")
	@JsonProperty("musicurl")
	private String musicUrl;

	/**
	 * <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
	 * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="HQMusicUrl")
	@JsonProperty("hqmusicurl")
	private String hQMusicUrl;
	
	/**
	 * 忽略该字段内容
	 */
	@Override
	@JsonIgnore
	public String getMediaId() {
		return getThumbMediaId();
	}
	
	@Override
	public void setMediaId(String mediaId) {
		setThumbMediaId(mediaId);
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}
	
}
