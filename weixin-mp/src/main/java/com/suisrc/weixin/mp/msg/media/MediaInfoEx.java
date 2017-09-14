package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class MediaInfoEx extends MediaInfo {

    /**
     * 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

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
