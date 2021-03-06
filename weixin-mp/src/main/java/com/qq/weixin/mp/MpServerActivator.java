package com.qq.weixin.mp;

import java.util.Set;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Sets;
import com.qq.weixin.mp.api.AccessTokenRest;
import com.qq.weixin.mp.api.UserRest;
import com.qq.weixin.mp.api.WxServerInfoRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.AbstractWeixinActivator;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.bean.GrantType;
import com.suisrc.weixin.core.bean.WxAccessToken;
import com.suisrc.weixin.core.exception.WxErrCodeException;

/**
 * 程序入口配置
 * 
 * @author Y13 https://api.weixin.qq.com/cgi-bin
 */
@Named(MpWxConsts.NAMED)
@ApplicationScoped
public class MpServerActivator extends AbstractWeixinActivator implements ApiActivator, WxConfig {

    /**
     * 暴露给外部远程访问接口 这里保护了系统访问的两个接口AccessToken接口 如何企业号和公众号同时使用的时候，接口可能出现问题，请注意
     */
    public Set<Class<?>> getClasses() {
        // return Utils.getRemoteApiClasses(null, false,
        // UserRest.class.getPackage().getName(),
        // AccessTokenRest.class.getPackage().getName());
        return Sets.newHashSet(
                UserRest.class, 
                AccessTokenRest.class, 
                WxServerInfoRest.class);

    }

    /**
     * 注入远程获取AccessTokenRest接口
     */
    private AccessTokenRest accessTokenRest;

    /**
     * 设定access token api接口 默认使用自己AccessToken，
     * 如果需要使用统一的接口，需要单独主动调用该方法，替换系统原来的接口实现
     * 如果需要主动修改，请使用setAdapter方法进行修改。
     * 
     * @param atr
     */
    @Inject
    @Named(MpWxConsts.NAMED + "/AccessTokenRest")
    @SuppressWarnings("cdi-ambiguous-dependency")
    protected void setAccessTokenRest(AccessTokenRest atr) {
        setAdapter(AccessTokenRest.class, atr);
    }

    /**
     * 获取 weixin access token
     */
    @Override
    protected WxAccessToken getWxAccessToken() {
        WxAccessToken token = accessTokenRest.getToken(GrantType.client_credential.name(), getAppId(), getAppSecret());
        if (token.getErrcode() != null) {
            throw WxErrCodeException.err(token);
        }
        return token;
    }

    /**
     * 构造后被系统调用 进行内容初始化
     */
    @PostConstruct
    @Override
    public void init() {
        String value = System.getProperty(MpWxConsts.KEY_APP_ID);
        if (value != null) {
            appId = value;
        }
        value = System.getProperty(MpWxConsts.KEY_APP_SECRET);
        if (value != null) {
            appSecret = value;
        }
        value = System.getProperty(MpWxConsts.KEY_TOKEN);
        if (value != null) {
            token = value;
        }
        value = System.getProperty(MpWxConsts.KEY_ENCODING_AES_KEY);
        if (value != null) {
            encodingAesKey = value;
        }
        baseUrl = System.getProperty(MpWxConsts.KEY_BASE_URL, "https://api.weixin.qq.com");
        // 构建缓存线程池
        executor = Executors.newFixedThreadPool(Integer.valueOf(System.getProperty(MpWxConsts.KEY_ACTIVATOR_THREAD_COUNT, "10")));

        super.init();
    }

    /**
     * 主要是为了防止不支持javaee7.0标准的反向内容注入
     */
    public <T> void setAdapter(Class<T> type, T value) {
        if (type == AccessTokenRest.class) {
            accessTokenRest = (AccessTokenRest) value;
        } else {
            super.setAdapter(type, value);
        }
    }

    @Override
    protected String getTempFileName() {
        return "mp-13.obj";
    }

}
