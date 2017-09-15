package com.qq.weixin.mp.param.kf.msg;

import com.qq.weixin.mp.param.kf.media.Wxcard;
import com.suisrc.weixin.mp.msg.WxMsgType;

/**
 * 发送卡券
 * 
 * JSON解析
 */
public class KfReplyWxcardMessage extends KfReplyBaseMessage {

    /**
     * 卡券
     */
    private Wxcard wxcard;

    public KfReplyWxcardMessage() {
        setMsgtype(WxMsgType.wxcard.name());
    }

    /**
     * 获取卡券
     * @return the wxcard
     */
    public Wxcard getWxcard() {
        return wxcard;
    }

    /**
     * 设定卡券
     * @param wxcard the wxcard to set
     */
    public void setWxcard(Wxcard wxcard) {
        this.wxcard = wxcard;
    }
    
}
