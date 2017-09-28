package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 图文消息，一个图文消息支持1到8条图文
 * @author Y13
 * 
 * POST数据示例如下：
 * {
 *    "articles": [
 *          {
 *              "thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
 *              "author":"xxx",
 *              "title":"Happy Day",
 *              "content_source_url":"www.qq.com",
 *              "content":"content",
 *              "digest":"digest",
 *              "show_cover_pic":1
 *          },
 *          {
 *              "thumb_media_id":"qI6_Ze_6PtV7svjolgs-rN6stStuHIjs9_DidOHaj0Q-mwvBelOXCFZiq2OsIU-p",
 *              "author":"xxx",
 *              "title":"Happy Day",
 *              "content_source_url":"www.qq.com",
 *              "content":"content",
 *              "digest":"digest",
 *              "show_cover_pic":0
 *          }
 *    ]
 * }
 * 
 * 如果需要在群发图文中插入小程序，则在调用上传图文消息素材接口时，需在content字段中添加小程序跳转链接，有以下三种样式的可供选择。
 * 
 * 小程序卡片跳转小程序，代码示例：
 * <mp-miniprogram data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index/index" data-miniprogram-title="小程序示例" data-progarm-imageurl="http://mmbizqbic.cn/demo.jpg"></mp-miniprogram>
 * 文字跳转小程序，代码示例：
 * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href="">点击文字跳转小程序</a></p>
 * 图片跳转小程序，代码示例：
 * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href=""><img data-src="http://mmbiz.qpic.cn/mmbiz_jpg/demo/0?wx_fmt=jpg"></a></p>
 * 
 * 参数.                是否必须.        说明
 * Articles            是.             图文消息，一个图文消息支持1到8条图文
 * 
 * thumb_media_id      是.             图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
 * author              否.             图文消息的作者
 * title               是.             图文消息的标题
 * content_source_url  否.             在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
 * content             是.             图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
 * digest              否.             图文消息的描述，如本字段为空，则默认抓取正文前64个字
 * show_cover_pic      否.             是否显示封面，1为显示，0为不显示

 */
public class Uploadnews {

    /**
     * 图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;
    
    /**
     * 图文消息的作者
     */
    private String author;
    
    /**
     * 图文消息的标题
     */
    private String title;
    
    /**
     * 在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
     */
    @JsonProperty("content_source_url")
    private String contentSourceUrl;
    
    /**
     * 图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
     */
    private String content;
    
    /**
     * 图文消息的描述，如本字段为空，则默认抓取正文前64个字
     */
    private String digest;

    /**
     * 是否显示封面，1为显示，0为不显示
     */
    @JsonProperty("show_cover_pic")
    private String showCoverPic;

    /**
     * 获取图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     * @return the thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设定图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
     * @param thumbMediaId the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    /**
     * 获取图文消息的作者
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设定图文消息的作者
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取图文消息的标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定图文消息的标题
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
     * @return the contentSourceUrl
     */
    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    /**
     * 设定在图文消息页面点击“阅读原文”后的页面，受安全限制，如需跳转Appstore，可以使用itun.es或appsto.re的短链服务，并在短链后增加 #wechat_redirect 后缀。
     * @param contentSourceUrl the contentSourceUrl to set
     */
    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    /**
     * 获取图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设定图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用，如需插入小程序卡片，可参考下文。
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取图文消息的描述，如本字段为空，则默认抓取正文前64个字
     * @return the digest
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 设定图文消息的描述，如本字段为空，则默认抓取正文前64个字
     * @param digest the digest to set
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 获取是否显示封面，1为显示，0为不显示
     * @return the showCoverPic
     */
    public String getShowCoverPic() {
        return showCoverPic;
    }

    /**
     * 设定是否显示封面，1为显示，0为不显示
     * @param showCoverPic the showCoverPic to set
     */
    public void setShowCoverPic(String showCoverPic) {
        this.showCoverPic = showCoverPic;
    }
    
/*
 * 
 * 小程序卡片跳转小程序，代码示例：
 * <mp-miniprogram data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index/index" data-miniprogram-title="小程序示例" data-progarm-imageurl="http://mmbizqbic.cn/demo.jpg"></mp-miniprogram>
 * 文字跳转小程序，代码示例：
 * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href="">点击文字跳转小程序</a></p>
 * 图片跳转小程序，代码示例：
 * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href=""><img data-src="http://mmbiz.qpic.cn/mmbiz_jpg/demo/0?wx_fmt=jpg"></a></p>
 * 
 */
    
    /**
     * 小程序卡片跳转小程序
     * @param appid 小程序的AppID
     * @param path 小程序要打开的路径
     * @param title 小程序卡片的标题，不超过35个字
     * @param imageurl 小程序卡片的封面图链接，图片必须为1080*864像素
     */
    public void setMpMinipropram(String appid, String path, String title, String imageurl) {
        content = String.format("<mp-miniprogram data-miniprogram-appid=\"%s\" data-miniprogram-path=\"%s\" data-miniprogram-title=\"%s\" data-progarm-imageurl=\"%s\"></mp-miniprogram>", 
                appid, path, title, imageurl);
    }
    
    /**
     * 文字跳转小程序
     * @param appid
     * @param path
     * @param text
     */
    public void setPAText(String appid, String path, String text) {
        content = String.format("<p><a data-miniprogram-appid=\"%s\" data-miniprogram-path=\"%s\" href=\"%s\">%s</a></p>", 
                appid, path, "", text);
    }
    
    /**
     * 图片跳转小程序
     * @param appid
     * @param path
     * @param text
     */
    public void setPAImg(String appid, String path, String img) {
        content = String.format("<p><a data-miniprogram-appid=\"%s\" data-miniprogram-path=\"%s\" href=\"%s\"><img data-src=\"%s\"></a></p>", 
                appid, path, "", img);
    }
}
