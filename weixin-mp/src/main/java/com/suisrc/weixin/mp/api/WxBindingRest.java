package com.suisrc.weixin.mp.api;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.suisrc.weixin.core.bean.WxEncryptSignature;
import com.suisrc.weixin.core.bean.WxJsapiSignature;

import io.swagger.annotations.ApiOperation;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
@Path("wx/mp")
public interface WxBindingRest {

    @ApiOperation(nickname="mp10010", value="后台微信请求服务器运行状态")
    @GET
    @Path("info")
    @Produces(MediaType.TEXT_PLAIN)
    String getServerInfo();
    
    @ApiOperation(nickname="mp10020", value="刷新/启用微信服务器IP白名单")
    @GET
    @Path("whitelist/update")
    @Produces(MediaType.TEXT_PLAIN)
    String updateWhiteList(@QueryParam("secret")String secret);
    
    @ApiOperation(nickname="mp10021", value="删除/禁用微信服务器IP白名单")
    @GET
    @Path("whitelist/delete")
    @Produces(MediaType.TEXT_PLAIN)
    String deleteWhiteList(@QueryParam("secret")String secret);

    @ApiOperation(nickname="mp10030", value="微信回调URL绑定")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String doGet(@BeanParam WxJsapiSignature sign);
    
    @ApiOperation(nickname="mp10030", value="微信回调请求绑定")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    Response doPost(@BeanParam WxEncryptSignature sign, String data);

}
