package com.qq.weixin.mp.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 获取用户列表
 * {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
 * @author Y13
 *
 */
public class UserListResult extends WxErrCode {
	private static final long serialVersionUID = -4939436975081137027L;

	public static class Data {
		/**
		 * 列表数据，OPENID的列表
		 */
		private  String[] openid;
		
		public String[] getOpenid() {
			return openid;
		}
		
		public void setOpenid(String[] openid) {
			this.openid = openid;
		}
	}

	/**
	 * 关注该公众账号的总用户数
	 */
	private Integer total;
	
	/**
	 * 拉取的OPENID个数，最大值为10000
	 */
	private Integer count;
	
	/**
	 * 列表数据，OPENID的列表
	 */
	private Data data;
	
	/**
	 * 拉取列表的最后一个用户的OPENID
	 */
	@JsonProperty("next_openid")
	private String nextOpenId;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
	
}
