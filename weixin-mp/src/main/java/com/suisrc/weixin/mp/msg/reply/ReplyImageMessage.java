package com.suisrc.weixin.mp.msg.reply;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.msg.WxMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.media.MediaId;

/**
 * 图片
 * <Image>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * </Image>
 */
@JacksonXmlRootElement(localName = "xml")
public class ReplyImageMessage extends BaseMessage {

    /**
     * 通过素材管理中的接口上传多媒体文件，得到的id。 必须
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Image")
    @JsonProperty("Image")
    private MediaId image;

    public ReplyImageMessage() {
        setMsgType(WxMsgType.image.name());
        setCreateTime(Long.valueOf(System.currentTimeMillis() / 1000l).intValue());
    }

    /**
     * 获取通过素材管理中的接口上传多媒体文件，得到的id。 必须
     * @return the image
     */
    public MediaId getImage() {
        return image;
    }

    /**
     * 设定通过素材管理中的接口上传多媒体文件，得到的id。 必须
     * @param image the image to set
     */
    public void setImage(MediaId image) {
        this.image = image;
    }

}
