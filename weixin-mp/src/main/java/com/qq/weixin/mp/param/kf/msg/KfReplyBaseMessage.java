package com.qq.weixin.mp.param.kf.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.qq.weixin.mp.param.kf.KfAccountSi;

/**
 * 客服恢复的基础消息类型
 * 
 * 发送文本消息
 * {
 *     "touser":"OPENID",
 *     "msgtype":"text",
 *     "text":
 *     {
 *          "content":"Hello World"
 *     }
 *     "customservice":
 *     {
 *          "kf_account": "test1@kftest"
 *     }
 * }
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class KfReplyBaseMessage {

    /**
     * 发送给用户的地址
     */
    private String touser;
    
    /**
     * 消息的类型
     */
    private String msgtype;
    
    /**
     * 客服信息
     */
    private KfAccountSi customservice;

    /**
     * 获取发送给用户的地址
     * @return the touser
     */
    public String getTouser() {
        return touser;
    }

    /**
     * 设定发送给用户的地址
     * @param touser the touser to set
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }

    /**
     * 获取消息的类型
     * @return the msgtype
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * 设定消息的类型
     * @param msgtype the msgtype to set
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    /**
     * 获取客服信息
     * @return the customservice
     */
    public KfAccountSi getCustomservice() {
        return customservice;
    }

    /**
     * 设定客服信息
     * @param customservice the customservice to set
     */
    public void setCustomservice(KfAccountSi customservice) {
        this.customservice = customservice;
    }
    
    
}
