package com.qq.weixin.mp.param.menu;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 测试个性化菜单时候，用户的信息
 * @author Y13
 *
 */
public class ConditionalMenuUserParam {

    /**
     * user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     */
    @JsonProperty("user_id")
    private String userId;

    /**
     * 获取user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设定user_id可以是粉丝的OpenID，也可以是粉丝的微信号。
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
