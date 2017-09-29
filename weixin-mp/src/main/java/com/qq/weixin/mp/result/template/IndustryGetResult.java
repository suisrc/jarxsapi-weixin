package com.qq.weixin.mp.result.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 获取设置的行业信息
 * 
 * 
 * {
 * "primary_industry":{"first_class":"运输与仓储","second_class":"快递"},
 * "secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"}
 * }
 * 返回参数说明
 * 参数  是否必填说明
 * primary_industry    是.   帐号设置的主营行业
 * secondary_industry  是.   帐号设置的副营行业
 * @author Y13
 *
 */
public class IndustryGetResult {

    /**
     * 帐号设置的主营行业
     */
    @JsonProperty("primary_industry")
    private IndustryInfo primaryIndustry;

    /**
     * 帐号设置的副营行业
     */
    @JsonProperty("secondary_industry")
    private IndustryInfo secondaryIndustry;

    /**
     * 获取帐号设置的主营行业
     * @return the primaryIndustry
     */
    public IndustryInfo getPrimaryIndustry() {
        return primaryIndustry;
    }

    /**
     * 设定帐号设置的主营行业
     * @param primaryIndustry the primaryIndustry to set
     */
    public void setPrimaryIndustry(IndustryInfo primaryIndustry) {
        this.primaryIndustry = primaryIndustry;
    }

    /**
     * 获取帐号设置的副营行业
     * @return the secondaryIndustry
     */
    public IndustryInfo getSecondaryIndustry() {
        return secondaryIndustry;
    }

    /**
     * 设定帐号设置的副营行业
     * @param secondaryIndustry the secondaryIndustry to set
     */
    public void setSecondaryIndustry(IndustryInfo secondaryIndustry) {
        this.secondaryIndustry = secondaryIndustry;
    }
    
}
