package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.MediaId;

/**
 * 发送图文消息（点击跳转到图文消息页面）
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyMpnewsMessage extends BaseMessage {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。 必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Mpnews")
    @JsonProperty("mpnews")
    private MediaId mpnews;

    public ReplyMpnewsMessage() {
        setMsgType(WxMsgType.mpnews.name());
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取通过素材管理中的接口上传多媒体文件，得到的id。 必须
     * @return the mpnews
     */
    public MediaId getMpnews() {
        return mpnews;
    }

    /**
     * 设定通过素材管理中的接口上传多媒体文件，得到的id。 必须
     * @param mpnews the mpnews to set
     */
    public void setMpnews(MediaId mpnews) {
        this.mpnews = mpnews;
    }


}
