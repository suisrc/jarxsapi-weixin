package com.suisrc.weixin.mp.msg.msg;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.check.TypeRegexAssert;
import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.WxMessage;

/**
 * 未知类型的消息
 * @author Y13
 *
 */
@MpMsgType(value = ".+", priority = 4096, handler = TypeRegexAssert.class)
@JacksonXmlRootElement(localName="xml")
public class UnknowMessage extends WxMessage {
    
    /**
     * 未知属性列表
     */
    private Map<String, Object> other = new HashMap<>();
    
    /**
     * 设定未知属性
     * @param name
     * @param value
     */
    @JsonAnySetter
    public void other(String name,Object value) {  
        other.put(name,value);  
    }
    
    /**
     * 获取未知属性列表
     * @return
     */
    public Map<String, Object> other() {
        return other;
    }
}
