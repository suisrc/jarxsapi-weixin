package com.qq.weixin.mp.param.kf.msg;

import com.qq.weixin.mp.param.kf.media.MediaId;
import com.suisrc.weixin.mp.msg.WxMsgType;

/**
 * 图片
 * 
 * JSON解析
 */
public class KfReplyImageMessage extends KfReplyBaseMessage {

    /**
     * 图片消息
     */
    private MediaId image;

    public KfReplyImageMessage() {
        setMsgtype(WxMsgType.image.name());
    }
    
    /**
     * 获取图片消息
     * @return the image
     */
    public MediaId getImage() {
        return image;
    }

    /**
     * 设定图片消息
     * @param image the image to set
     */
    public void setImage(MediaId image) {
        this.image = image;
    }
    
}
