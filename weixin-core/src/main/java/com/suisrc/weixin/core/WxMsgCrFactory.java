package com.suisrc.weixin.core;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 消息工厂
 * 
 * @author Y13
 *
 */
public class WxMsgCrFactory {

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
