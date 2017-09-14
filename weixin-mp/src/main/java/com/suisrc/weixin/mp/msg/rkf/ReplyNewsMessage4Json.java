package com.suisrc.weixin.mp.msg.rkf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.mp.msg.media.ArticlesMedia;
import com.suisrc.weixin.mp.msg.reply.ReplyNewsMessage;

/**
 * 图文消息
 * 
 * JSON对象专用
 * 由于解析过程中，结构的不同，所以这里提供JSON专用的对象
 * 该类型不可用于xml解析出力，否则会出现解析重复问题
 */
public class ReplyNewsMessage4Json extends ReplyNewsMessage {

	@JsonIgnore
	public String getBaseUrl() {
		return super.getBaseUrl();
	}
	
	@JsonIgnore
	public String getFromUserName() {
		return super.getFromUserName();
	}
	
	@JsonIgnore
	public Integer getCreateTime() {
		return super.getCreateTime();
	}
	
	@JsonIgnore
	public int getArticleCount() {
		return super.getArticleCount();
	}
	
	@JsonIgnore
	public List<ArticlesMedia> getArticles() {
		return super.getArticles();
	}
	
	/**
	 * json解析使用
	 * @return
	 */
	@JsonProperty("news")@SuppressWarnings("unused")
	public Object getJsonNews() {
		return new Object() {public List<ArticlesMedia> getArticles() {return ReplyNewsMessage4Json.this.getArticles();}};
	}
	
}
