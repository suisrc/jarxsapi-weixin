package com.suisrc.weixin.core;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.suisrc.weixin.core.msg.BaseMessage;

/**
 * 消息工厂
 * 
 * @author Y13
 *
 */
public class WxMsgFactory {

    /**
     * 每一个线程上一个XmlMapper，加快分析进度。
     */
    private static final ThreadLocal<XmlMapper> mappers = new ThreadLocal<>();

    /**
     * 获取Xml Mapper
     * 
     * @return
     */
    private static XmlMapper getXmlMapper() {
        XmlMapper xmlMapper = mappers.get();
        if (xmlMapper == null) {
            xmlMapper = new XmlMapper();
            mappers.set(xmlMapper);
        }
        return xmlMapper;
    }

    /**
     * 获取消息的类型
     * 
     * @param content
     * @return
     */
    private static WxMsgType getMsgType(String content) {
        try {
            Matcher matcher = Pattern.compile(WxRegex.REGEX_TYPE).matcher(content);
            if (matcher.find()) {
                String typeName = content.substring(matcher.start(), matcher.end()).toLowerCase();
                if (WxMsgType.event.name().equals(typeName)) {
                    // 消息类型有分很多种
                    return getEventType(content);
                } else {
                    return WxMsgType.valueOf(typeName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WxMsgType.none; // 无法识别消息类型
    }

    /**
     * 获取关注的事件类型
     * 
     * @param content
     * @return
     */
    private static WxMsgType getEventType(String content) {
        try {
            Matcher matcher = Pattern.compile(WxRegex.REGEX_EVENT_TYPE).matcher(content);
            if (matcher.find()) {
                String typeName = content.substring(matcher.start(), matcher.end()).toLowerCase();
                typeName = WxMsgType.event.name() + "_" + typeName;
                if (WxMsgType.event_subscribe.name().equals(typeName)) {
                    // 关注的两种形式，基本关注和二维码关注
                    return getSubscribeEventType(content);
                } else {
                    return WxMsgType.valueOf(typeName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WxMsgType.event; // 返回基本事件类型
    }

    private static WxMsgType getSubscribeEventType(String content) {
        if (Pattern.compile(WxRegex.REGEX_QR_SUBSCRIBE).matcher(content).find()) {
            return WxMsgType.event_subscribe_qrscene;
        } else {
            return WxMsgType.event_subscribe; // 返回基本事件类型
        }
    }

    /**
     * 解析消息的内容，转换为Bean
     * 
     * @param content
     */
    public static BaseMessage xmlToWxMessage(String content) {
        WxMsgType msgType = getMsgType(content);
        if (msgType == WxMsgType.none) {
            return null;
        } // 无法解析内容
        return xmlToBean(content, msgType.clazz);
    }

    /**
     * xml转换为bean
     * 
     * @param xmlContent
     * @param clazz
     * @return
     */
    public static <T> T xmlToBean(String content, Class<T> clazz) {
        try {
            return getXmlMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * bean转换为xml
     * 
     * @param bean
     * @return
     */
    public static <T> String beanToXml(T bean) {
        try {
            return getXmlMapper().setSerializationInclusion(Include.NON_NULL).writerWithDefaultPrettyPrinter()
                    .writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

}
