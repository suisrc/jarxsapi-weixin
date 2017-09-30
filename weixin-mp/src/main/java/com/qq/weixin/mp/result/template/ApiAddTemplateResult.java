package com.qq.weixin.mp.result.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 在调用模板消息接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
 *     {
 *            "errcode":0,
 *            "errmsg":"ok",
 *            "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk"
 *     }
 * @author Y13
 *
 */
public class ApiAddTemplateResult extends WxErrCode {
    private static final long serialVersionUID = -5111664543845266156L;
    
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
