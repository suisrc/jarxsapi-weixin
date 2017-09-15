package com.suisrc.weixin.core;

import com.suisrc.jaxrsapi.core.Consts;

/**
 * 常数
 * 
 * @author Y13
 *
 */
public interface WxConsts extends Consts {

    /**
     * 测试标识
     */
    boolean DEBUG = Boolean.getBoolean("debug"); //$NON-NLS-N$
    /**
     * 微信公众号的appid
     */
    String APP_ID = "APP_ID"; //$NON-NLS-N$

    /**
     * 微信公众号的app corpSecret
     */
    String APP_SECRET = "APP_SECRET"; //$NON-NLS-N$

    /**
     * 微信公众号的 access token
     */
    String ACCESS_TOKEN = "ACCESS_TOKEN"; //$NON-NLS-N$

    /**
     * 微信公众号的 token
     */
    String TOKEN = "TOKEN"; //$NON-NLS-N$

    /**
     * 消息加解密密钥
     */
    String ENCODING_AES_KEY = "ENCODING_AES_KEY"; //$NON-NLS-N$

    /**
     * 加密方式
     */
    String ENCRYPT_TYPE_AES = "aes"; //$NON-NLS-N$

    /**
     * 当前登录用户open_id保存在cookie中的key //$NON-NLS-N$
     */
    String COOKIE_OPEN_ID = "openid"; //$NON-NLS-N$

    // ---------------------------------KEY---------------------------------//

    /**
     * access token 提前更新时间，该字段范围为0~7200
     */
    String KEY_ADVANCE_TIME = "com.qq.weixin.access-token.advance-time"; //$NON-NLS-N$
    
    /**
     * app language
     */
    String KEY_CORE_LANGUAGE_KEY = "com.qq.weixin.core.language"; //$NON-NLS-N$
    
    // -------------------------------APP INFO-----------------------------//
    /**
     * app id key
     */
    String KEY_CORE_APP_ID = "com.qq.weixin.core.endpoint.app-id"; //$NON-NLS-N$

    /**
     * app secret
     */
    String KEY_CORE_APP_SECRET = "com.qq.weixin.core.endpoint.app-secret"; //$NON-NLS-N$

    /**
     * token
     */
    String KEY_CORE_TOKEN = "com.qq.weixin.core.endpoint.token"; //$NON-NLS-N$

    /**
     * encoding aes key
     */
    String KEY_CORE_ENCODING_AES_KEY = "com.qq.weixin.core.endpoint.encoding-aes-key"; //$NON-NLS-N$

}
