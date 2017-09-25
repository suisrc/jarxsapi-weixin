package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 图片信息
 * <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
 * @author Y13
 *
 */
public class PicMedia {
    
    /**
     * 图片的MD5值，开发者若需要，可用于验证接收到图片
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "PicMd5Sum")
    @JsonProperty("PicMd5Sum")
    private String picMd5Sum;

    /**
     * 获取图片的MD5值，开发者若需要，可用于验证接收到图片
     * @return the picMd5Sum
     */
    public String getPicMd5Sum() {
        return picMd5Sum;
    }

    /**
     * 设定图片的MD5值，开发者若需要，可用于验证接收到图片
     * @param picMd5Sum the picMd5Sum to set
     */
    public void setPicMd5Sum(String picMd5Sum) {
        this.picMd5Sum = picMd5Sum;
    }
    
}
