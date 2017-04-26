package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.media.MediaId;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 发送图文消息（点击跳转到图文消息页面）
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyMpnewsMessage extends BaseMessage {
	
	/**
	 * 通过素材管理中的接口上传多媒体文件，得到的id。
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Mpnews")
	@JsonProperty("mpnews")
	private MediaId mpnews;

	public ReplyMpnewsMessage() {
		setMsgType(WxMsgType.mpnews.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public MediaId getMpnews() {
		return mpnews;
	}

	public void setMpnews(MediaId mpnews) {
		this.mpnews = mpnews;
	}

}
