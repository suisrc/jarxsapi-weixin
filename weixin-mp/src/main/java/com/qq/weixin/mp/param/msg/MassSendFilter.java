package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * is_to_all        否.         用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
 * tag_id           否.         群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
 * 
 * @author Y13
 *
 */
public class MassSendFilter {

    /**
     * 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     */
    @JsonProperty("is_to_all")
    private Boolean isToAll;
    
    /**
     * 群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     */
    @JsonProperty("tag_id")
    private Integer tagId;

    /**
     * 获取用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @return the isToAll
     */
    public Boolean getIsToAll() {
        return isToAll;
    }

    /**
     * 设定用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param isToAll the isToAll to set
     */
    public void setIsToAll(Boolean isToAll) {
        this.isToAll = isToAll;
    }

    /**
     * 获取群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @return the tagId
     */
    public Integer getTagId() {
        return tagId;
    }

    /**
     * 设定群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param tagId the tagId to set
     */
    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }
    
    
}
