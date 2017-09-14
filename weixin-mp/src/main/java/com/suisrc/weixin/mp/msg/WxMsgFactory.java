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
        return WxMsgCrFactory.xmlToBean(content, msgType.clazz);
    }

}
