package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.MediaId;

/**
 * 语音
 * <Voice>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Voice>
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyVoiceMessage extends BaseMessage {


    /**
     * 语音信息 通过素材管理中的接口上传多媒体文件，得到的id 必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Voice")
    @JsonProperty("Voice")
    private MediaId voice;

    public ReplyVoiceMessage() {
        setMsgType(WxMsgType.voice.name());
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取语音信息 通过素材管理中的接口上传多媒体文件，得到的id 必须
     * @return the voice
     */
    public MediaId getVoice() {
        return voice;
    }

    /**
     * 设定语音信息 通过素材管理中的接口上传多媒体文件，得到的id 必须
     * @param voice the voice to set
     */
    public void setVoice(MediaId voice) {
        this.voice = voice;
    }

}
