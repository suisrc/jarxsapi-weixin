package com.qq.weixin.open.intercept;

import javax.interceptor.InvocationContext;

/**
 * 拦截后执行的方法，进行 验证，验证的结果为null表示可以执行该方法
 * 但是如果返回的结果不是null,则执行返回的结果
 * @author Y13
 *
 */
public interface WxOAuth2Handler {

	/**
	 * 对数据内容记性拦截，如果发生异常，则直接抛出异常
	 * 这样来跳过后面的处理
	 * @param ctx 
	 * 
	 */
	void intercept(InvocationContext ctx);
	
}
