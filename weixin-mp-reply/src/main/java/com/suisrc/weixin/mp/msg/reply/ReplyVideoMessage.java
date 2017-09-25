package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.MediaInfo;

/**
 * 视频
 * <Video>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * </Video>
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyVideoMessage extends BaseMessage {

    /**
     * 视频信息 必须
     */
    @JacksonXmlProperty(localName = "Video")
    @JsonProperty("Video")
    private MediaInfo video;

    public ReplyVideoMessage() {
        // setMsgType(WxMsgType.video.name());
        setMsgType("video");
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取视频信息 必须
     * @return the video
     */
    public MediaInfo getVideo() {
        return video;
    }

    /**
     * 设定视频信息 必须
     * @param video the video to set
     */
    public void setVideo(MediaInfo video) {
        this.video = video;
    }

}
