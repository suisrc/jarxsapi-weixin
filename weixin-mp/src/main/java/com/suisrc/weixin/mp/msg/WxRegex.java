package com.suisrc.weixin.mp.msg;

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
}
