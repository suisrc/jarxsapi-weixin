package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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
@JacksonXmlRootElement(localName="xml")
public class LinkMessage extends WxMessage {

	/**
	 * 消息标题
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Title")
	private String title;

	/**
	 * 消息描述
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Description")
	private String description;

	/**
	 * 消息链接
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Url")
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
