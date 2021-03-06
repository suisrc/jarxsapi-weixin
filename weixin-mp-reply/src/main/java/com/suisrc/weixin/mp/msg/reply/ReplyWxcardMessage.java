package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.MediaId;

/**
 * 微信卡券消息
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyWxcardMessage extends BaseMessage {

    /**
     * 微信卡券 必须
     */
    @JacksonXmlProperty(localName = "Wxcard")
    @JsonProperty("Wxcard")
    private MediaId wxcard;

    public ReplyWxcardMessage() {
        // setMsgType(WxMsgType.wxcard.name());
        setMsgType("wxcard");
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取微信卡券 必须
     * @return the wxcard
     */
    public MediaId getWxcard() {
        return wxcard;
    }

    /**
     * 设定微信卡券 必须
     * @param wxcard the wxcard to set
     */
    public void setWxcard(MediaId wxcard) {
        this.wxcard = wxcard;
    }

}
