package com.suisrc.weixin.core.exception;

import com.suisrc.weixin.core.bean.WxErrCode;
import com.suisrc.weixin.message.ErrCodeMsg;

/**
 * Response异常处理 用于返回特殊实体，比如异常处理和跳转，重定向等待
 * 
 * @author Y13
 *
 */
public class WxErrCodeException extends RuntimeException {
    private static final long serialVersionUID = -5509957337155851929L;
    
    public static WxErrCodeException err(WxErrCode err) {
        String msg = ErrCodeMsg.getErrCodeMsg(err.getErrcode(), err.getErrmsg());
        msg = "[" + err.getErrcode() + "]:" + msg;
        WxErrCodeException exception = new WxErrCodeException(err, msg);
        return exception;
    }
    
    /**
     * 错误编码
     */
    private WxErrCode err;

    /**
     * 微信处理异常
     * @param err
     */
    public WxErrCodeException(WxErrCode err, String msg) {
        super(msg);
        this.err = err;
    }

    /**
     * 获取错误编码
     * @return the err
     */
    public WxErrCode getErr() {
        return err;
    }
}
