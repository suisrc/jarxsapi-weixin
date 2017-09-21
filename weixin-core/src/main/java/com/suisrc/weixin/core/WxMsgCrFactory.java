package com.suisrc.weixin.core;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 消息工厂
 * 
 * 舍弃的线程变量
 * 
 * @author Y13
 *
 */
public class WxMsgCrFactory {

    /**
     * xml转换为node
     * 
     * @param xmlContent
     * @param clazz
     * @return
     */
    public static WxMsgNode xml2Node(String content) {
        try {
            ObjectMapper mapper = new XmlMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.writerWithDefaultPrettyPrinter();
            return WxMsgNode.create(mapper, content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * xml转换为bean
     * 
     * @param xmlContent
     * @param clazz
     * @return
     */
    public static <T> T xml2Bean(String content, Class<T> clazz) {
        try {
            ObjectMapper mapper = new XmlMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.writerWithDefaultPrettyPrinter();
            return mapper.readValue(content, clazz);
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
    public static <T> String bean2Xml(T bean) {
        try {
            ObjectMapper mapper = new XmlMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.writerWithDefaultPrettyPrinter();
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * xml转换为node
     * 
     * @param xmlContent
     * @param clazz
     * @return
     */
    public static WxMsgNode json2Node(String content) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.writerWithDefaultPrettyPrinter();
            return WxMsgNode.create(mapper, content);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * json转换为bean
     * 
     * @param xmlContent
     * @param clazz
     * @return
     */
    public static <T> T json2Bean(String content, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * bean转换为json
     * 
     * @param bean
     * @return
     */
    public static <T> String bean2Json(T bean) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            mapper.writerWithDefaultPrettyPrinter();
            return mapper.writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * bean转换为字符串
     * @param bean
     * @param isJson
     * @return
     */
    public static <T> String bean2Str(T bean, boolean isJson) {
        return isJson ? bean2Json(bean) : bean2Xml(bean);
    }

    /**
     * 字符串转换为bean
     * @param data
     * @param class1
     * @param isJson
     * @return
     */
    public static <T> T str2Bean(String data, Class<T> clazz, boolean isJson) {
        return isJson ? json2Bean(data, clazz) : xml2Bean(data, clazz);
    }

    /**
     * 字符串转换为bean
     * @param data
     * @param class1
     * @param isJson
     * @return
     */
    public static WxMsgNode str2Node(String data, boolean isJson) {
        return isJson ? json2Node(data) : xml2Node(data);
    }

}
