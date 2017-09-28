package com.qq.weixin.mp.result.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 查询群发消息发送状态【订阅号与服务号认证后均可用】
 * 
 * 
 * {
 *      "msg_id":201053012,
 *      "msg_status":"SEND_SUCCESS"
 * }
 * 参数.        说明
 * msg_id      群发消息后返回的消息id
 * msg_status  消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
 * @author Y13
 *
 */
public class MassGetResult extends WxErrCode {
    private static final long serialVersionUID = 3366720507639060075L;

    /**
     * 群发消息后返回的消息id
     */
    @JsonProperty("msg_id")
    private Long msgId;
    
    /**
     * 消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     */
    @JsonProperty("msg_status")
    private String msgStatus;

    /**
     * 获取群发消息后返回的消息id
     * @return the msgId
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * 设定群发消息后返回的消息id
     * @param msgId the msgId to set
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     * @return the msgStatus
     */
    public String getMsgStatus() {
        return msgStatus;
    }

    /**
     * 设定消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     * @param msgStatus the msgStatus to set
     */
    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }
    
}
