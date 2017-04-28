package com.qq.weixin.open.handler;

import com.qq.weixin.open.OpenWxConsts;
import com.suisrc.jaxrsapi.core.func.ValueHandler;

/**
 * 微信跳转后缀
 * https://open.weixin.qq.com/connect/qrconnect?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect
 * 增加#wechat_redirect后缀
 * @author Y13
 *
 */
public class WeChatRedirectHandler implements ValueHandler<String> {

	@Override
	public String revise(String value) {
		return value == null ? null : value + OpenWxConsts.WECHAT_REDIRECT;
	}

}
