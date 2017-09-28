package com.qq.weixin.mp.param.msg;

/**
 * 
 * content         是.         用于群发的消息的media_id
 *
 */
public class MassSendItemText implements MassSendItem {
    
    /**
     * 用于群发的消息的content
     * 
     * text内容
     */
    private String content;

    /**
     * 获取用于群发的消息的content text内容
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设定用于群发的消息的content text内容
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
