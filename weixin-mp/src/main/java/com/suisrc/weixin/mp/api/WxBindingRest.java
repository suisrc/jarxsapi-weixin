package com.suisrc.weixin.mp.api;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.suisrc.weixin.core.bean.WxEncryptSignature;
import com.suisrc.weixin.core.bean.WxJsapiSignature;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
@Path("wx/mp")
public interface WxBindingRest {

    /**
     * 后台微信请求服务器运行状态
     */
    @GET
    @Path("info")
    @Produces(MediaType.TEXT_PLAIN)
    String getServerInfo();

    /**
     * 微信回调URL绑定
     * 
     * @throws AesException
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String doGet(@BeanParam WxJsapiSignature sign);
    
    /**
     * 微信回调请求绑定
     * 
     */
    @POST
    @Produces(MediaType.APPLICATION_XML)
    Response doPost(@BeanParam WxEncryptSignature sign, String data);

}
