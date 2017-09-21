package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 消息, 微信通信时候处理使用的消息对象接口
 * @author Y13
 *
 */
// json字段只负责传递数据格式，不记性数据格式化
// rawData2字段不负责内容传递，最好不要使用，只是微信数据验证使用， 微信发送到服务器的原始数据
@JsonIgnoreProperties({"json", "rawData2"})
@JsonInclude(Include.NON_NULL)
public interface IMessage {
    
    /**
     * 是否格式化为json
     */
    default boolean isJson() {
        // 默认使用xml格式
        return false;
    }
    
    /**
     * 设定格式化为json
     */
    default void setJson(boolean isJson) {
        // no operation
    }
    
    /**
     * 获取原始数据
     * 使用RawData2为了防止与微信以后内容重复
     */
    default String getRawData2() {
        return null;
    }
    
    /**
     * 设定原始内容
     */
    default void setRawData2(String content) {
        // no operation
    }
    
    //----------------------------------消息共通方法, 一下字段有可能无效
    
    /**
     * 消息的类型，未知消息，返回值为null
     * @return
     */
    @Deprecated
    default String getMsgType() {
        return null;
    }
    
    /**
     * 事件消息，非事件消息，返回值为null
     * @return
     */
    @Deprecated
    default String getEvent() {
        return null;
    }
    
    /**
     * 事件KEY, 没有事件KEY,返回值为null
     * @return
     */
    @Deprecated
    default String getEventKey() {
        return null;
    }
}
