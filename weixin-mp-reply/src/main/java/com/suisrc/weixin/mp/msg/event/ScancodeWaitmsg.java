package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;

/**
 * 扫码推事件且弹出“消息接收中”提示框的事件推送
 * 
 * <Event><![CDATA[scancode_waitmsg]]></Event>
 * <EventKey><![CDATA[6]]></EventKey>
 * <ScanCodeInfo>
 *   <ScanType><![CDATA[qrcode]]></ScanType>
 *   <ScanResult><![CDATA[2]]></ScanResult>
 * </ScanCodeInfo>
 * 
 * @author Y13
 *
 */
@MpEvent("scancode_waitmsg")
@JacksonXmlRootElement(localName="xml")
public class ScancodeWaitmsg extends ScancodePushEvent {

}
