package com.suisrc.weixin.core.bean;

import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.suisrc.weixin.core.WxConsts;

/**
 * WxAccessToken 执行过程中访问凭证暂存 {"access_token":"ACCESS_TOKEN","expires_in":7200}
 * 
 * @author Y13
 *
 */
public class WxAccessToken extends WxErrCode {
    private static final long serialVersionUID = 8709719312922168909L;

    /**
     * 凭据状态
     */
    public static enum Status {
        NONE("凭证无效"), VALID("凭证有效"), WILL_EXPIRE("凭证将要过期"), EXPIRED("凭证已经过期");

        public final String msg;

        private Status(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 提前更新时间 私有字段，且不能更改
     */
    private static final long advanceTime;
    static {
        // 默认提前半个小时
        advanceTime = Long.valueOf(System.getProperty(WxConsts.KEY_ADVANCE_TIME, "1800"));
    }

    /**
     * 获取到的凭证
     */
    @JsonProperty("access_token")
    private String accessToken;

    /**
     * 凭证有效时间，单位：秒
     */
    @JsonProperty("expires_in")
    private long expiresIn = -1;

    /**
     * 凭证创建时间, 单位：毫秒
     */
    private long createTime;

    /**
     * 是否在同步 access token 正在同步中，该字段是给异步同时时候使用的，避免多次异步同步更新
     */
    private AtomicBoolean sync = new AtomicBoolean(false);

    public WxAccessToken() {
        createTime = System.currentTimeMillis(); // 凭证创建时间
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * 是否在同步标记
     * 
     * @return
     */
    public AtomicBoolean getSync() {
        return sync;
    }

    /**
     * 通过时间判定平均是否有效
     * 
     * @return
     */
    public Status checkValid() {
        if (accessToken == null) {
            return Status.NONE;
        } // 凭证无效
          // 取得时间间隔
        long interval = System.currentTimeMillis() - createTime;
        // 转换为秒,获取差距间隔时间
        interval = expiresIn - interval / 1000;
        if (interval <= 0) {
            // 过期
            return Status.EXPIRED;
        }
        if (interval <= advanceTime) {
            // 凭据将要
            return Status.WILL_EXPIRE;
        }
        return Status.VALID; // 凭据有效
    }

    /**
     * 数据拷贝
     * 
     * @param token
     */
    public void copy(WxAccessToken token) {
        this.accessToken = token.accessToken;
        this.expiresIn = token.expiresIn;
        this.createTime = token.createTime;
    }

}
