package com.qq.weixin.mp.param.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 客服信息
 * {
 *      "kf_account" : "test1@test",
 *      "nickname" : "客服1",
 *      "password" : "pswmd5",
 * }
 * @author Y13
 *
 */
public class KfAccount {

    /**
     * 客服账户
     */
    @JsonProperty("kf_account")
    private String kfAccount;
    
    /**
     * 客户昵称
     */
    private String nickname;
    
    /**
     * 客户密码
     */
    private String password;

    /**
     * 获取客服账户
     * @return the kfAccount
     */
    public String getKfAccount() {
        return kfAccount;
    }

    /**
     * 设定客服账户
     * @param kfAccount the kfAccount to set
     */
    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount;
    }

    /**
     * 获取客户昵称
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设定客户昵称
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取客户密码
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设定客户密码
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
