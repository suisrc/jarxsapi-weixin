package com.qq.weixin.open.exception;

import javax.ws.rs.core.Response;

/**
 * Response异常处理
 * 用于返回特殊实体，比如异常处理和跳转，重定向等待
 * @author Y13
 *
 */
public class ResponseException extends RuntimeException {
	private static final long serialVersionUID = -7907484456973810206L;
	
	/**
	 * 返回结果集
	 */
	private ResponseHandler handler = null;
	
	public ResponseException(ResponseHandler handler){
		super("重置响应内容");
		this.handler = handler;
	}
	
	public Response getResponse() {
		return handler.build(this);
	}
	
}
