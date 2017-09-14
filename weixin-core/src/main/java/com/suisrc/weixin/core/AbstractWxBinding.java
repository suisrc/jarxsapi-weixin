package com.suisrc.weixin.core;

import java.time.LocalDateTime;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.suisrc.weixin.core.bean.WxEncryptSignature;
import com.suisrc.weixin.core.bean.WxJsapiSignature;
import com.suisrc.weixin.core.crypto.WxCrypto;
import com.suisrc.weixin.core.listener.ListenerManager;
import com.suisrc.weixin.core.msg.EncryptMessage;
import com.suisrc.weixin.core.msg.IMessage;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
public abstract class AbstractWxBinding {

    /**
     * 监听器
     */
    protected ListenerManager listenerManager;

    /**
     * 是否使用信息加密
     */
    protected boolean isEncrypt = true;

    /**
     * 微信配置
     */
    protected WxConfig config;

    /**
     * 需要初始化listenerManager和isEncrypt
     */
    protected void initialized() {
        listenerManager = new ListenerManager(this);
    }

    /**
     * 设定微信配置信息
     * 
     * @param config
     */
    protected void setWxConfig(WxConfig config) {
        this.config = config;
    }
    
    /**
     * 把xml转换为消息
     * @param xml
     * @return
     */
    protected abstract IMessage xml2Message(String xml);
    
    /**
     * 后台微信请求服务器运行状态
     */
    public String getServerInfo() {
        return "Server is Running! time:" + LocalDateTime.now();
    }

    /**
     * 微信回调URL绑定
     * 
     */
    public String doGet(@BeanParam WxJsapiSignature sign) {
        if (config.getToken() == null || !sign.isValid()) {
            return "非法请求";
        }
        // 进行验证
        String signature = WxCrypto.genSHA1(config.getToken(), sign.getTimestamp(), sign.getNonce());
        if (signature.equals(sign.getSignature())) {
            return sign.getEchostr();
        } else {
            return "";
        }
    }

    /**
     * 微信回调请求绑定
     * 
     */
    public Response doPost(@BeanParam WxEncryptSignature sign, String data) {
        // --------------------------------服务器验证------------------------------------//
        if (isEncrypt && (config.getToken() == null || !sign.isValid())) {
            // 没有签名信息
            return Response.ok().entity("非法请求").type(MediaType.TEXT_PLAIN).build();
        }
        if (sign.getSignature() != null) {
            // 服务器验证
            String signature = WxCrypto.genSHA1(config.getToken(), sign.getTimestamp(), sign.getNonce());
            if (!signature.equals(sign.getSignature())) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                return Response.ok().entity("非法请求").type(MediaType.TEXT_PLAIN).build();
            }
            if (sign.getEchostr() != null && !sign.getEchostr().isEmpty()) {
                // 仅仅用来验证的请求，回显echostr
                return Response.ok().entity(sign.getEchostr()).type(MediaType.TEXT_PLAIN).build();
            }
        }
        // --------------------------------消息签名验证------------------------------------//
        // 处理消息内容
        WxCrypto wxCrypt = null;
        String xmlContent;
        if (WxConsts.ENCRYPT_TYPE_AES.equals(sign.getEncryptType())) {
            // 使用AES加密
            wxCrypt = new WxCrypto(config.getToken(), config.getEncodingAesKey(), config.getAppId());
            // 解析网络数据
            EncryptMessage encryptMsg = WxMsgCrFactory.xmlToBean(data, EncryptMessage.class);
            // 验证数据签名
            String signature = WxCrypto.genSHA1(wxCrypt.getToken(), sign.getTimestamp(), sign.getNonce(), encryptMsg.getEncrypt());
            if (!signature.equals(sign.getMsgSignature())) {
                return Response.ok().entity("数据签名异常").type(MediaType.TEXT_PLAIN).build();
            }
            xmlContent = wxCrypt.decrypt(encryptMsg.getEncrypt());
        } else {
            // raw 明文传输
            xmlContent = data;
        }
        // --------------------------------消息内容处理------------------------------------//
        // 解析消息内容
        IMessage message = xml2Message(xmlContent); // 转换为bean
        if (message == null) {
            return Response.ok().entity("消息内容无法解析").type(MediaType.TEXT_PLAIN).build();
        }
        // 通过监听器处理消息内容
        Object bean = listenerManager.accept(message); // 得到处理的结构
        if (bean == null) {
            return Response.ok().entity("消息内容无法应答").type(MediaType.TEXT_PLAIN).build();
        }
        // --------------------------------响应结果解析------------------------------------//
        // 分析结果
        String reault = bean instanceof String ? bean.toString() : WxMsgCrFactory.beanToXml(bean);
        if (wxCrypt != null) {
            // 消息内容需要加密返回
            String encryText = wxCrypt.encrypt(reault);
            // 构建返回对象
            EncryptMessage encryptMsg = new EncryptMessage();
            encryptMsg.setEncrypt(encryText);
            encryptMsg.setNonce(WxCrypto.genRandomStr());
            encryptMsg.setTimeStamp(System.currentTimeMillis());
            // 生成签名
            String signature = WxCrypto.genSHA1(wxCrypt.getToken(), encryptMsg.getTimeStamp(), encryptMsg.getNonce(), encryText);
            encryptMsg.setMsgSignature(signature);
            // 生成xml内容
            reault = WxMsgCrFactory.beanToXml(encryptMsg);
        }
        // --------------------------------返回处理的结果------------------------------------//
        return Response.ok().entity(reault).build();
    }

}
