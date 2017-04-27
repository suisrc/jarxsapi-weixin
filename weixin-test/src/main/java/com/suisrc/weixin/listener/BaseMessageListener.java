package com.suisrc.weixin.listener;

import javax.inject.Named;

import com.suisrc.weixin.core.listener.Include;
import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.msg.BaseMessage;
import com.suisrc.weixin.core.msg.pm.TextMessage;
import com.suisrc.weixin.core.msg.pm.VoiceMessage;

/**
 * 监听文本消息
 * 
 * @author Y13
 *
 */
@Named("com.suisrc.weixin.mp.WxBinding")
@Include({ TextMessage.class, VoiceMessage.class })
public class BaseMessageListener implements Listener<BaseMessage> {

	/**
	 * 文本消息处理
	 */
	@Override
	public Object accept(BaseMessage message) {
		return "BaseMessageListener";
	}
	
	/**
	 * 比正常加载顺序多一个加载等级
	 */
	@Override
	public int priority() {
		return Listener.super.priority() - 1;
	}
}
