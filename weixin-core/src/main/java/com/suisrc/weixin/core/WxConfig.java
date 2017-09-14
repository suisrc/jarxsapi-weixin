package com.suisrc.weixin.core;

/**
 * 微信配置接口
 * @author Y13
 *
 */
public interface WxConfig {

    /**
     * 微信公众号的appid
     */
    String getAppId();

    /**
     * 微信公众号的app corpSecret
     */
    String getAppSecret();

    /**
     * 微信公众号的token
     */
    String getToken();

    /**
     * 消息加解密密钥
     */
    String getEncodingAesKey();

    /**
     * 获取access token
     */
    String getAccessToken();

    /**
     * 防止AccessToken在访问过程中实现，提供清空接口
     */
    void clearAccessToken();
}
