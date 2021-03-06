package com.qq.weixin.mp.param.kf.msg;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qq.weixin.mp.param.kf.media.ArticlesMedia;

/**
 * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内
 * 
 * JSON解析
 */
public class KfReplyNewsMessage extends KfReplyBaseMessage {
    
    /**
     * 消息,用于转接消息类型的内容
     */
    public static class News {
        private List<ArticlesMedia> articles;
        public List<ArticlesMedia> getArticles() {
            return articles;
        }
        public void setArticles(List<ArticlesMedia> articles) {
            this.articles = articles;
        }
    }

    /**
     * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     */
    private News news;
    
    public KfReplyNewsMessage() {
        // setMsgtype(WxMsgType.news.name());
        setMsgtype("news");
    }

    /**
     * 获取发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     * @return the news
     */
    public News getNews() {
        return news;
    }

    /**
     * 设定发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     * @param news the news to set
     */
    public void setNews(News news) {
        this.news = news;
    }

    /**
     * 设定发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     * @param news the news to set
     */
    @JsonIgnore
    public void setArticlesMedias(List<ArticlesMedia> news) {
        this.news = new News();
        this.news.setArticles(news);
    }

}
