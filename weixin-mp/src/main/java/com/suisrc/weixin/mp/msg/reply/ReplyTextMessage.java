package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;

/**
 * 文本
 * <Content><![CDATA[你好]]></Content>
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyTextMessage extends BaseMessage {

    /**
     * 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） 必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Content")
    private String content;

    public ReplyTextMessage() {
        setMsgType(WxMsgType.text.name());
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） 必须
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设定回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示） 必须
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
