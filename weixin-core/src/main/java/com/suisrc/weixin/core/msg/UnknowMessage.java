package com.suisrc.weixin.core.msg;

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

}
