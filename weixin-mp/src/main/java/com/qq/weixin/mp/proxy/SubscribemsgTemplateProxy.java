package com.qq.weixin.mp.proxy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.jaxrsapi.core.ServiceClient;

/**
 * 虚拟远程代理服务器
 * 
 * 看似远程访问，但是实际访问本地，返回本地处理结果
 * 
 * 一次性订阅消息用户访问接口
 * 
 * proxy方法为入口
 * 
 * @author Y13
 *
 */
public class SubscribemsgTemplateProxy {
    
    /**
     * 远程从定向微信服务器地址
     */
    public static final String BASE_URI_KEY = MpWxConsts.PRE_SYSTEM + "system.weixin.mp.subscribemsg.base_uri";

    /**
     * 远程代理客户端接口
     */
    private ServiceClient client;

    /**
     * 构造
     * 
     * @param client
     */
    public SubscribemsgTemplateProxy(ServiceClient client) {
        this.client = client;
    }

    /**
     * 获取基础路径
     * 
     * @return
     */
    private String getBaseUrl() {
        String baseUrl = client.getActivator().getAdapter(BASE_URI_KEY, String.class);
        return baseUrl != null ? baseUrl : "https://mp.weixin.qq.com/mp/subscribemsg";
    }

    /**
     * 一次性订阅消息用户访问接口
     * https://mp.weixin.qq.com/mp/subscribemsg?action=get_confirm&appid=wxaba38c7f163da69b&scene=1000&template_id=1uDxHNXwYQfBmXOfPJcjAS3FynHArD8aWMEFNRGSbCc&
     * redirect_url=http%3a%2f%2fsupport.qq.com&reserved=test#wechat_redirect
     */
    public String proxy(String uri, String action, String appid, String scene, String template_id, String redirect_uri, String reserved) {
        String url = redirect_uri;
        try {
            url = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sbir = new StringBuilder(getBaseUrl()).append('?');
        sbir.append("action=").append(action).append('&');
        sbir.append("appid=").append(appid).append('&');
        sbir.append("scene=").append(scene).append('&');
        sbir.append("template_id=").append(template_id).append('&');
        sbir.append("redirect_uri=").append(url);
        if (reserved != null) {
            sbir.append('&').append("reserved=").append(reserved);
        }
        return sbir.toString();
    }
}
