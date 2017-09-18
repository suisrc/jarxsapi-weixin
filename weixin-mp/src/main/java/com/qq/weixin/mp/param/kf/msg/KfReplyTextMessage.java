package com.qq.weixin.mp.param.kf.msg;

/**
 * 文本
 * 
 * JSON解析
 */
public class KfReplyTextMessage extends KfReplyBaseMessage {

    /**
     * 文本信息
     */
    private String text;
    
    public KfReplyTextMessage() {
        // setMsgtype(WxMsgType.text.name());
        setMsgtype("text");
    }

    /**
     * 获取文本信息
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * 设定文本信息
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

}
