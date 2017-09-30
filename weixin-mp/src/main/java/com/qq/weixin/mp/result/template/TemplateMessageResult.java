package com.qq.weixin.mp.result.template;

/**
 * 发送模板消息
 * 
 * 在调用模板消息接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
 *     {
 *            "errcode":0,
 *            "errmsg":"ok",
 *            "msgid":200228332
 *     }
 * @author Y13
 *
 */
public class TemplateMessageResult {

    /**
     * 消息ID
     */
    private Long msgid;

    /**
     * 获取消息ID
     * @return the msgid
     */
    public Long getMsgid() {
        return msgid;
    }

    /**
     * 设定消息ID
     * @param msgid the msgid to set
     */
    public void setMsgid(Long msgid) {
        this.msgid = msgid;
    }
    
}
