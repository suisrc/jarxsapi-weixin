package com.qq.weixin.mp.param.kf.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class ArticlesMedia {

    /**
     * 图文消息标题
     * 
     * 非必须
     */
    private String title;

    /**
     * 图文消息描述
     * 
     * 非必须
     */
    private String description;

    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * 
     * 非必须
     */
    @JsonProperty("picurl")
    private String picUrl;

    /**
     * 点击图文消息跳转链接
     * 
     * 非必须
     */
    private String url;

    /**
     * 获取图文消息标题 非必须
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定图文消息标题 非必须
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取图文消息描述 非必须
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定图文消息描述 非必须
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取图片链接，支持JPG、PNG格式，较好的效果为大图360 200，小图200 200 非必须
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设定图片链接，支持JPG、PNG格式，较好的效果为大图360 200，小图200 200 非必须
     * @param picUrl the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取点击图文消息跳转链接 非必须
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设定点击图文消息跳转链接 非必须
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    
}
