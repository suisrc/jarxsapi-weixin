package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.MusicMedia;

/**
 * 音乐
 * <Music>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
 * <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
 * <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
 * </Music>
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyMusicMessage extends BaseMessage {

    /**
     * 音乐内容 必须
     */
    @JacksonXmlProperty(localName = "Music")
    @JsonProperty("Music")
    private MusicMedia music;

    public ReplyMusicMessage() {
        // setMsgType(WxMsgType.music.name());
        setMsgType("music");
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取音乐内容 必须
     * @return the music
     */
    public MusicMedia getMusic() {
        return music;
    }

    /**
     * 设定音乐内容 必须
     * @param music the music to set
     */
    public void setMusic(MusicMedia music) {
        this.music = music;
    }

}
