package com.qq.weixin.mp.param.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * template_id_short  是.        模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
 * @author Y13
 *
 */
public class ApiAddTemplateParam {
    
    /**
     * 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
     */
    @JsonProperty("template_id_short")
    private String templateIdShort;

    /**
     * 获取模板库中模板的编号，有“TM ”和“OPENTMTM ”等形式
     * @return the templateIdShort
     */
    public String getTemplateIdShort() {
        return templateIdShort;
    }

    /**
     * 设定模板库中模板的编号，有“TM ”和“OPENTMTM ”等形式
     * @param templateIdShort the templateIdShort to set
     */
    public void setTemplateIdShort(String templateIdShort) {
        this.templateIdShort = templateIdShort;
    }
    
}
