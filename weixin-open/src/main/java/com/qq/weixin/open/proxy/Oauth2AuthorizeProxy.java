package com.qq.weixin.open.proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 虚拟远程代理服务器
 * 
 * 看似远程访问，但是实际访问本地，返回本地处理结果
 * 
 * Oauth凭据认证的时候，需要跳转，这个时候，该接口需要返回需要跳转的URL地址
 * 
 * proxy方法为入口
 * @author Y13
 *
 */
public class Oauth2AuthorizeProxy {

	/**
	 * 执行Oauth2认证路径
	 * @param url
	 * @param appid
	 * @param redirectUri
	 * @param responseType
	 * @param scope
	 * @param state
	 * @return
	 */
	public String proxy(String url, String appid, String redirect_uri, String response_type, String scope, String state) {
		String uri = redirect_uri;
		try {
			uri = URLEncoder.encode(uri, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder sbir = new StringBuilder(url).append('?');
		sbir.append("appid=").append(appid).append('&');
		sbir.append("redirect_uri=").append(uri).append('&');
		sbir.append("response_type=").append(response_type).append('&');
		sbir.append("scope=").append(scope).append('&');
		sbir.append("state=").append(state);
		return sbir.toString();
		
	}
}
