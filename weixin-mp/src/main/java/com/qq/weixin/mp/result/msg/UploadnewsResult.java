package com.qq.weixin.mp.result.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 上传图文消息素材【订阅号与服务号认证后均可用】
 * @author Y13
 * 
 * {
 *    "type":"news",
 *    "media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ",
 *    "created_at":1391857799
 * }
 * 
 * 
 * 参数.       说明
 * type       媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息（news）
 * media_id   媒体文件/图文消息上传后获取的唯一标识
 * created_at 媒体文件上传时间
 * 
 * 错误时微信会返回错误码等信息，请根据错误码查询错误信息
 */
public class UploadnewsResult extends WxErrCode {
    private static final long serialVersionUID = 902675236684553949L;

    /**
     * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息（news）
     */
    private String type;
    
    /**
     * 媒体文件/图文消息上传后获取的唯一标识
     */
    @JsonProperty("media_id")
    private String mediaId;
    
    /**
     * 媒体文件上传时间
     */
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * 获取媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息（news）
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * 设定媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb），图文消息（news）
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取媒体文件 图文消息上传后获取的唯一标识
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设定媒体文件 图文消息上传后获取的唯一标识
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * 获取媒体文件上传时间
     * @return the createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 设定媒体文件上传时间
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
