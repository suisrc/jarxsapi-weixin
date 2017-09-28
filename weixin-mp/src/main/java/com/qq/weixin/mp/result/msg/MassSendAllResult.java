package com.qq.weixin.mp.result.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 根据标签进行群发【订阅号与服务号认证后均可用】
 * {
 *    "errcode":0,
 *    "errmsg":"send job submission success",
 *    "msg_id":34182, 
 *    "msg_data_id": 206227730
 * }
 * @author Y13
 *
 */
public class MassSendAllResult extends WxErrCode {
    private static final long serialVersionUID = 8683401521637614717L;

    /**
     * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     */
    private String type;
    
    /**
     *  消息发送任务的ID
     */
    @JsonProperty("msg_id")
    private String msgId;
    
    /**
     * 消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中，
     * 获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     */
    @JsonProperty("msg_data_id")
    private String msgDataId;

    /**
     * 获取媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * 设定媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息为news
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取消息发送任务的ID
     * @return the msgId
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     * 设定消息发送任务的ID
     * @param msgId the msgId to set
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中， 
     * 获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * @return the msgDataId
     */
    public String getMsgDataId() {
        return msgDataId;
    }

    /**
     * 设定消息的数据ID，该字段只有在群发图文消息时，才会出现。可以用于在图文分析数据接口中， 
     * 获取到对应的图文消息的数据，是图文分析数据接口中的msgid字段中的前半部分，详见图文分析数据接口中的msgid字段的介绍。
     * @param msgDataId the msgDataId to set
     */
    public void setMsgDataId(String msgDataId) {
        this.msgDataId = msgDataId;
    }
    
}
