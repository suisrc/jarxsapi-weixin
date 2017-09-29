package com.qq.weixin.mp.param.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 设置所属行业
 * 数据类型：
 *       {
 *           "industry_id1":"1",
 *           "industry_id2":"4"
 *        }
 *        
 * industry_id1    是.         公众号模板消息所属行业编号
 * industry_id2    是.         公众号模板消息所属行业编号
 * @author Y13
 *
 */
public class IndustrySetParam {

    /**
     * 公众号模板消息所属行业编号
     */
    @JsonProperty("industry_id1")
    private String industryId1;

    /**
     * 公众号模板消息所属行业编号
     */
    @JsonProperty("industry_id2")
    private String industryId2;

    /**
     * 获取公众号模板消息所属行业编号
     * @return the industryId1
     */
    public String getIndustryId1() {
        return industryId1;
    }

    /**
     * 设定公众号模板消息所属行业编号
     * @param industryId1 the industryId1 to set
     */
    public void setIndustryId1(String industryId1) {
        this.industryId1 = industryId1;
    }

    /**
     * 获取公众号模板消息所属行业编号
     * @return the industryId2
     */
    public String getIndustryId2() {
        return industryId2;
    }

    /**
     * 设定公众号模板消息所属行业编号
     * @param industryId2 the industryId2 to set
     */
    public void setIndustryId2(String industryId2) {
        this.industryId2 = industryId2;
    }
    
    
}
