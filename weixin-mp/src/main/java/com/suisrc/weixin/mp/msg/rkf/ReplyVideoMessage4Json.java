package com.suisrc.weixin.mp.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suisrc.weixin.mp.msg.reply.ReplyVideoMessage;

/**
 * 视频
 */
public class ReplyVideoMessage4Json extends ReplyVideoMessage {

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
