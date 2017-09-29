package com.qq.weixin.mp.result.template;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * {"first_class":"运输与仓储","second_class":"快递"},
 * 
 * @author Y13
 *
 */
public class IndustryInfo {

    /**
     * 主行业
     */
    @JsonProperty("first_class")
    private String firstClass;

    /**
     * 副行业
     */
    @JsonProperty("second_class")
    private String secondClass;

    /**
     * 获取主行业
     * @return the firstClass
     */
    public String getFirstClass() {
        return firstClass;
    }

    /**
     * 设定主行业
     * @param firstClass the firstClass to set
     */
    public void setFirstClass(String firstClass) {
        this.firstClass = firstClass;
    }

    /**
     * 获取副行业
     * @return the secondClass
     */
    public String getSecondClass() {
        return secondClass;
    }

    /**
     * 设定副行业
     * @param secondClass the secondClass to set
     */
    public void setSecondClass(String secondClass) {
        this.secondClass = secondClass;
    }
    
    
}
