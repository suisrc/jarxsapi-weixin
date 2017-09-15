package com.suisrc.weixin.mp.msg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.suisrc.weixin.core.WxMsgCrFactory;
import com.suisrc.weixin.mp.msg.base.BaseMessage;

/**
 * 消息工厂
 * 
 * @author Y13
 *
 */
public class WxMsgFactory {

    /**
     * 获取消息的类型
     * 
     * @param content
     * @return
     */
    private static WxMsgType getMsgType(String content, boolean isJson) {
        try {
            Matcher matcher = Pattern.compile(isJson ? WxRegex.REGEX_TYPE_JSON : WxRegex.REGEX_TYPE_XML).matcher(content);
            if (matcher.find()) {
                String typeName = matcher.group().toLowerCase();
                if (WxMsgType.event.name().equals(typeName)) {
                    // 消息类型有分很多种
                    return getEventType(content, isJson);
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
    private static WxMsgType getEventType(String content, boolean isJson) {
        try {
            Matcher matcher = Pattern.compile(isJson ? WxRegex.REGEX_EVENT_TYPE_JSON : WxRegex.REGEX_EVENT_TYPE_XML).matcher(content);
            if (matcher.find()) {
                String typeName = matcher.group().toLowerCase();
                typeName = WxMsgType.event.name() + "_" + typeName;
                if (WxMsgType.event_subscribe.name().equals(typeName)) {
                    // 关注的两种形式，基本关注和二维码关注
                    return getSubscribeEventType(content, isJson);
                } else {
                    return WxMsgType.valueOf(typeName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return WxMsgType.event; // 返回基本事件类型
    }

    private static WxMsgType getSubscribeEventType(String content, boolean isJson) {
        if (Pattern.compile(isJson ? WxRegex.REGEX_QR_SUBSCRIBE_JSON : WxRegex.REGEX_QR_SUBSCRIBE_XML).matcher(content).find()) {
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
    public static BaseMessage strToWxMessage(String content, boolean isJson) {
        WxMsgType msgType = getMsgType(content, isJson);
        if (msgType == WxMsgType.none) {
            return null;
        } // 无法解析内容
        return WxMsgCrFactory.str2Bean(content, msgType.clazz, isJson);
    }

}
