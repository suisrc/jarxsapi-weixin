package com.qq.weixin.open.handler;

import com.qq.weixin.open.result.OAuth2AccessToken;

/**
 * 网页授权的Access token管理
 * @author Y13
 *
 */
public interface AccessTokenHandler {

	/**
	 * 获取网页授权的access token
	 * @param openid
	 * @return
	 */
	OAuth2AccessToken getByOpenid(String openid);

	/**
	 * 获取网页授权的access token
	 * @param code
	 * @return
	 */
	OAuth2AccessToken getByCode(String code);

}
