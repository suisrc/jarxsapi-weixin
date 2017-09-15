package com.suisrc.weixin.mp.msg.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.suisrc.weixin.core.msg.IMessage;

/**
 * 微信消息
 * 抽象接口
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * </xml>
 * @author Y13
 */
//@JsonAutoDetect(getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE) // 禁止方法检测
//@JsonIgnoreProperties(ignoreUnknown=true) // 忽略位置属性
public abstract class BaseMessage implements IMessage {

    /**
     * 微信回调地址
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "URL")
    @JsonProperty("URL")
    private String baseUrl;

    /**
     * 开发者微信号
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "ToUserName")
    @JsonProperty("ToUserName")
    private String toUserName;

    /**
     * 发送方帐号（一个OpenID）
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "FromUserName")
    @JsonProperty("FromUserName")
    private String fromUserName;

    /**
     * 消息创建时间 （整型）
     */
    @JacksonXmlProperty(localName = "CreateTime")
    @JsonProperty("CreateTime")
    private Integer createTime;

    /**
     * 消息类型
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MsgType")
    @JsonProperty("MsgType")
    private String msgType;

    /**
     * 获取微信回调地址
     * @return the baseUrl
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 设定微信回调地址
     * @param baseUrl the baseUrl to set
     */
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 获取开发者微信号
     * @return the toUserName
     */
    public String getToUserName() {
        return toUserName;
    }

    /**
     * 设定开发者微信号
     * @param toUserName the toUserName to set
     */
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    /**
     * 获取发送方帐号（一个OpenID）
     * @return the fromUserName
     */
    public String getFromUserName() {
        return fromUserName;
    }

    /**
     * 设定发送方帐号（一个OpenID）
     * @param fromUserName the fromUserName to set
     */
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    /**
     * 获取消息创建时间 （整型）
     * @return the createTime
     */
    public Integer getCreateTime() {
        return createTime;
    }

    /**
     * 设定消息创建时间 （整型）
     * @param createTime the createTime to set
     */
    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取消息类型
     * @return the msgType
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     * 设定消息类型
     * @param msgType the msgType to set
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    
    /**
     * 是否格式化为json
     * 
     * 该字段不参与格式数据内容
     */
    private boolean isJson = false;
    
    /**
     * 是否格式化为json
     */
    @Override
    public boolean isJson() {
        return isJson;
    }
    
    /**
     * 设定格式化为json，默认情况下格式化为xml
     * @param isJson
     */
    @Override
    public void setJson(boolean isJson) {
        this.isJson = isJson;
    }

    /**
     * 发送和接受用户反转
     * 
     * @param msg
     */
    public <T extends BaseMessage> T reverse(T msg) {
        msg.setToUserName(this.getFromUserName());
        msg.setFromUserName(this.getToUserName());
        return msg;
    }
}
