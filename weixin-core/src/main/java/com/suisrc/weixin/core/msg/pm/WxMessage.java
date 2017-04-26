package com.suisrc.weixin.core.msg.pm;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 微信消息
 * <MsgId>1234567890123456</MsgId>
 * @author Y13
 */
public abstract class WxMessage extends BaseMessage {
	/**
	 * 消息id，64位整型
	 */
	@JacksonXmlProperty(localName="MsgId")
	private Long msgId;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

}
