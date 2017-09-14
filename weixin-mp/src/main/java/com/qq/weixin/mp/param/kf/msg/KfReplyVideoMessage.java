package com.qq.weixin.mp.param.kf.msg;

import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.media.MediaInfoEx;

/**
 * 发送视频消息
 * 
 * JSON解析
 */
public class KfReplyVideoMessage extends KfReplyBaseMessage {

    /**
     * 视频消息
     */
    private MediaInfoEx video;
    
    public KfReplyVideoMessage() {
        setMsgtype(WxMsgType.video.name());
    }

    /**
     * 获取视频消息
     * @return the video
     */
    public MediaInfoEx getVideo() {
        return video;
    }

    /**
     * 设定视频消息
     * @param video the video to set
     */
    public void setVideo(MediaInfoEx video) {
        this.video = video;
    }
    
}
