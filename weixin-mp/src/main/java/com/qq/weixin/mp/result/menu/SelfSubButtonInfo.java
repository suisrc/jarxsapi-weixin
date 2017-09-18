package com.qq.weixin.mp.result.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.qq.weixin.mp.param.menu.AbstractButtonInfo;

/**
 * 子菜单信息
 * @author Y13
 *
 */
public class SelfSubButtonInfo extends AbstractButtonInfo {
    
    /**
     * 对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单：
     * Text:保存文字到value； Img、voice：保存mediaID到value； Video：保存视频下载链接到value； News：保存图文消息到news_info，同时保存mediaID到value； View：保存链接到url。
     * 使用API设置的自定义菜单： click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、    pic_weixin、location_select：保存值到key；view：保存链接到url
     */
    private String value;
    
    /**
     * 图文消息的信息
     */
    @JsonProperty("news_info")
    private SelfSubButtonNewsInfo newsInfo;

    /**
     * 获取对于不同的菜单类型，value的值意义不同。
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设定对于不同的菜单类型，value的值意义不同。
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取图文消息的信息
     * @return the newsInfo
     */
    public SelfSubButtonNewsInfo getNewsInfo() {
        return newsInfo;
    }

    /**
     * 设定图文消息的信息
     * @param newsInfo the newsInfo to set
     */
    public void setNewsInfo(SelfSubButtonNewsInfo newsInfo) {
        this.newsInfo = newsInfo;
    }
    
}
