package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 小程序widget消息
 * 
 * <Event><![CDATA[wxa_widget_data]]></Event>
 * <Query><![CDATA[1]]></Query>
 * <Scene>1</Scene></xml>
 * 
 * 事件类型，小程序widget消息固定为wxa_widget_data
 * @author Y13
 *
 */
@MpEvent("wxa_widget_data")
@JacksonXmlRootElement(localName = "xml")
public class WxaWidgetDataEvent extends WxEventMessage {

    /**
     * 事件KEY值，与自定义菜单接口中KEY值对应
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "EventKey")
    @JsonProperty("EventKey")
    private String eventKey;
    
    /**
     * 询问
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Query")
    @JsonProperty("Query")
    private String query;
    
    /**
     * 场景
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Scene")
    @JsonProperty("Scene")
    private String scene;

    /**
     * 获取事件KEY值，与自定义菜单接口中KEY值对应
     * @return the eventKey
     */
    public String getEventKey() {
        return eventKey;
    }

    /**
     * 设定事件KEY值，与自定义菜单接口中KEY值对应
     * @param eventKey the eventKey to set
     */
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    /**
     * 获取询问
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * 设定询问
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * 获取场景
     * @return the scene
     */
    public String getScene() {
        return scene;
    }

    /**
     * 设定场景
     * @param scene the scene to set
     */
    public void setScene(String scene) {
        this.scene = scene;
    }

}
