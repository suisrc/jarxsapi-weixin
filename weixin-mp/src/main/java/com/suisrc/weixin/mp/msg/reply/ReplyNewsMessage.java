package com.suisrc.weixin.mp.msg.reply;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.ArticlesMedia;

/**
 * 图文消息
 * 
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>12345678</CreateTime>
 * <MsgType><![CDATA[news]]></MsgType>
 * <ArticleCount>2</ArticleCount>
 * <Articles>
 * <item>
 * <Title><![CDATA[title1]]></Title> 
 * <Description><![CDATA[description1]]></Description>
 * <PicUrl><![CDATA[picurl]]></PicUrl>
 * <Url><![CDATA[url]]></Url>
 * </item>
 * <item>
 * <Title><![CDATA[title]]></Title>
 * <Description><![CDATA[description]]></Description>
 * <PicUrl><![CDATA[picurl]]></PicUrl>
 * <Url><![CDATA[url]]></Url>
 * </item>
 * </Articles>
 * </xml>
 * 
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyNewsMessage extends BaseMessage {

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应 必须
     */
    @JacksonXmlElementWrapper(localName = "Articles")
    @JacksonXmlProperty(localName = "item")
    private List<ArticlesMedia> articles = new ArrayList<>();

    public ReplyNewsMessage() {
        setMsgType(WxMsgType.news.name());
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 图文消息个数，限制为8条以内
     */
    @JacksonXmlProperty(localName = "ArticleCount")
    public int getArticleCount() {
        return articles.size();
    }

    /**
     * 获取多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应 必须
     * @return the articles
     */
    public List<ArticlesMedia> getArticles() {
        return articles;
    }

    /**
     * 设定多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应 必须
     * @param articles the articles to set
     */
    public void setArticles(List<ArticlesMedia> articles) {
        this.articles = articles;
    }

}
