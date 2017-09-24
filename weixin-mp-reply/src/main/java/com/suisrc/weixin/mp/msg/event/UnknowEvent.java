package com.suisrc.weixin.mp.msg.event;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.check.TypeTrueAssert;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 未知事件类型
 * @author Y13
 *
 */

@MpEvent(value = "", priority = "x", handler = TypeTrueAssert.class)
@JacksonXmlRootElement(localName="xml")
public class UnknowEvent extends WxEventMessage {

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
