package com.qq.weixin.open.handler;

import javax.interceptor.InvocationContext;

/**
 * 拦截后执行的方法，进行 验证，验证的结果为null表示可以执行该方法
 * 
 * 因为放回结果的不确定性，所以，不进行结果放回，使用异常的方式记性处理
 * @author Y13
 *
 */
public interface WxOAuth2Handler {

    /**
     * 验证OAuth2令牌信息，如果没有，记性重定向，向微信服务器获取认证的令牌信息
     * @param ctx 
     * 
     */
    void checkAndGetOAuth2(InvocationContext ctx);

    /**
     * 重定向到微信服务器，获取令牌信息
     * @param ctx 
     * 
     */
    void getOAuth2(InvocationContext ctx);

    /**
     * 验证服务器上是有有 OAuth2令牌信息，如果没有，直接失败
     * @param ctx 
     * 
     */
    void checkOAuth2(InvocationContext ctx);
	
}
