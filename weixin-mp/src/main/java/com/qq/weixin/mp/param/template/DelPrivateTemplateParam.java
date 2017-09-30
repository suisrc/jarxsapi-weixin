package com.qq.weixin.mp.param.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除模板
 * {
 *      "template_id" : "Dyvp3-Ff0cnail_CDSzk1fIc6-9lOkxsQE7exTJbwUE"
 * }
 * 
 * @author Y13
 *
 */
public class DelPrivateTemplateParam {

    /**
     * 模版ID
     */
    @JsonProperty("template_id")
    private String templateId;

    /**
     * 获取模版ID
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * 设定模版ID
     * @param templateId the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
    
}
