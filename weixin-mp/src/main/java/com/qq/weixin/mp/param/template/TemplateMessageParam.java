package com.qq.weixin.mp.param.template;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 发送模板消息
 * 
 *       {
 *            "touser":"OPENID",
 *            "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
 *            "url":"http://weixin.qq.com/download",  
 *            "miniprogram":{
 *              "appid":"xiaochengxuappid12345",
 *              "pagepath":"index?foo=bar"
 *            },          
 *            "data":{
 *                    "first": {
 *                        "value":"恭喜你购买成功！",
 *                        "color":"#173177"
 *                    },
 *                    "keynote1":{
 *                        "value":"巧克力",
 *                        "color":"#173177"
 *                    },
 *                    "keynote2": {
 *                        "value":"39.8元",
 *                        "color":"#173177"
 *                    },
 *                    "keynote3": {
 *                        "value":"2014年9月22日",
 *                        "color":"#173177"
 *                    },
 *                    "remark":{
 *                        "value":"欢迎再次购买！",
 *                        "color":"#173177"
 *                    }
 *            }
 *        }
 * 
 * 参数说明
 * 
 * 参数.     是否必填.     说明
 * touser      是.       接收者openid
 * template_id 是.       模板ID
 * url.        否.       模板跳转链接
 * miniprogram 否.       跳小程序所需数据，不需跳小程序可不用传该数据
 * appid       是.       所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
 * pagepath    是.       所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
 * data        是.       模板数据
 * color       否.       模板内容字体颜色，不填默认为黑色
 * 
 * @author Y13
 *
 */
public class TemplateMessageParam {

    /**
     * 接收者openid
     */
    private String touser;
    
    /**
     * 模板ID
     */
    @JsonProperty("template_id")
    private String templateId;
    
    /**
     * 模板跳转链接
     */
    private String url;
    
    /**
     * 跳小程序所需数据，不需跳小程序可不用传该数据
     */
    private MiniprogramInfo miniprogram;
    
    /**
     * 模板数据
     */
    private Map<String, TemplateDataInfo> data;

    /**
     * 获取接收者openid
     * @return the touser
     */
    public String getTouser() {
        return touser;
    }

    /**
     * 设定接收者openid
     * @param touser the touser to set
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }

    /**
     * 获取模板ID
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * 设定模板ID
     * @param templateId the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    /**
     * 获取模板跳转链接
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设定模板跳转链接
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取跳小程序所需数据，不需跳小程序可不用传该数据
     * @return the miniprogram
     */
    public MiniprogramInfo getMiniprogram() {
        return miniprogram;
    }

    /**
     * 设定跳小程序所需数据，不需跳小程序可不用传该数据
     * @param miniprogram the miniprogram to set
     */
    public void setMiniprogram(MiniprogramInfo miniprogram) {
        this.miniprogram = miniprogram;
    }

    /**
     * 获取模板数据
     * @return the data
     */
    public Map<String, TemplateDataInfo> getData() {
        return data;
    }

    /**
     * 设定模板数据
     * @param data the data to set
     */
    public void setData(Map<String, TemplateDataInfo> data) {
        this.data = data;
    }
    
    /**
     * 增加模版内容
     */
    public void putData(String key, String value, String color) {
        TemplateDataInfo data = new TemplateDataInfo();
        data.setValue(value);
        data.setColor(color);
        this.data.put(key, data);
    }
}
