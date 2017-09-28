package com.qq.weixin.mp.result.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 *  
 * {
 *    "errcode":0,
 *    "errmsg":"preview success",
 *    "msg_id":34182
 * }
 * 参数.说明
 * errcode 错误码
 * errmsg  错误信息
 * msg_id  消息ID
 * @author Y13
 *
 */
public class MassPreviewResult extends WxErrCode {
    private static final long serialVersionUID = 4290453905739290363L;
    
    /**
     * 消息ID
     */
    @JsonProperty("msg_id")
    private Long msgId;

    /**
     * 获取消息ID
     * @return the msgId
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * 设定消息ID
     * @param msgId the msgId to set
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
    
    
}
