package com.suisrc.weixin.core.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suisrc.weixin.core.msg.r.ReplyImageMessage;

/**
 * 图片
 */
public class ReplyImageMessage4Json extends ReplyImageMessage {

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
