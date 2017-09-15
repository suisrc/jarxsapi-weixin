package com.qq.weixin.mp.param.kf.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class MediaInfo extends MediaId {

    /**
     * 消息的标题
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    private String title;

    /**
     * 消息的描述
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    private String description;
    
    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    /**
     * 获取消息的标题 非必须
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定消息的标题 非必须
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取消息的描述 非必须
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定消息的描述 非必须
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @return the thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设定缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     * @param thumbMediaId the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

}
