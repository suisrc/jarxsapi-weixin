package com.qq.weixin.mp.param.msg;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * 参数.       说明
 * touser     接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
 * msgtype    群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
 * media_id   用于群发的消息的media_id
 * content    发送文本消息时文本的内容
 * @author Y13
 *
 */
public class MassPreviewParam extends AbstractMassSendInfo {

    /**
     * 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     */
    private String touser;
    
    /**
     * 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     */
    private String towxname;
    
    /**
     * 群发的消息
     */
    @JsonIgnore
    private MassSendItem item;

    /**
     * 获取接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @return the touser
     */
    public String getTouser() {
        return touser;
    }

    /**
     * 设定接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param touser the touser to set
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }

    /**
     * 获取接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @return the towxname
     */
    public String getTowxname() {
        return towxname;
    }

    /**
     * 设定接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param towxname the towxname to set
     */
    public void setTowxname(String towxname) {
        this.towxname = towxname;
    }

    /**
     * 获取群发的消息
     * @return the item
     */
    public MassSendItem getItem() {
        return item;
    }

    /**
     * 设定群发的消息
     * @param item the item to set
     */
    public void setItem(MassSendItem item) {
        this.item = item;
    }
    
    /**
     * 获取消息内容
     * 该方法是给fasterxml 框架使用
     * @return the items
     */
    @JsonAnyGetter
    public Map<String, MassSendItem> items() {
        return Collections.singletonMap(getMsgtype(), item);
    }

    /**
     * 设定消息内容
     * 该方法是给fasterxml 框架使用
     * @param msgtype
     * @param item
     */
    @JsonAnySetter
    public void items(String msgtype, MassSendItem item) {
        if (getMsgtype() == null) {
            setMsgtype(msgtype);
        }
        this.item = item;
    }
}
