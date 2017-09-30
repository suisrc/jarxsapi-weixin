package com.qq.weixin.mp.proxy;

import com.suisrc.jaxrsapi.core.runtime.ReviseHandler;

/**
 * 微信跳转后缀
 * 增加#wechat_redirect后缀
 * @author Y13
 *
 */
public class WeChatRedirectHandler implements ReviseHandler<String> {

	@Override
	public String accept(String value) {
		return value == null ? null : value + "#wechat_redirect";
	}

}
