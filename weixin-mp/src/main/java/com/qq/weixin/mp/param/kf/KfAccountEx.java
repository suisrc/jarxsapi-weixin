package com.qq.weixin.mp.param.kf;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 客服信息
 * {
 *     "kf_account": "test1@test", 
 *     "kf_nick": "ntest1", 
 *     "kf_id": "1001"
 *     "kf_headimgurl": " http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0"
 * }
 * 
 * @author Y13
 *
 */
public class KfAccountEx {

    /**
     * 账户
     */
    @JsonProperty("kf_account")
    private String kfAccount;
    
    /**
     * 昵称
     */
    @JsonProperty("kf_nick")
    private String kfNick;
    
    /**
     * 账户ID
     */
    @JsonProperty("kf_id")
    private String kfId;
    
    /**
     * 头像
     */
    @JsonProperty("kf_headimgurl")
    private String kfHeadimgurl;

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

    /**
     * 获取昵称
     * @return the kfNick
     */
    public String getKfNick() {
        return kfNick;
    }

    /**
     * 设定昵称
     * @param kfNick the kfNick to set
     */
    public void setKfNick(String kfNick) {
        this.kfNick = kfNick;
    }

    /**
     * 获取账户ID
     * @return the kfId
     */
    public String getKfId() {
        return kfId;
    }

    /**
     * 设定账户ID
     * @param kfId the kfId to set
     */
    public void setKfId(String kfId) {
        this.kfId = kfId;
    }

    /**
     * 获取头像
     * @return the kfHeadimgurl
     */
    public String getKfHeadimgurl() {
        return kfHeadimgurl;
    }

    /**
     * 设定头像
     * @param kfHeadimgurl the kfHeadimgurl to set
     */
    public void setKfHeadimgurl(String kfHeadimgurl) {
        this.kfHeadimgurl = kfHeadimgurl;
    }
    
    
}
