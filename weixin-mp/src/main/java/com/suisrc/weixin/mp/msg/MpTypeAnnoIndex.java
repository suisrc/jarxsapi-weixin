package com.suisrc.weixin.mp.msg;

import java.lang.reflect.Modifier;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ConfigurationBuilder;

import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;

/**
 * 用于存储和转换注解
 * Event EventKey MsgType
 * @author Y13
 *
 */
public class MpTypeAnnoIndex {

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
        }
    }

    /**
     * 获取消息的类型
     * @param content
     * @param isJson
     * @return
     */
    public Class<? extends BaseMessage> getMsgType(String content, boolean isJson) {
        // TODO Auto-generated method stub
        return null;
    }

}
