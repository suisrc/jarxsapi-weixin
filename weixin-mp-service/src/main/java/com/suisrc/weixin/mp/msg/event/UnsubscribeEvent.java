package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 取消订阅事件
 * <Event><![CDATA[unsubscribe]]></Event>
 * @author Y13
 *
 */
@MpEvent("unsubscribe")
@JacksonXmlRootElement(localName="xml")
public class UnsubscribeEvent extends WxEventMessage {
}
