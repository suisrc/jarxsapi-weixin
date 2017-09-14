package com.suisrc.weixin.mp.msg.rkf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发送卡券
 * 
 * JSON解析
 */
public class KfReplyWxcardMessage extends ReplyWxcardMessage4Json {
	
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
