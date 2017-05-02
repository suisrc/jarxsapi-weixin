package com.qq.weixin.open.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * 切片拦截异常
 * 进行必要的异常拦截
 * 防止异常内容传向客户端
 * @author Y13
 *
 */
@Provider
public class ResponseExceptionMapper implements ExceptionMapper<ResponseException> {

	/**
	 * 创建响应内容
	 */
	@Override
	public Response toResponse(ResponseException e) {
		return e.getResponse();
	}

}
