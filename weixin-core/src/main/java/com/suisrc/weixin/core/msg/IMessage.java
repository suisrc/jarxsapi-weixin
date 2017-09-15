package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 消息, 微信通信时候处理使用的消息对象接口
 * @author Y13
 *
 */
@JsonIgnoreProperties("json") // json字段只负责传递数据格式，不记性数据格式化
public interface IMessage {
    
    /**
     * 是否格式化为json
     * @return
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

}
