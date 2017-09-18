package com.qq.weixin.mp.param.kf.msg;

import com.qq.weixin.mp.param.kf.media.MusicMedia;

/**
 * 发送语音消息
 * 
 * JSON解析
 */
public class KfReplyMusicMessage extends KfReplyBaseMessage {

    /**
     * 音乐消息
     */
    private MusicMedia music;
    
    public KfReplyMusicMessage() {
        // setMsgtype(WxMsgType.music.name());
        setMsgtype("music");
    }

    /**
     * 获取音乐消息
     * @return the music
     */
    public MusicMedia getMusic() {
        return music;
    }

    /**
     * 设定音乐消息
     * @param music the music to set
     */
    public void setMusic(MusicMedia music) {
        this.music = music;
    }
    
}
