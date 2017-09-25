package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.core.check.TypeEqualsAssert;
import com.suisrc.weixin.core.check.TypeStartsWithAssert;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.annotation.MpEventKey;

/**
 * 二维码扫描
 * 
 * 用户未关注时，进行关注后的事件推送
 * 
 * <Event><![CDATA[subscribe]]></Event>
 * 
 * <EventKey><![CDATA[qrscene_123123]]></EventKey>
 * <Ticket><![CDATA[TICKET]]></Ticket>
 * 
 * 事件KEY值，qrscene_为前缀，后面为二维码的参数值
 * 
 * @author Y13
 *
 */
@MpEvent(value = "subscribe", priority = "M", handler = TypeEqualsAssert.class)
@MpEventKey(value = "qrscene_", handler = TypeStartsWithAssert.class)
@JacksonXmlRootElement(localName = "xml")
public class QrsceneSubscribeEvent extends QrsceneEvent {

}
