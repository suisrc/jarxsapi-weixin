package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.suisrc.weixin.core.WxMsgNode;

/**
 * 无法解析的内容，获取解析出错的内容
 * @author Y13
 *
 */
public class UnknowMessage implements IMessage {

    /**
     * 是否格式化为json
     * 
     * 该字段不参与格式数据内容
     */
    private boolean isJson = false;
    
    /**
     * 原始数据内容
     */
    private String rawData2 = null;
    
    /**
     * 共通解析的消息内容
     * 
     * 该内容只有在消息内容无法解析的时候有效
     */
    @JsonIgnore
    private WxMsgNode rawNode2 = null;
    
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
     * 获取原始数据
     * 使用RawData2为了防止与微信以后内容重复
     */
    @Override
    public String getRawData2() {
        return rawData2;
    }
    
    /**
     * 设定原始内容
     */
    @Override
    @Deprecated
    public void setRawData2(String rawData2) {
        this.rawData2 = rawData2;
    }

    /**
     * 获取共通解析的消息内容 该内容只有在消息内容无法解析的时候有效
     * @return the rawNode2
     */
    @Deprecated
    public WxMsgNode getRawNode2() {
        return rawNode2;
    }

    /**
     * 设定共通解析的消息内容 该内容只有在消息内容无法解析的时候有效
     * @param rawNode2 the rawNode2 to set
     */
    @Deprecated
    public void setRawNode2(WxMsgNode rawNode2) {
        this.rawNode2 = rawNode2;
    }
    

}
