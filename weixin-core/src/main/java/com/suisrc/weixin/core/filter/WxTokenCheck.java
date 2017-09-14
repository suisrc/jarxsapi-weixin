package com.suisrc.weixin.core.filter;

import java.util.Arrays;
import java.util.List;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.runtime.ReviseHandler;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 对微信中的Access Token 有效性记性验证 系统很容易形成恶性竞争，所以请谨慎使用。
 * 
 * @author Y13
 *
 */
public class WxTokenCheck implements ReviseHandler<Object> {

    /**
     * AccessToken 异常的code
     */
    private static final List<String> errList = Arrays.asList("40001", "40014", "42001");

    /**
     * 方法激活器，管理接口
     */
    private AbstractWeixinActivator activator;

    public WxTokenCheck(ApiActivator activator) {
        this.activator = (AbstractWeixinActivator) activator;
    }

    @Override
    public Object accept(Object value) {
        if (value instanceof WxErrCode) {
            WxErrCode wec = (WxErrCode) value;
            if (wec.getErrcode() != null && errList.contains(wec.getErrcode())) {
                activator.clearAccessToken(); // token 已经失效，通知系统清理
            }
        }
        return value;
    }

}
