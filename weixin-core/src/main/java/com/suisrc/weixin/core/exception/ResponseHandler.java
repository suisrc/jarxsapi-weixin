package com.suisrc.weixin.core.exception;

import javax.ws.rs.core.Response;

/**
 * 响应内容构建句柄
 * 
 * @author Y13
 *
 */
@FunctionalInterface
public interface ResponseHandler {

    /**
     * 创建响应内容
     * 
     * @param re
     * @return
     */
    Response build(ResponseException re);

}
