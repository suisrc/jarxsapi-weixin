package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 取消订阅事件
 * <Event><![CDATA[unsubscribe]]></Event>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class UnsubscribeEvent extends WxEventMessage {
}
