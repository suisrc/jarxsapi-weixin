package com.qq.weixin.mp.result.template;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取模板列表
 * {   
 *  "template_list": [{
 *       "template_id": "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s",
 *       "title": "领取奖金提醒",
 *       "primary_industry": "IT科技",
 *       "deputy_industry": "互联网|电子商务",
 *       "content": "{ {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖  时间:{ {withdrawTime.DATA} }\n银行信息:{ {cardInfo.DATA} }\n到账时间:  { {arrivedTime.DATA} }\n{ {remark.DATA} }",
 *       "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡"
 *    }]
 * }
 * 
 * @author Y13
 *
 */
public class AppPrivateTemplateListResult {

    /**
     * 模版列表
     */
    @JsonProperty("template_list")
    private List<AppPrivateTemplateInfo> templateList;

    /**
     * 获取模版列表
     * @return the templateList
     */
    public List<AppPrivateTemplateInfo> getTemplateList() {
        return templateList;
    }

    /**
     * 设定模版列表
     * @param templateList the templateList to set
     */
    public void setTemplateList(List<AppPrivateTemplateInfo> templateList) {
        this.templateList = templateList;
    }
    
}
