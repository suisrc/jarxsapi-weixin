package com.suisrc.weixin.core.msg;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 订阅事件
 * <Event><![CDATA[subscribe]]></Event>
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class SubscribeEvent extends WxEventMessage {
}
