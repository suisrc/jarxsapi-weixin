package com.suisrc.weixin.core;

interface WxRegex {

    /**
     * 获取消息类型的正则表达式
     */
    String REGEX_TYPE = "(?<=<MsgType><!\\[CDATA\\[).+?(?=\\]\\]></MsgType>)";

    /**
     * 获取事件类型的正则表达式
     */
    String REGEX_EVENT_TYPE = "(?<=<Event><!\\[CDATA\\[).+?(?=\\]\\]></Event>)";

    /**
     * 二维码关注订阅，EventKey内容为qrscene_开头
     */
    String REGEX_QR_SUBSCRIBE = "<EventKey><!\\[CDATA\\[qrscene_.+?\\]\\]></EventKey>";
}
