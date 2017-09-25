package com.qq.weixin.mp.result.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 菜单配置，按钮
 * @author Y13
 *
 */
public class SelfButtonInfo {


    /**
     * 菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice。使用API设置的则有8种，详见《自定义菜单创建接口》
     */
    private String type;
    
    /**
     * 菜单名称
     */
    private String name;
    
    /**
     * 对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单：
     * Text:保存文字到value； Img、voice：保存mediaID到value； Video：保存视频下载链接到value； News：保存图文消息到news_info，同时保存mediaID到value； 
     * 
     */
    private String value;
    
    /**
     * View：保存链接到url。
     * 使用API设置的自定义菜单：view：保存链接到url
     */
    private String url;
    
    /**
     * 使用API设置的自定义菜单： click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、 pic_weixin、location_select：保存值到key；
     */
    private String key;
    
    /**
     * 子菜单内容
     */
    @JsonProperty("sub_button")
    private SelfSubButtonList subButtons;

    /**
     * 获取菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * 设定菜单的类型，公众平台官网上能够设置的菜单类型有view（跳转网页）、text（返回文本，下同）、img、photo、video、voice
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取菜单名称
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * 设定菜单名称
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单：
     * Text:保存文字到value； 
     * Img、voice：保存mediaID到value； 
     * Video：保存视频下载链接到value；
     * News：保存图文消息到news_info，同时保存mediaID到value；
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设定对于不同的菜单类型，value的值意义不同。官网上设置的自定义菜单：
     * Text:保存文字到value； 
     * Img、voice：保存mediaID到value；
     * Video：保存视频下载链接到value；
     * News：保存图文消息到news_info，同时保存mediaID到value；
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取View：保存链接到url。 使用API设置的自定义菜单：view：保存链接到url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设定View：保存链接到url。 使用API设置的自定义菜单：view：保存链接到url
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取使用API设置的自定义菜单： click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、 pic_weixin、location_select：保存值到key；
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * 设定使用API设置的自定义菜单： click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、 pic_weixin、location_select：保存值到key；
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取子菜单内容
     * @return the subButtons
     */
    public SelfSubButtonList getSubButtons() {
        return subButtons;
    }

    /**
     * 设定子菜单内容
     * @param subButtons the subButtons to set
     */
    public void setSubButtons(SelfSubButtonList subButtons) {
        this.subButtons = subButtons;
    }
}
