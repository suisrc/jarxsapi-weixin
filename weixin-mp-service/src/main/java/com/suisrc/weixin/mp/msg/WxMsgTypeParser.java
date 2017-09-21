package com.suisrc.weixin.mp.msg;

import com.suisrc.weixin.core.msg.IMessage;

/**
 * 微信公众号消息类型解析器
 * @author Y13
 *
 */
public interface WxMsgTypeParser {

    /**
     * 获取消息对应的类型
     * @param node
     * @return
     */
    Class<? extends IMessage> parser(String msgtype, String event, String eventkey);

}
