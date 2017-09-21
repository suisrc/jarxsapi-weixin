package com.suisrc.weixin.mp.msg;

import java.util.regex.Pattern;

/**
 * 消息类型记性断言
 * 
 * 引入WxMsgNode类型，不再需要这里的正则表达式进行断言
 * 
 * @author Y13
 *
 */
@Deprecated
interface WxRegex {

    /**
     * 获取消息类型的正则表达式
     */
    String REGEX_TYPE_XML = "(?<=<MsgType><!\\[CDATA\\[).+?(?=\\]\\]></MsgType>)";
    String REGEX_TYPE_JSON = "(?<=\"MsgType\":\").+?(?=\")";

    /**
     * 获取事件类型的正则表达式
     */
    String REGEX_EVENT_TYPE_XML = "(?<=<Event><!\\[CDATA\\[).+?(?=\\]\\]></Event>)";
    String REGEX_EVENT_TYPE_JSON = "(?<=\"Event\":\").+?(?=\")";

    /**
     * EventKey内容的正则表达式
     */
    String REGEX_EVENT_KEY_XML = "(?<=<EventKey><!\\[CDATA\\[).+?(?=\\]\\]></EventKey>)";
    String REGEX_EVENT_KEY_JSON = "(?<=\"EventKey\":\").+?(?=\")";
    
    /**
     * 获取消息类型的正则表达式
     * @param isJson
     * @return
     */
    static Pattern getRegexType(boolean isJson) {
        return Pattern.compile(isJson ? REGEX_TYPE_JSON : REGEX_TYPE_XML);
    }
    
    /**
     * 获取事件类型的正则表达式
     * @param isJson
     * @return
     */
    static Pattern getRegexEvent(boolean isJson) {
        return Pattern.compile(isJson ? REGEX_EVENT_TYPE_JSON : REGEX_EVENT_TYPE_XML);
    }
    
    /**
     * 获取消息类型的正则表达式
     * @param isJson
     * @return
     */
    static Pattern getRegexEventKey(boolean isJson) {
        return Pattern.compile(isJson ? REGEX_EVENT_KEY_JSON : REGEX_EVENT_KEY_XML);
    }
}
