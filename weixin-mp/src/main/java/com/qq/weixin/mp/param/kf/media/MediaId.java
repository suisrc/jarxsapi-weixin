package com.qq.weixin.mp.param.kf.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class MediaId {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。
     * 
     * 必须字段
     */
    @JsonProperty("media_id")
    private String mediaId;

    /**
     * 获取通过素材管理中的接口上传多媒体文件，得到的id。 必须字段
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设定通过素材管理中的接口上传多媒体文件，得到的id。 必须字段
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
