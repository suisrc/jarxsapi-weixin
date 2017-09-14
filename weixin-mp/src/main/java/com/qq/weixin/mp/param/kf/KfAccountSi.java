package com.qq.weixin.mp.param.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 客服信息
 * {
 *     "kf_account": "test1@test"
 * }
 * 
 * @author Y13
 *
 */
public class KfAccountSi {

    /**
     * 账户
     */
    @JsonProperty("kf_account")
    private String kfAccount;

    /**
     * 获取账户
     * @return the kfAccount
     */
    public String getKfAccount() {
        return kfAccount;
    }

    /**
     * 设定账户
     * @param kfAccount the kfAccount to set
     */
    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }
    
    
}
