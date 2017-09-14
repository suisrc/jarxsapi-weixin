package com.suisrc.weixin.mp.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suisrc.weixin.mp.msg.reply.ReplyWxcardMessage;

/**
 * 微信卡券消息
 * @author Y13
 *
 */
public class ReplyWxcardMessage4Json extends ReplyWxcardMessage {

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
