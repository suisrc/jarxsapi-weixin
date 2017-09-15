package com.qq.weixin.mp.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * {
 *   "subscribe": 1, 
 *   "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", 
 *   "nickname": "Band", 
 *   "sex": 1, 
 *   "language": "zh_CN", 
 *   "city": "广州", 
 *   "province": "广东", 
 *   "country": "中国", 
 *   "headimgurl":  "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
 *   "subscribe_time": 1382694957,
 *   "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
 *   "remark": "",
 *   "groupid": 0,
 *   "tagid_list":[128,2]
 * }
 * 用户基本信息
 * @author Y13
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfoResult extends WxErrCode {
    private static final long serialVersionUID = -4583873614235629079L;

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     */
    private Integer subscribe;

    /**
     * 用户的标识，对当前公众号唯一
     */
    private String openid;

    /**
     * 用户的昵称
     */
    private String nickname;

    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    private Integer sex;

    /**
     * 用户的语言，简体中文为zh_CN
     */
    private String language;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     */
    private String headimgurl;

    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JsonProperty("subscribe_time")
    private Long subscribeTime;

    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     */
    private String unionid;

    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    private String remark;

    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private Integer groupid;

    /**
     * 用户被打上的标签ID列表
     */
    @JsonProperty("tagid_list")
    private Integer[] tagidList;

    /**
     * 获取用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * @return the subscribe
     */
    public Integer getSubscribe() {
        return subscribe;
    }

    /**
     * 设定用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
     * @param subscribe the subscribe to set
     */
    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    /**
     * 获取用户的标识，对当前公众号唯一
     * @return the openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设定用户的标识，对当前公众号唯一
     * @param openid the openid to set
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户的昵称
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设定用户的昵称
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * @return the sex
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设定用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     * @param sex the sex to set
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取用户的语言，简体中文为zh_CN
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设定用户的语言，简体中文为zh_CN
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 获取用户所在城市
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * 设定用户所在城市
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取用户所在省份
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设定用户所在省份
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取用户所在国家
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设定用户所在国家
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640 640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * @return the headimgurl
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设定用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640 640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
     * @param headimgurl the headimgurl to set
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 获取用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * @return the subscribeTime
     */
    public Long getSubscribeTime() {
        return subscribeTime;
    }

    /**
     * 设定用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     * @param subscribeTime the subscribeTime to set
     */
    public void setSubscribeTime(Long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    /**
     * 获取只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * @return the unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设定只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
     * @param unionid the unionid to set
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 获取公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设定公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取用户所在的分组ID（兼容旧的用户分组接口）
     * @return the groupid
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * 设定用户所在的分组ID（兼容旧的用户分组接口）
     * @param groupid the groupid to set
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取用户被打上的标签ID列表
     * @return the tagidList
     */
    public Integer[] getTagidList() {
        return tagidList;
    }

    /**
     * 设定用户被打上的标签ID列表
     * @param tagidList the tagidList to set
     */
    public void setTagidList(Integer[] tagidList) {
        this.tagidList = tagidList;
    }

}
