package com.qq.weixin.mp.param.kf.msg;

import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.media.MediaId;

/**
 * 发送语音消息
 * 
 * JSON解析
 */
public class KfReplyVoiceMessage extends KfReplyBaseMessage {

    /**
     * 语音消息
     */
    private MediaId voice;
    
    public KfReplyVoiceMessage() {
        setMsgtype(WxMsgType.voice.name());
    }

    /**
     * 获取语音消息
     * @return the voice
     */
    public MediaId getVoice() {
        return voice;
    }

    /**
     * 设定语音消息
     * @param voice the voice to set
     */
    public void setVoice(MediaId voice) {
        this.voice = voice;
    }
    
}
