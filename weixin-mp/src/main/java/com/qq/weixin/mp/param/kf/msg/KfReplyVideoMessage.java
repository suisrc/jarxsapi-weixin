package com.qq.weixin.mp.param.kf.msg;

import com.qq.weixin.mp.param.kf.media.MediaInfo;

/**
 * 发送视频消息
 * 
 * JSON解析
 */
public class KfReplyVideoMessage extends KfReplyBaseMessage {

    /**
     * 视频消息
     */
    private MediaInfo video;
    
    public KfReplyVideoMessage() {
        // setMsgtype(WxMsgType.video.name());
        setMsgtype("video");
    }

    /**
     * 获取视频消息
     * @return the video
     */
    public MediaInfo getVideo() {
        return video;
    }

    /**
     * 设定视频消息
     * @param video the video to set
     */
    public void setVideo(MediaInfo video) {
        this.video = video;
    }
    
}
