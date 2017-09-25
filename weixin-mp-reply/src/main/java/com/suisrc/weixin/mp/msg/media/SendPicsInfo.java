package com.suisrc.weixin.mp.msg.media;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 发送的图片信息
 * <SendPicsInfo>
 *   <Count>1</Count>
 *   <PicList>
 *     <item>
 *       <PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>
 *     </item>
 *   </PicList>
 * </SendPicsInfo>
 * @author Y13
 *
 */
public class SendPicsInfo {

    /**
     * 图片列表
     */
    @JacksonXmlElementWrapper(localName = "PicList")
    @JacksonXmlProperty(localName = "item")
    @JsonProperty("PicList")
    private List<PicMedia> picList = new ArrayList<>();

    /**
     * 发送的图片数量
     */
    @JacksonXmlProperty(localName = "Count")
    @JsonProperty("Count")
    private Integer count;

    /**
     * 获取图片列表
     * @return the picList
     */
    public List<PicMedia> getPicList() {
        return picList;
    }

    /**
     * 设定图片列表
     * @param picList the picList to set
     */
    public void setPicList(List<PicMedia> picList) {
        this.picList = picList;
    }

    /**
     * 获取发送的图片数量
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设定发送的图片数量
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }
    
}
