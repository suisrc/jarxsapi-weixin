package com.qq.weixin.mp.result.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * 模版信息
 * {
 *       "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
 *       "title": "领取奖金提醒",
 *       "primary_industry": "IT科技",
 *       "deputy_industry": "互联网|电子商务",
 *       "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
 *       "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
 * }
 * 返回参数说明
 * 参数.           是否必填.      说明
 * template_id     是.          模板ID
 * title           是.          模板标题
 * primary_industry 是.         模板所属行业的一级行业
 * deputy_industry 是.          模板所属行业的二级行业
 * content         是.          模板内容
 * example         是.          模板示例
 * 
 * @author Y13
 *
 */
public class AppPrivateTemplateInfo {

    /**
     * 模板ID
     */
    @JsonProperty("template_id")
    private String templateId;
    
    /**
     * 模板标题
     */
    @JsonProperty("title")
    private String title;
    
    /**
     * 模板所属行业的一级行业
     */
    @JsonProperty("primary_industry")
    private String primaryIndustry;
    
    /**
     * 模板所属行业的二级行业
     */
    @JsonProperty("deputy_industry")
    private String deputyIndustry;
    
    /**
     * 模板内容
     */
    @JsonProperty("content")
    private String content;
    
    /**
     * 模板示例
     */
    @JsonProperty("example")
    private String example;

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
     * 获取模板标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定模板标题
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取模板所属行业的一级行业
     * @return the primaryIndustry
     */
    public String getPrimaryIndustry() {
        return primaryIndustry;
    }

    /**
     * 设定模板所属行业的一级行业
     * @param primaryIndustry the primaryIndustry to set
     */
    public void setPrimaryIndustry(String primaryIndustry) {
        this.primaryIndustry = primaryIndustry;
    }

    /**
     * 获取模板所属行业的二级行业
     * @return the deputyIndustry
     */
    public String getDeputyIndustry() {
        return deputyIndustry;
    }

    /**
     * 设定模板所属行业的二级行业
     * @param deputyIndustry the deputyIndustry to set
     */
    public void setDeputyIndustry(String deputyIndustry) {
        this.deputyIndustry = deputyIndustry;
    }

    /**
     * 获取模板内容
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设定模板内容
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取模板示例
     * @return the example
     */
    public String getExample() {
        return example;
    }

    /**
     * 设定模板示例
     * @param example the example to set
     */
    public void setExample(String example) {
        this.example = example;
    }
    
}
