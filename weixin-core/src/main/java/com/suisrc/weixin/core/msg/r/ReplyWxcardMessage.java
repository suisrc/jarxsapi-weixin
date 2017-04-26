package com.suisrc.weixin.core.msg.r;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.WxMsgType;
import com.suisrc.weixin.core.media.MediaId;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 微信卡券消息
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class ReplyWxcardMessage extends BaseMessage {
	
	/**
	 *微信卡券
	 * 必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Wxcard")
	@JsonProperty("wxcard")
	private MediaId wxcard;

	public ReplyWxcardMessage() {
		setMsgType(WxMsgType.wxcard.name());
		setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
	}

	public MediaId getWxcard() {
		return wxcard;
	}

	public void setWxcard(MediaId wxcard) {
		this.wxcard = wxcard;
	}

}
