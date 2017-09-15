package com.qq.weixin.mp.param.kf.msg;

/**
 * 客服恢复的基础消息类型
 * 
 * { "touser":"OPENID", "command":"Typing"} 
 * @author Y13
 *
 */
public class KfReplyCustomTyping {
    
    /**
     * 发送的用户地址
     */
    private String touser;
    
    /**
     * 发送的状态
     * "Typing"：对用户下发“正在输入"状态,"CancelTyping"：取消对用户的”正在输入"状态
     * 
     */
    private String command;

    /**
     * 获取发送的用户地址
     * @return the touser
     */
    public String getTouser() {
        return touser;
    }

    /**
     * 设定发送的用户地址
     * @param touser the touser to set
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }

    /**
     * 获取发送的状态 "Typing"：对用户下发“正在输入"状态,"CancelTyping"：取消对用户的”正在输入"状态
     * @return the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * 设定发送的状态 "Typing"：对用户下发“正在输入"状态,"CancelTyping"：取消对用户的”正在输入"状态
     * @param command the command to set
     */
    public void setCommand(String command) {
        this.command = command;
    }

}
