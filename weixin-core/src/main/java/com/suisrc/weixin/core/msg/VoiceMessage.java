package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 语音消息
 * <MsgType><![CDATA[voice]]></MsgType>
 * 
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <Format><![CDATA[Format]]></Format>
 * <Recognition><![CDATA[腾讯微信团队]]></Recognition>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class VoiceMessage extends WxMessage {

	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="MediaId")
	private String mediaId;
	
	/**
	 * 语音格式，如amr，speex等
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Format")
	private String format;
	
	/**
	 * 语音识别结果，UTF8编码
	 */
	@JacksonXmlCData
	@JacksonXmlProperty(localName="Recognition")
	private String recognition;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	
}
