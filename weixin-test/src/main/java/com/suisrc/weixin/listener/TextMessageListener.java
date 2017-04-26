package com.suisrc.weixin.listener;

import javax.inject.Named;

import com.suisrc.weixin.core.listener.Listener;
import com.suisrc.weixin.core.media.ArticlesMedia;
import com.suisrc.weixin.core.msg.pm.TextMessage;
import com.suisrc.weixin.core.msg.r.ReplyNewsMessage;

/**
 * 监听文本消息
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
		ReplyNewsMessage reply = message.reverse(new ReplyNewsMessage());

		ArticlesMedia media = new ArticlesMedia();
		media.setDescription("hello");
		media.setTitle("word");
		reply.addArticles(media);
		
		media = new ArticlesMedia();
		media.setDescription("hello2");
		media.setTitle("word3");
		reply.addArticles(media);
		
		return reply;
	}

}
