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
     * 二维码关注订阅，EventKey内容为qrscene_开头
     */
    String REGEX_QR_SUBSCRIBE_XML = "<EventKey><!\\[CDATA\\[qrscene_.+?\\]\\]></EventKey>";
    String REGEX_QR_SUBSCRIBE_JSON = "\"EventKey\":\"qrscene_+?\"";
}
