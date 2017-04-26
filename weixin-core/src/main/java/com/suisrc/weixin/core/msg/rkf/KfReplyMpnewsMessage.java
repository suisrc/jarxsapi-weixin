package com.suisrc.weixin.core.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 图文消息
 * 发送图文消息（点击跳转到图文消息页面） 
 * 
 * JSON解析
 */
public class KfReplyMpnewsMessage extends ReplyMpnewsMessage4Json {
	
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
