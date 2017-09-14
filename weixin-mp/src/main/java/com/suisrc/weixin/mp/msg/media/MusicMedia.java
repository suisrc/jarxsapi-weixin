package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
public class MusicMedia {

    /**
     * <Title><![CDATA[title]]></Title> 
     * 消息的标题
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    private String title;

    /**
     * <Description><![CDATA[description]]></Description> 
     * 消息的描述
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    private String description;

    /**
     * <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl> 
     * 音乐链接
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MusicUrl")
    @JsonProperty("musicurl")
    private String musicUrl;

    /**
     * <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl> 
     * 高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "HQMusicUrl")
    @JsonProperty("hqmusicurl")
    private String hQMusicUrl;

    /**
     * <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId> 
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "ThumbMediaId")
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    /**
     * 获取<Title><![CDATA[title]]>< Title> 消息的标题 非必须
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定<Title><![CDATA[title]]>< Title> 消息的标题 非必须
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取<Description><![CDATA[description]]>< Description> 消息的描述 非必须
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定<Description><![CDATA[description]]>< Description> 消息的描述 非必须
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取<MusicUrl><![CDATA[MUSIC_Url]]>< MusicUrl> 音乐链接 非必须
     * @return the musicUrl
     */
    public String getMusicUrl() {
        return musicUrl;
    }

    /**
     * 设定<MusicUrl><![CDATA[MUSIC_Url]]>< MusicUrl> 音乐链接 非必须
     * @param musicUrl the musicUrl to set
     */
    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    /**
     * 获取<HQMusicUrl><![CDATA[HQ_MUSIC_Url]]>< HQMusicUrl> 高质量音乐链接，WIFI环境优先使用该链接播放音乐 非必须
     * @return the hQMusicUrl
     */
    public String gethQMusicUrl() {
        return hQMusicUrl;
    }

    /**
     * 设定<HQMusicUrl><![CDATA[HQ_MUSIC_Url]]>< HQMusicUrl> 高质量音乐链接，WIFI环境优先使用该链接播放音乐 非必须
     * @param hQMusicUrl the hQMusicUrl to set
     */
    public void sethQMusicUrl(String hQMusicUrl) {
        this.hQMusicUrl = hQMusicUrl;
    }

    /**
     * 获取<ThumbMediaId><![CDATA[media_id]]>< ThumbMediaId> 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @return the thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设定<ThumbMediaId><![CDATA[media_id]]>< ThumbMediaId> 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @param thumbMediaId the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
