package com.qq.weixin.mp.param.kf.xcx;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信小程序中的信息
 * 
 * 
 *     {
 *         "title":"title",
 *         "appid":"appid",
 *         "pagepath":"pagepath",
 *         "thumb_media_id":"thumb_media_id"
 *     }
 *     
 * @author Y13
 *
 */
public class MiniprogrampageInfo {
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 小程序的appid，要求小程序的appid需要与公众号有关联关系
     */
    private String appid;
    
    /**
     * 小程序的页面路径，跟app.json对齐，支持参数，比如pages/index/index?foo=bar
     */
    private String pagepath;
    
    /**
     * 缩略图/小程序卡片图片的媒体ID，小程序卡片图片建议大小为520*416
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    /**
     * 获取标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定标题
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取小程序的appid，要求小程序的appid需要与公众号有关联关系
     * @return the appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设定小程序的appid，要求小程序的appid需要与公众号有关联关系
     * @param appid the appid to set
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 获取小程序的页面路径，跟app.json对齐，支持参数，比如pages index index?foo=bar
     * @return the pagepath
     */
    public String getPagepath() {
        return pagepath;
    }

    /**
     * 设定小程序的页面路径，跟app.json对齐，支持参数，比如pages index index?foo=bar
     * @param pagepath the pagepath to set
     */
    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }

    /**
     * 获取缩略图 小程序卡片图片的媒体ID，小程序卡片图片建议大小为520 416
     * @return the thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设定缩略图 小程序卡片图片的媒体ID，小程序卡片图片建议大小为520 416
     * @param thumbMediaId the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    
}
