package com.suisrc.weixin.mp.msg.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信消息
 *  <MsgId>1234567890123456</MsgId>
 * 
 * @author Y13
 */
public abstract class WxMessage extends BaseMessage {
    /**
     * 消息id，64位整型
     */
    @JacksonXmlProperty(localName = "MsgId")
    @JsonProperty("MsgId")
    private Long msgId;

    /**
     * 获取消息id，64位整型
     * 
     * @return the msgId
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * 设定消息id，64位整型
     * 
     * @param msgId the msgId to set
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

}
