package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 订阅事件
 * <Event><![CDATA[subscribe]]></Event>
 * @author Y13
 *
 */
@MpEvent("subscribe")
@JacksonXmlRootElement(localName="xml")
public class SubscribeEvent extends WxEventMessage {
}
