package com.suisrc.weixin.mp.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suisrc.weixin.mp.msg.reply.ReplyMpnewsMessage;

/**
 * 发送图文消息（点击跳转到图文消息页面）
 */
public class ReplyMpnewsMessage4Json extends ReplyMpnewsMessage {

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
