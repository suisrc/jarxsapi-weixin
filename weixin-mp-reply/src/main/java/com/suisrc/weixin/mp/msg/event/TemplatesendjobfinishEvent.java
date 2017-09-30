package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;

/**
 * 模版消息推动事件通知
 * 
 * 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中。
 * 1、送达成功时，推送的XML如下：
 *            <xml>
 *            <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
 *            <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
 *            <CreateTime>1395658920</CreateTime>
 *            <MsgType><![CDATA[event]]></MsgType>
 *            <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
 *            <MsgID>200163836</MsgID>
 *            <Status><![CDATA[success]]></Status>
 *            </xml>
 * 参数说明
 * 参数  说明
 * ToUserName  公众号微信号
 * FromUserName    接收模板消息的用户的openid
 * CreateTime  创建时间
 * MsgType 消息类型是事件
 * Event   事件为模板消息发送结束
 * MsgID   消息id
 * Status  发送状态为成功
 * 
 * 2、送达由于用户拒收（用户设置拒绝接收公众号消息）而失败时，推送的XML如下：
 *            <xml>
 *            <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
 *            <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
 *            <CreateTime>1395658984</CreateTime>
 *            <MsgType><![CDATA[event]]></MsgType>
 *            <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
 *            <MsgID>200163840</MsgID>
 *            <Status><![CDATA[failed:user block]]></Status>
 *            </xml>
 * 参数说明
 * 参数  说明
 * ToUserName  公众号微信号
 * FromUserName    接收模板消息的用户的openid
 * CreateTime  创建时间
 * MsgType 消息类型是事件
 * Event   事件为模板消息发送结束
 * MsgID   消息id
 * Status  发送状态为用户拒绝接收
 * 
 * 3、送达由于其他原因失败时，推送的XML如下：
 *            <xml>
 *            <ToUserName><![CDATA[gh_7f083739789a]]></ToUserName>
 *            <FromUserName><![CDATA[oia2TjuEGTNoeX76QEjQNrcURxG8]]></FromUserName>
 *            <CreateTime>1395658984</CreateTime>
 *            <MsgType><![CDATA[event]]></MsgType>
 *            <Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>
 *            <MsgID>200163840</MsgID>
 *            <Status><![CDATA[failed: system failed]]></Status>
 *            </xml>
 * 参数说明
 * 参数  说明
 * ToUserName  公众号微信号
 * FromUserName    接收模板消息的用户的openid
 * CreateTime  创建时间
 * MsgType 消息类型是事件
 * Event   事件为模板消息发送结束
 * MsgID   消息id
 * Status  发送状态为发送失败（非用户拒绝）
 * 
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277
 * 
 * @author Y13
 *
 */
@MpEvent("TEMPLATESENDJOBFINISH")
@JacksonXmlRootElement(localName="xml")
public class TemplatesendjobfinishEvent extends WxEventMessage {

    /**
     * 群发的消息ID
     */
    @JacksonXmlProperty(localName = "MsgID")
    @JsonProperty("MsgID")
    private Long msgId;
    
    /**
     * 发送状态：success,成功|failed:user block,用户拒收|failed: system failed,其他原因失败
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Status")
    @JsonProperty("Status")
    private String status;

    /**
     * 获取群发的消息ID
     * @return the msgId
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * 设定群发的消息ID
     * @param msgId the msgId to set
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取发送状态：success,成功|failed:user block,用户拒收|failed: system failed,其他原因失败
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设定发送状态：success,成功|failed:user block,用户拒收|failed: system failed,其他原因失败
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
