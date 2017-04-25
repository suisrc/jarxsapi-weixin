package com.suisrc.weixin.core.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信服务器IP地址列表
 * {
 *    "ip_list": [
 *        "127.0.0.1", 
 *        "127.0.0.2", 
 *        "101.226.103.0/25"
 *    ]
 * }
 * @author Y13
 *
 */
public class ServerIpResult extends WxErrCode {
	private static final long serialVersionUID = -3732592883756143708L;
	/**
	 * 微信服务器IP地址列表
	 */
	@JsonProperty("ip_list")
	private String[] ipList;
	
	public String[] getIpList() {
		return ipList;
	}
	
	public void setIpList(String[] ipList) {
		this.ipList = ipList;
	}
}
