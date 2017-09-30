package com.suisrc.weixin.core.exception;

import javax.ws.rs.core.Response;

import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * Response异常处理 用于返回特殊实体，比如异常处理和跳转，重定向等待
 * 
 * @author Y13
 *
 */
public class ResponseException extends RuntimeException {
    private static final long serialVersionUID = -7907484456973810206L;

    /**
     * 返回结果集
     */
    private ResponseHandler handler = null;

    /**
     * 异常
     */
    private WxErrCode errCode = null;

    public ResponseException(String cause, String errmsg) {
        this(cause, errmsg, null);
    }

    public ResponseException(String cause, String errmsg, Long errcode) {
        super(cause);
        errCode = new WxErrCode();
        errCode.setErrmsg(errmsg);
        errCode.setErrcode(errcode == null ? 10099L: errcode);
    }

    public ResponseException(String cause, ResponseHandler handler) {
        super(cause);
        this.handler = handler;
    }

    /**
     * 获取重置内容
     * 
     * @return
     */
    public Response getResponse() {
        Response response;
        if (handler != null) {
            response = handler.build(this);
        } else if (errCode != null) {
            response = Response.ok(errCode).build();
        } else {
            response = Response.ok(getMessage()).build();
        }
        return response;
    }

}
