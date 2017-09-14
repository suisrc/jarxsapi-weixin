package com.suisrc.weixin.mp.msg.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.base.WxMessage;

/**
 * 文本消息
 * <MsgType><![CDATA[text]]></MsgType>
 * 
 * <Content><![CDATA[this is a test]]></Content>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class TextMessage extends WxMessage {

    /**
     * 文本消息内容
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Content")
    private String content;

    /**
     * 获取文本消息内容
     * 
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设定文本消息内容
     * 
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

}
