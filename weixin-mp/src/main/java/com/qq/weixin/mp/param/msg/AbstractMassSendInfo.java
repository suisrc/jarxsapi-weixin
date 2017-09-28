package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 根据标签进行群发【订阅号与服务号认证后均可用】
 * @author Y13
 * 
 * 参数.                 是否必须.           说明
 * msgtype              是.                群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard。
 * send_ignore_reprint  是.                图文消息被判定为转载时，是否继续群发。1为继续群发（转载），0为停止群发。该参数默认为0。
 * clientmsgid          否.                开发者侧群发msgid，长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid。
 */
public abstract class AbstractMassSendInfo {
    
    /**
     * 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     */
    @JsonProperty("msgtype")
    private String msgtype;
    
    /**
     * 图文消息被判定为转载时，是否继续群发。1为继续群发（转载），0为停止群发。该参数默认为0。
     */
    @JsonProperty("send_ignore_reprint")
    private Integer sendIgnoreReprint;
    
    /**
     * 开发者侧群发msgid，长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid。
     */
    @JsonProperty("clientmsgid")
    private Integer clientmsgid;

    /**
     * 获取群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @return the msgtype
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * 设定群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @param msgtype the msgtype to set
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    /**
     * 获取图文消息被判定为转载时，是否继续群发。1为继续群发（转载），0为停止群发。该参数默认为0。
     * @return the sendIgnoreReprint
     */
    public Integer getSendIgnoreReprint() {
        return sendIgnoreReprint;
    }

    /**
     * 设定图文消息被判定为转载时，是否继续群发。1为继续群发（转载），0为停止群发。该参数默认为0。
     * @param sendIgnoreReprint the sendIgnoreReprint to set
     */
    public void setSendIgnoreReprint(Integer sendIgnoreReprint) {
        this.sendIgnoreReprint = sendIgnoreReprint;
    }

    /**
     * 获取开发者侧群发msgid，长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid。
     * @return the clientmsgid
     */
    public Integer getClientmsgid() {
        return clientmsgid;
    }

    /**
     * 设定开发者侧群发msgid，长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid。
     * @param clientmsgid the clientmsgid to set
     */
    public void setClientmsgid(Integer clientmsgid) {
        this.clientmsgid = clientmsgid;
    }
    
}
