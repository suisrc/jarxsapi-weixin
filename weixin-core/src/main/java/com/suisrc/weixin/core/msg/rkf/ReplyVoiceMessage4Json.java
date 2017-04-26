package com.suisrc.weixin.core.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suisrc.weixin.core.msg.r.ReplyVoiceMessage;

/**
 * 语音
 */
public class ReplyVoiceMessage4Json extends ReplyVoiceMessage {

	@JsonIgnore
	public String getBaseUrl() {
		return super.getBaseUrl();
	}
	
	@JsonIgnore
	public String getFromUserName() {
		return super.getFromUserName();
	}
	
	@JsonIgnore
	public Integer getCreateTime() {
		return super.getCreateTime();
	}
	
}
