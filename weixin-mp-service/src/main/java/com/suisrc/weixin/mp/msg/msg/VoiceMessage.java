package com.suisrc.weixin.mp.msg.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.WxMessage;

/**
 * 语音消息
 * <MsgType><![CDATA[voice]]></MsgType>
 * 
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Format><![CDATA[Format]]></Format>
 * <Recognition><![CDATA[腾讯微信团队]]></Recognition>
 * @author Y13
 *
 */
@MpMsgType("voice")
@JacksonXmlRootElement(localName="xml")
public class VoiceMessage extends WxMessage {

    /**
     * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MediaId")
    @JsonProperty("MediaId")
    private String mediaId;

    /**
     * 语音格式，如amr，speex等
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Format")
    @JsonProperty("Format")
    private String format;

    /**
     * 语音识别结果，UTF8编码
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Recognition")
    @JsonProperty("Recognition")
    private String recognition;

    /**
     * 获取语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设定语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * 获取语音格式，如amr，speex等
     * @return the format
     */
    public String getFormat() {
        return format;
    }

    /**
     * 设定语音格式，如amr，speex等
     * @param format the format to set
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 获取语音识别结果，UTF8编码
     * @return the recognition
     */
    public String getRecognition() {
        return recognition;
    }

    /**
     * 设定语音识别结果，UTF8编码
     * @param recognition the recognition to set
     */
    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

}
