package com.suisrc.weixin.core.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内
 * 
 * JSON解析
 */
public class KfReplyNewsMessage extends ReplyNewsMessage4Json {
	
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
