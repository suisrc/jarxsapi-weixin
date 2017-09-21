package com.suisrc.weixin.mp.msg.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.WxMessage;

/**
 * 链接消息
 * <MsgType><![CDATA[link]]></MsgType>
 * 
 * <Title><![CDATA[公众平台官网链接]]></Title>
 * <Description><![CDATA[公众平台官网链接]]></Description>
 * <Url><![CDATA[url]]></Url>
 * @author Y13
 *
 */
@MpMsgType("link")
@JacksonXmlRootElement(localName="xml")
public class LinkMessage extends WxMessage {

    /**
     * 消息标题
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    @JsonProperty("Title")
    private String title;

    /**
     * 消息描述
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    @JsonProperty("Description")
    private String description;

    /**
     * 消息链接
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Url")
    @JsonProperty("Url")
    private String url;

    /**
     * 获取消息标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定消息标题
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取消息描述
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定消息描述
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取消息链接
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设定消息链接
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
