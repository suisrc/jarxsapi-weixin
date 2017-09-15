package com.qq.weixin.mp.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

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
@JsonIgnoreProperties(ignoreUnknown=true)
public class ServerIpResult extends WxErrCode {
    private static final long serialVersionUID = -3732592883756143708L;
    /**
     * 微信服务器IP地址列表
     */
    @JsonProperty("ip_list")
    private String[] ipList;
    /**
     * 获取微信服务器IP地址列表
     * @return the ipList
     */
    public String[] getIpList() {
        return ipList;
    }
    /**
     * 设定微信服务器IP地址列表
     * @param ipList the ipList to set
     */
    public void setIpList(String[] ipList) {
        this.ipList = ipList;
    }

}
