package com.suisrc.weixin.core.bean;

/**
 * 授权类型
 * 
 * @author Y13
 *
 */
public enum GrantType {
    /**
     * 授权码模式
     */
    client_credential,
    /**
     * 客户端模式
     */
    authorization_code,
    /**
     * 刷新token
     */
    refresh_token
}
