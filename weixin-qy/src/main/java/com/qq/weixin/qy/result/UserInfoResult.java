package com.qq.weixin.qy.result;

import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * {
 *    "errcode": 0,
 *    "errmsg": "ok",
 *    "userid": "zhangsan",
 *    "name": "李四",
 *    "department": [1, 2],
 *    "position": "后台工程师",
 *    "mobile": "15913215421",
 *    "gender": "1",
 *    "email": "zhangsan@gzdev.com",
 *    "weixinid": "lisifordev",  
 *    "avatar": "http://wx.qlogo.cn/mmopen/ajNVdqHZLLA3WJ6DSZUfiakYe37PKnQhBIeOQBO4czqrnZDS79FH5Wm5m4X69TBicnHFlhiafvDwklOpZeXYQQ2icg/0",
 *    "status": 1,
 *    "extattr": {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
 * }
 * 用户基本信息
 * @author Y13
 *
 */
public class UserInfoResult extends WxErrCode {
	private static final long serialVersionUID = 8301869130238610771L;
	
	/**
	 * 属性内容
	 */
	public static class AttributeValue {
		
		/** 名称 */
		private String name;
		/** 值 */
		private String value;
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public String getValue() {
			return value;
		}
		
		public void setValue(String value) {
			this.value = value;
		}
	}
	/**
	 * 扩展属性
	 */
	public static class Attribute {
		
		/** 属性 */
		private AttributeValue[] attrs;
		
		public AttributeValue[] getAttrs() {
			return attrs;
		}
		
		public void setAttrs(AttributeValue[] attrs) {
			this.attrs = attrs;
		}
	}
	/**
	 * 成员UserID。对应管理端的帐号
	 */
	private String userid;
	
	/**
	 * 成员名称
	 */
	private String name;
	
	/**
	 * 成员所属部门id列表
	 */
	private Integer[] department;
	
	/**
	 * 职位信息
	 */
	private String position;
	
	/**
	 * 手机号码。第三方不可获取
	 */
	private String mobile;
	
	/**
	 * 性别。0表示未定义，1表示男性，2表示女性
	 */
	private String gender;
	
	/**
	 * 邮箱。第三方不可获取
	 */
	private String email;
	
	/**
	 * 微信号
	 */
	private String weixinid;
	
	/**
	 * 头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
	 */
	private String avatar;
	
	/**
	 * 关注状态: 1=已关注，2=已禁用，4=未关注
	 */
	private Integer status;
	
	/**
	 * 扩展属性。第三方不可获取
	 */
	private Attribute extattr;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getDepartment() {
		return department;
	}

	public void setDepartment(Integer[] department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Attribute getExtattr() {
		return extattr;
	}

	public void setExtattr(Attribute extattr) {
		this.extattr = extattr;
	}

}
