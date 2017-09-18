package com.qq.weixin.mp.result.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 子菜单的 图文消息的信息
 * @author Y13
 */
public class SelfSubButtonNewsInfo {

    /**
     * 图文消息的标题
     */
    private String title;
    
    /**
     * 摘要
     */
    private String digest;
    
    /**
     * 作者
     */
    private String author;
    
    /**
     * 是否显示封面，0为不显示，1为显示
     */
    @JsonProperty("show_cover")
    private String showCover;
    
    /**
     * 封面图片的URL
     */
    @JsonProperty("show_url")
    private String showUrl;
    
    /**
     * 正文的URL
     */
    @JsonProperty("content_url")
    private String contentUrl;
    
    /**
     * 原文的URL，若置空则无查看原文入口
     */
    @JsonProperty("source_url")
    private String shourceUrl;

    /**
     * 获取图文消息的标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定图文消息的标题
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取摘要
     * @return the digest
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 设定摘要
     * @param digest the digest to set
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 获取作者
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设定作者
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取是否显示封面，0为不显示，1为显示
     * @return the showCover
     */
    public String getShowCover() {
        return showCover;
    }

    /**
     * 设定是否显示封面，0为不显示，1为显示
     * @param showCover the showCover to set
     */
    public void setShowCover(String showCover) {
        this.showCover = showCover;
    }

    /**
     * 获取封面图片的URL
     * @return the showUrl
     */
    public String getShowUrl() {
        return showUrl;
    }

    /**
     * 设定封面图片的URL
     * @param showUrl the showUrl to set
     */
    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    /**
     * 获取正文的URL
     * @return the contentUrl
     */
    public String getContentUrl() {
        return contentUrl;
    }

    /**
     * 设定正文的URL
     * @param contentUrl the contentUrl to set
     */
    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    /**
     * 获取原文的URL，若置空则无查看原文入口
     * @return the shourceUrl
     */
    public String getShourceUrl() {
        return shourceUrl;
    }

    /**
     * 设定原文的URL，若置空则无查看原文入口
     * @param shourceUrl the shourceUrl to set
     */
    public void setShourceUrl(String shourceUrl) {
        this.shourceUrl = shourceUrl;
    }
}
