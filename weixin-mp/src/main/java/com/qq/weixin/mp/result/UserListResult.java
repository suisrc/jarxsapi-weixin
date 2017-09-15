package com.qq.weixin.mp.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 获取用户列表
 * {"total":2,"count":2,"data":{"openid":["","OPENID1","OPENID2"]},"next_openid":"NEXT_OPENID"}
 * @author Y13
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserListResult extends WxErrCode {
	private static final long serialVersionUID = -4939436975081137027L;

	public static class Data {
		/**
		 * 列表数据，OPENID的列表
		 */
		private  String[] openid;

        /**
         * 获取列表数据，OPENID的列表
         * @return the openid
         */
        public String[] getOpenid() {
            return openid;
        }

        /**
         * 设定列表数据，OPENID的列表
         * @param openid the openid to set
         */
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

    /**
     * 获取关注该公众账号的总用户数
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设定关注该公众账号的总用户数
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取拉取的OPENID个数，最大值为10000
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设定拉取的OPENID个数，最大值为10000
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取列表数据，OPENID的列表
     * @return the data
     */
    public Data getData() {
        return data;
    }

    /**
     * 设定列表数据，OPENID的列表
     * @param data the data to set
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * 获取拉取列表的最后一个用户的OPENID
     * @return the nextOpenId
     */
    public String getNextOpenId() {
        return nextOpenId;
    }

    /**
     * 设定拉取列表的最后一个用户的OPENID
     * @param nextOpenId the nextOpenId to set
     */
    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }
    
}
