package com.suisrc.weixin.mp.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发送视频消息
 * 
 * JSON解析
 */
public class KfReplyVideoMessage extends ReplyVideoMessage4Json {
	
	/**
	 * 客服帐号
	 */
	private String kfAccount;
	
	@JsonIgnore
	public String getKfAccount() {
		return kfAccount;
	}
	
	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
	
	/**
	 * json解析使用
	 * @return
	 */
	@JsonProperty("customservice")@SuppressWarnings("unused")
	public Object getJsonText() {
		return new Object() {public String getKf_account() {return getKfAccount();}};
	}
	
}
