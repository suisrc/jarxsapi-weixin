package com.suisrc.weixin.mp.msg.msg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.WxMessage;

/**
 * 图片消息
 * <MsgType><![CDATA[image]]></MsgType>
 * 
 * <PicUrl><![CDATA[this is a url]]></PicUrl>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * @author Y13
 *
 */
@MpMsgType("image")
@JacksonXmlRootElement(localName="xml")
public class ImageMessage extends WxMessage {

    /**
     * 图片链接（由系统生成）
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "PicUrl")
    @JsonProperty("PicUrl")
    private String picUrl;

    /**
     * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MediaId")
    @JsonProperty("MediaId")
    private String mediaId;

    /**
     * 获取图片链接（由系统生成）
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 设定图片链接（由系统生成）
     * @param picUrl the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * @return the mediaId
     */
    public String getMediaId() {
        return mediaId;
    }

    /**
     * 设定图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     * @param mediaId the mediaId to set
     */
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
