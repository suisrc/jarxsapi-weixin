package com.qq.weixin.mp.param.msg;

import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 根据标签进行群发【订阅号与服务号认证后均可用】
 * @author Y13
 * 
 * 参数.             是否必须.           说明
 * filter           是.         用于设定图文消息的接收者
 * is_to_all        否.         用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
 * tag_id           否.         群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
 * mpnews           是.         用于设定即将发送的图文消息
 * media_id         是.         用于群发的消息的media_id
 * msgtype          是.         群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
 * title            否.         消息的标题
 * description      否.         消息的描述
 * thumb_media_id   是.         视频缩略图的媒体ID
 * 
 * send_ignore_reprint 是.      图文消息被判定为转载时，是否继续群发。1为继续群发（转载），0为停止群发。该参数默认为0。
 */
public class MassSendAllParam extends AbstractMassSendInfo {
    
    /**
     * 用于设定图文消息的接收者
     */
    @JsonProperty("filter")
    private MassSendFilter filter;

    /**
     * 消息内容
     */
    @JsonIgnore
    private MassSendItem item;

    /**
     * 消息的标题
     */
    private String title;

    /**
     * 消息的描述
     */
    private String description;

    /**
     * 视频缩略图的媒体ID
     */
    @JsonProperty("thumb_media_id")
    private String thumbMediaId;
    
    /**
     * 获取用于设定图文消息的接收者
     * @return the filter
     */
    public MassSendFilter getFilter() {
        return filter;
    }

    /**
     * 设定用于设定图文消息的接收者
     * @param filter the filter to set
     */
    public void setFilter(MassSendFilter filter) {
        this.filter = filter;
    }
    
    /**
     * 获取消息内容
     * @return the item
     */
    public MassSendItem getItem() {
        return item;
    }

    /**
     * 设定消息内容
     * @param item the item to set
     */
    public void setItem(MassSendItem item) {
        this.item = item;
    }

    /**
     * 获取消息的标题
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设定消息的标题
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取消息的描述
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设定消息的描述
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取视频缩略图的媒体ID
     * @return the thumbMediaId
     */
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    /**
     * 设定视频缩略图的媒体ID
     * @param thumbMediaId the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
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
    
//    public static void main(String[] args) {
//        MassSendAllParam param = new MassSendAllParam();
//        MassSendItemText text = new MassSendItemText();
//        text.setContent("hello");
//        param.items("test", text);
//        MassSendFilter filter = new MassSendFilter();
//        filter.setIsToAll(false);
//        param.setFilter(filter);
//        String json = WxMsgCrFactory.bean2Json(param);
//        System.out.println(json);
//    }
}
