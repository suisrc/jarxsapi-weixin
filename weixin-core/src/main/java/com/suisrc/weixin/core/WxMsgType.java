package com.suisrc.weixin.core;

import com.suisrc.weixin.core.msg.BaseMessage;
import com.suisrc.weixin.core.msg.ClickEvent;
import com.suisrc.weixin.core.msg.ImageMessage;
import com.suisrc.weixin.core.msg.LinkMessage;
import com.suisrc.weixin.core.msg.LocationEvent;
import com.suisrc.weixin.core.msg.LocationMessage;
import com.suisrc.weixin.core.msg.QrsceneEvent;
import com.suisrc.weixin.core.msg.QrsceneSubscribeEvent;
import com.suisrc.weixin.core.msg.ShortvideoMessage;
import com.suisrc.weixin.core.msg.SubscribeEvent;
import com.suisrc.weixin.core.msg.TextMessage;
import com.suisrc.weixin.core.msg.UnsubscribeEvent;
import com.suisrc.weixin.core.msg.VideoMessage;
import com.suisrc.weixin.core.msg.ViewEvent;
import com.suisrc.weixin.core.msg.VoiceMessage;
import com.suisrc.weixin.core.msg.WxEventMessage;

/**
 * 微信消息类型
 * @author Y13
 *
 */
public enum WxMsgType {
	
	/**
	 * 未知消息类型，用于防止无法解析消息内容
	 * 无法确定消息类型
	 */
	none("未知类型消息", null),
	
	/**
	 * 文本消息类型
	 */
	text("文本消息", TextMessage.class),
	
	/**
	 * 图片消息类型
	 */
	image("图片消息", ImageMessage.class),
	
	/**
	 * 语音消息类型
	 */
	voice("语音消息", VoiceMessage.class),
	
	/**
	 * 视频消息类型
	 */
	video("视频消息", VideoMessage.class),
	
	/**
	 * 小视频消息类型
	 */
	shortvideo("小视频消息", ShortvideoMessage.class),
	
	/**
	 * 地址消息类型
	 */
	location("地理位置消息", LocationMessage.class),
	
	/**
	 * 链接消息类型
	 */
	link("链接消息", LinkMessage.class),
	
	/**
	 * 事件类型消息
	 * MsgType是事件，但是无法知道事件的种类
	 */
	event("未知事件", WxEventMessage.class),
	
	/**
	 * 订阅事件消息
	 */
	event_subscribe("订阅事件", SubscribeEvent.class),
	
	/**
	 * 取消订阅事件消息
	 */
	event_unsubscribe("取消订阅事件", UnsubscribeEvent.class),
	
	/**
	 * 用户未关注时，进行关注后的事件推送
	 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
	 */
	event_subscribe_qrscene("二维码订阅事件", QrsceneSubscribeEvent.class),
	
	/**
	 * 用户已关注时的事件推送
	 * 如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者。
	 */
	event_scan("二维码扫描事件", QrsceneEvent.class),
	
	/**
	 * 上报地理位置事件
	 */
	event_location("上报地理位置事件", LocationEvent.class),
	
	/**
	 * 点击菜单拉取消息时的事件推送
	 */
	event_click("点击菜单拉取消息时的事件", ClickEvent.class),
	
	/**
	 * 点击菜单跳转链接时的事件推送
	 */
	event_view("点击菜单跳转链接时的事件", ViewEvent.class);
	
	/** 消息内容 */
	public final String value;
	/** 消息对应的Bean类型 */
	public final Class<? extends BaseMessage> clazz;
	private WxMsgType(String value, Class<? extends BaseMessage> clazz) {
		this.value = value;
		this.clazz = clazz;
	}
}
