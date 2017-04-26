package com.suisrc.weixin.core.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 
 * 多媒体信息
 * 发送给微信服务器时候，提供的多媒体信息
 * @author Y13
 *
 */
public class ArticlesMedia {

	/**
	 * <Title><![CDATA[title]]></Title>
	 * 图文消息标题
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Title")
	@JsonProperty("title")
	private String title;
	
	/**
	 * <Description><![CDATA[description]]></Description>
	 * 图文消息描述
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Description")
	@JsonProperty("description")
	private String description;

	/**
	 * <PicUrl><![CDATA[picurl]]></PicUrl>
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="PicUrl")
	@JsonProperty("picurl")
	private String picUrl;
	
	/**
	 * <Url><![CDATA[url]]></Url>
	 * 点击图文消息跳转链接
	 * 
	 * 非必须
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Url")
	@JsonProperty("url")
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
