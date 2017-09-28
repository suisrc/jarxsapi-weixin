package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * {
 *    "msg_id": "201053012"
 * }
 * 参数  说明
 * msg_id  群发消息后返回的消息id
 * @author Y13
 *
 */
public class MassGetParam {

    /**
     * 群发消息后返回的消息id
     */
    @JsonProperty("msg_id")
    private String msgId;

    /**
     * 获取群发消息后返回的消息id
     * @return the msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设定群发消息后返回的消息id
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
}
