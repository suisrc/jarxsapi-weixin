package com.qq.weixin.mp.param.kf;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 客服账户List
 * 
 * @author Y13
 *
 */
public class KfAccountList {
    
    /**
     * 客服账户集合
     */
    @JsonProperty("kf_list")
    private List<KfAccountEx> kfList;

    /**
     * 获取客服账户集合
     * @return the kfList
     */
    public List<KfAccountEx> getKfList() {
        return kfList;
    }

    /**
     * 设定客服账户集合
     * @param kfList the kfList to set
     */
    public void setKfList(List<KfAccountEx> kfList) {
        this.kfList = kfList;
    }
    
    

}
