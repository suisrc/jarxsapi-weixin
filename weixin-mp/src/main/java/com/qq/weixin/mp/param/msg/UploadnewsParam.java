package com.qq.weixin.mp.param.msg;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 上传图文消息素材【订阅号与服务号认证后均可用】
 * @author Y13
 * 
 */
public class UploadnewsParam {

    /**
     * 图文消息，一个图文消息支持1到8条图文
     */
    @JsonProperty("articles")
    private List<Uploadnews> articles;

    /**
     * 获取图文消息，一个图文消息支持1到8条图文
     * @return the articles
     */
    public List<Uploadnews> getArticles() {
        return articles;
    }

    /**
     * 设定图文消息，一个图文消息支持1到8条图文
     * @param articles the articles to set
     */
    public void setArticles(List<Uploadnews> articles) {
        this.articles = articles;
    }
    
}
