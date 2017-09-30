package com.qq.weixin.mp.param.template;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 通过API推送订阅模板消息给到授权微信用户
 * 
 * 参数说明
 * 参数  是否必须    说明
 * touser  是   填接收消息的用户openid
 * template_id 是   订阅消息模板ID
 * url 否   点击消息跳转的链接，需要有ICP备案
 * scene   是   订阅场景值
 * title   是   消息标题，15字以内
 * data    是   消息正文，value为消息内容文本（200字以内），没有固定格式，可用\n换行，color为整段消息内容的字体颜色（目前仅支持整段消息为一种颜色）
 * @author Y13
 *
 */
public class SubscribeTemplateMsgParam extends TemplateMessageParam {
    
    /**
     *  订阅场景值
     */
    private String scene;
    
    /**
     * 消息标题，15字以内
     */
    private String title;
    
    /**
     * 获取订阅场景值
     * @return the scene
     */
    public String getScene() {
        return scene;
    }

    /**
     * 设定订阅场景值
     * @param scene the scene to set
     */
    public void setScene(String scene) {
        this.scene = scene;
    }

    /**
     * 获取消息标题，15字以内
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定消息标题，15字以内
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 禁用小程序的内容
     */
    @JsonIgnore
    @Deprecated
    @Override
    public MiniprogramInfo getMiniprogram() {
        return null;
    }
    
    /**
     * 禁用小程序的内容
     */
    @JsonIgnore
    @Deprecated
    @Override
    public void setMiniprogram(MiniprogramInfo miniprogram) {
    }

}
