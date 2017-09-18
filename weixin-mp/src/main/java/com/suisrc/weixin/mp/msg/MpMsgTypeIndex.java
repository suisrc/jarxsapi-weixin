package com.suisrc.weixin.mp.msg;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import com.suisrc.weixin.core.check.TypeAssert;
import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;

/**
 * 用于存储和转换注解
 * Event EventKey MsgType
 * @author Y13
 *
 */
public class MpMsgTypeIndex {
    
    /**
     * 默认消息类型处理集合
     * 
     * 这里可以使用顺序结构，依次遍历，但是感觉这样太慢了。所以这里改变方式，
     * 默认方式采用HashMap作为索引进行快速遍历，默认方式为等于
     */
    private Map<String, MpTypeAnnoInfo> defaultMsgTypes = new HashMap<>();
    /**
     * 前期消息类型处理集合
     */
    private List<MpTypeAnnoInfo> prefixMsgTypes = new ArrayList<>();
    /**
     * 后期消息类型处理集合
     */
    private List<MpTypeAnnoInfo> suffixMsgTypes = new ArrayList<>();

    /**
     * 构建消息推送索引
     * @param packages
     */
    public void buildMsgType(String... packages) {
        ConfigurationBuilder config = new ConfigurationBuilder();
        config.forPackages(packages);
        config.setScanners(new SubTypesScanner()/*, new TypeAnnotationsScanner()*/);
        Reflections reflections = new Reflections(config);
        Set<Class<? extends BaseMessage>> messageClasses = reflections.getSubTypesOf(BaseMessage.class);
        for (Class<? extends BaseMessage> messageClass : messageClasses) {
            if (Modifier.isAbstract(messageClass.getModifiers())) {
                continue; // 抽象类跳过
            }
            MpMsgType msgType = messageClass.getAnnotation(MpMsgType.class);
            if (msgType == null) {
                continue; // 不存在解析类型，跳过
            }
            if (!msgType.effect()) {
                continue; // 失效
            }
            if (msgType.handler() == TypeAssert.class) {
                MpTypeAnnoInfo annoInfo = defaultMsgTypes.get(msgType.value());
                if (annoInfo == null) {
                    defaultMsgTypes.put(msgType.value(), annoInfo = new MpTypeAnnoInfo(msgType, messageClass));
                }
                annoInfo.add(msgType, messageClass);
            } else {
                MpTypeAnnoInfo annoInfo = new MpTypeAnnoInfo(msgType, messageClass);
                if (annoInfo.getPriority().compareTo(WxMsgFactory.DEFAULT_PRIORITY) < 0) {
                    prefixMsgTypes.add(annoInfo);
                } else {
                    suffixMsgTypes.add(annoInfo);
                }
            }
        }
        prefixMsgTypes.sort((l, r) -> l.getPriority().compareTo(r.getPriority()));
        suffixMsgTypes.sort((l, r) -> l.getPriority().compareTo(r.getPriority()));
    }

    /**
     * 获取消息的类型
     * @param content
     * @param isJson
     * @return
     */
    public Class<? extends BaseMessage> getMsgType(String content, boolean isJson) {
        Supplier<Matcher> event = () -> WxRegex.getRegexEvent(isJson).matcher(content);
        Supplier<Matcher> eventKey = () -> WxRegex.getRegexEvent(isJson).matcher(content);

        Matcher matcher = WxRegex.getRegexType(isJson).matcher(content);
        if (matcher.find()) {
            String typeName = matcher.group();
            for (MpTypeAnnoInfo annoInfo : prefixMsgTypes) {
                // 前期定位
                Class<? extends BaseMessage> clazz = annoInfo.get(typeName, event, eventKey);
                if (clazz != null) {
                    return clazz;
                }
            }
            {
                // 默认方式定位
                MpTypeAnnoInfo annoInfo = defaultMsgTypes.get(typeName);
                if (annoInfo != null) {
                    Class<? extends BaseMessage> clazz = annoInfo.get(typeName, event, eventKey);
                    if (clazz != null) {
                        return clazz;
                    }
                }
            }
            for (MpTypeAnnoInfo annoInfo : suffixMsgTypes) {
                // 后期定位
                Class<? extends BaseMessage> clazz = annoInfo.get(typeName, event, eventKey);
                if (clazz != null) {
                    return clazz;
                }
            }
        }
        // 没有知道处理的内容
        return null;
    }

}
