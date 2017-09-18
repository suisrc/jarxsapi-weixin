package com.suisrc.weixin.mp.impl;

import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.qq.weixin.mp.MpWxConsts;
import com.qq.weixin.mp.api.WxServerInfoRest;
import com.qq.weixin.mp.result.ServerIpResult;
import com.suisrc.weixin.core.AbstractWxBinding;
import com.suisrc.weixin.core.WxConfig;
import com.suisrc.weixin.core.exception.ResponseException;
import com.suisrc.weixin.core.exception.WxErrCodeException;
import com.suisrc.weixin.core.listener.ListenerManager;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.api.WxBindingRest;
import com.suisrc.weixin.mp.msg.WxMsgFactory;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
@ApplicationScoped
public class WxBinding extends AbstractWxBinding<WxBindingRest> implements WxBindingRest {
    
    /**
     * 客户端访问信息
     */
    @Inject private HttpServletRequest request;
    @SuppressWarnings("cdi-ambiguous-dependency")
    @Inject private WxServerInfoRest wxServerInfoRest;
    
    /**
     * 白名单操作密钥
     */
    private String whitelistSecret;
    
    /**
     * 微信服务器可信白名单
     * 
     * 默认null表示不使用白名单
     */
    private HashSet<String> whitelist = null;

    /**
     * 获取客户端IP
     * @return
     */
    protected String getRemoteHost() {
        String ip = request.getHeader("X-Real-IP");
        return ip != null ? ip : request.getRemoteHost();
    }
    
    /**
     * 构造
     */
    @PostConstruct
    @Override
    protected void initialized() {
        // 初始化监听管理器
        listenerManager = new ListenerManager<>(this, WxBindingRest.class);
        // 设定构建器创建工具
        listenerManager.setListenerCreater(clazz -> CDI.current().select(clazz).get());
        // 设定监听的内容
        listenerManager.addClassesBySysProp(MpWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_CLASSES);
        listenerManager.addPackagesBySysProp(MpWxConsts.KEY_WEIXIN_CALLBACK_LISTENER_PACKAGES);
        // 消息加密
        isEncrypt = Boolean.valueOf(System.getProperty(MpWxConsts.KEY_WEIXIN_CALLBACK_MESSAGE_ENCRYPT, "true")).booleanValue();
        // 其他
        whitelistSecret = System.getProperty(MpWxConsts.KEY_WEIXIN_WHITELIST_SECRET);
    }

    /**
     * 注入服务器配置信息
     */
    @Inject
    @Named(MpWxConsts.NAMED)
    @Override
    protected void setWxConfig(WxConfig config) {
        super.setWxConfig(config);
    }

    /**
     * 消息类型转换
     */
    @Override
    protected IMessage str2Bean(String str, boolean isJson) {
        return WxMsgFactory.strToWxMessage(str, isJson);
    }
    
    /**
     * 确认客户端信息
     */
    protected void assertClientInfo() {
        if (whitelist == null) {
            return; // 白名单为空，不进行处理
        }
        String clientIp = getRemoteHost();
        if (whitelist.contains(clientIp)) {
            return; // 客户ip在白名单中，不进行处理
        }
        throw new ResponseException("Illegal access, from:" + clientIp, "Invalid IP[" + clientIp + 
                "],Not in the weixin server IP whitelist", "HTTP-ERR-403");
    }

    /**
     * 后台微信请求服务器运行状态
     */
    @Override
    public String getServerInfo() {
        return "Mp " + super.getServerInfo();
    }

    /**
     * 刷新/启用微信服务器IP白名单
     */
    @Override
    public String updateWhiteList(String secret) {
        if (whitelistSecret == null) {
            return "The access key was not configured, no operation";
        } else if (!whitelistSecret.equals(secret)) {
            return "Key authentication failed, please replace the operation key";
        }
        //-------------------------------------------
        try {
            ServerIpResult result = wxServerInfoRest.getCallbackIp();
            if (result.getErrcode() != null) {
                throw WxErrCodeException.err(result);
            }
            whitelist = new HashSet<>();
            StringBuilder sbir = new StringBuilder("Weixin service interface's whitelist has been enabled, IP list:\n");
            for (String ip : result.getIpList()) {
                int offset = ip.indexOf('/');
                String ipf = offset > 0 ? ip.substring(0, offset) : ip;
                sbir.append(ipf).append('\n');
                whitelist.add(ipf);
            }
            return sbir.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Weixin service interface's whitelist failed to start：" + e.getMessage();
        }
    }

    /**
     * 删除/禁用微信服务器IP白名单
     */
    @Override
    public String deleteWhiteList(String secret) {
        if (whitelistSecret == null) {
            return "The access key was not configured, no operation";
        } else if (!whitelistSecret.equals(secret)) {
            return "Key authentication failed, please replace the operation key";
        }
        //-------------------------------------------
        if (whitelist == null) {
            return "Weixin service interface's whitelist does not start";
        }
        HashSet<String> list = whitelist;
        whitelist = null;
        StringBuilder sbir = new StringBuilder("Weixin service interface's whitelist has been disabled, IP list:\n");
        list.forEach(ip -> sbir.append(ip).append('\n'));
        return sbir.toString();
    }

}
