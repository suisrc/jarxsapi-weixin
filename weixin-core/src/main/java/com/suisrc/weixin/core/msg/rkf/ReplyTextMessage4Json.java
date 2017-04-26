package com.suisrc.weixin.core.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.msg.r.ReplyTextMessage;

/**
 * 文本
 * 
 * JSON解析
 */
public class ReplyTextMessage4Json extends ReplyTextMessage {

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
	
	@JsonIgnore
	public String getContent() {
		return super.getContent();
	}
	
	/**
	 * json解析使用
	 * @return
	 */
	@JsonProperty("text")@SuppressWarnings("unused")
	public Object getJsonText() {
		return new Object() {public String getContent() {return ReplyTextMessage4Json.this.getContent();}};
	}
	
}
