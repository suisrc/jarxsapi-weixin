package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
@JsonRootName("item")
public class ArticlesMedia {

    /**
     * <Title><![CDATA[title]]></Title> 
     * 图文消息标题
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Title")
    @JsonProperty("Title")
    private String title;

    /**
     * <Description><![CDATA[description]]></Description> 
     * 图文消息描述
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Description")
    @JsonProperty("Description")
    private String description;

    /**
     * <PicUrl><![CDATA[picurl]]></PicUrl> 
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "PicUrl")
    @JsonProperty("PicUrl")
    private String picUrl;

    /**
     * <Url><![CDATA[url]]></Url> 
     * 点击图文消息跳转链接
     * 
     * 非必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Url")
    @JsonProperty("Url")
    private String url;

    /**
     * 获取<Title><![CDATA[title]]>< Title> 图文消息标题 非必须
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定<Title><![CDATA[title]]>< Title> 图文消息标题 非必须
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取<Description><![CDATA[description]]>< Description> 图文消息描述 非必须
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定<Description><![CDATA[description]]>< Description> 图文消息描述 非必须
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取<PicUrl><![CDATA[picurl]]>< PicUrl> 图片链接，支持JPG、PNG格式，较好的效果为大图360 200，小图200 200 非必须
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设定<PicUrl><![CDATA[picurl]]>< PicUrl> 图片链接，支持JPG、PNG格式，较好的效果为大图360 200，小图200 200 非必须
     * @param picUrl the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取<Url><![CDATA[url]]>< Url> 点击图文消息跳转链接 非必须
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设定<Url><![CDATA[url]]>< Url> 点击图文消息跳转链接 非必须
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
