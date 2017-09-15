package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
public class MediaInfo extends MediaId {

    /**
     * <Title><![CDATA[title]]></Title> 
     * 消息的标题
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    @JsonProperty("Title")
    private String title;

    /**
     * <Description><![CDATA[description]]></Description> 
     * 消息的描述
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    @JsonProperty("Description")
    private String description;

    /**
     * 获取<Title><![CDATA[title]]>< Title> 消息的标题 非必须
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定<Title><![CDATA[title]]>< Title> 消息的标题 非必须
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取<Description><![CDATA[description]]>< Description> 消息的描述 非必须
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定<Description><![CDATA[description]]>< Description> 消息的描述 非必须
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
