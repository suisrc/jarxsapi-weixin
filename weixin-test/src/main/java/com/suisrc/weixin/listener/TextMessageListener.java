package com.suisrc.weixin.listener;

import javax.inject.Named;

import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.msg.pm.TextMessage;
import com.suisrc.weixin.core.msg.r.ReplyTextMessage;

/**
 * 监听文本消息
 * 
 * @author Y13
 *
 */
@Named("com.suisrc.weixin.mp.WxBinding")
public class TextMessageListener implements Listener<TextMessage> {

	/**
	 * 文本消息处理
	 */
	@Override
	public Object accept(TextMessage message) {
		ReplyTextMessage msg = message.reverse(new ReplyTextMessage());
		msg.setContent("TextMessageListener->" + message.getContent());
		return msg;
	}
}
