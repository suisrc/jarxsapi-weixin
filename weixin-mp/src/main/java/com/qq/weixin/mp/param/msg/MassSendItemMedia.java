package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * media_id         是.         用于群发的消息的media_id
 * title            否.         消息的标题
 * description      否.         消息的描述
 * thumb_media_id   是.         视频缩略图的媒体ID
 * @author Y13
 *
 */
public class MassSendItemMedia implements MassSendItem {
    
    /**
     * 用于群发的消息的media_id
     */
    @JsonProperty("media_id")
    private String mediaId;
    
    /**
     * 获取用于群发的消息的media_id
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设定用于群发的消息的media_id
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
