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
import com.suisrc.weixin.core.msg.UnknowMessage;

/**
 * 跟微信服务器捆绑
 * 
 * @author Y13
 *
 */
public abstract class AbstractWxBinding<T> {

    /**
     * 监听器
     */
    protected ListenerManager<T> listenerManager;

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
    protected abstract void initialized();
    
    /**
     * 获取数据对应的类型
     * @param node
     * @return
     */
    protected abstract Class<? extends IMessage> findMsgTypeClass(WxMsgNode node);
    
    /**
     * 判断用于信息
     * 
     * 理论上这里的消息不是发给微信服务器的，请注意
     */
    protected void assertClientInfo() {}
    
    /**
     * 输入log, 可以重写该部分，输入到logger中
     * 
     * @param log
     */
    protected void printThrowable(Throwable e) {
        System.out.println(e.getMessage());
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
     * 把字符串转换为消息
     * @param xml
     * @return
     */
    @SuppressWarnings("deprecation")
    protected IMessage str2Bean(String content, boolean isJson) {
        WxMsgNode node = WxMsgCrFactory.str2Node(content, isJson);
        Class<? extends IMessage> msgType = findMsgTypeClass(node);
        IMessage bean = null;
        if (msgType != null) {
            // 解析数据
            try {
                bean = node.toBean2(msgType);
            } catch (Throwable e) {
                // 使用 node.toBean在某种情况下，比如@JacksonXmlElementWrapper是无法解析的
                System.out.println(String.format("Message content can not be resolved [%s]:%s", 
                        msgType.getCanonicalName(), e.getMessage()));
                // 尝试使用基础解析工具解析
                bean = WxMsgCrFactory.str2Bean(content, msgType, isJson);
            }
        }
        if (bean == null) {
            // 数据无法解析
            bean = new UnknowMessage();
            ((UnknowMessage)bean).setRawNode2(node);
        }
        // 给出数据的原始类型
        bean.setJson(isJson);
        // 给出原始数据内容--防止后面用于验证时候使用一些漏掉的信息
        bean.setRawData2(content);
        // 返回解析的结果
        return bean;
    }

    /**
     * 把消息转换为字符串
     * @param xml
     * @return
     */
    protected String bean2Str(Object bean, boolean isJson) {
        return WxMsgCrFactory.bean2Str(bean, isJson);
    }
    
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
        assertClientInfo();
        if (config.getToken() == null || !sign.isValid()) {
            return "Illegal request";
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
        try {
            return doInternalWork(sign, data);
        } catch (Throwable e) {
            printThrowable(e);
            // 虽然失败了，但是还是需要通知服务器已经接受到请求
            return Response.ok().entity("success").type(MediaType.TEXT_PLAIN).build();
        }
    }

    /**
     * 微信回调请求绑定
     * 
     * 内部处理方法
     */
    protected Response doInternalWork(WxEncryptSignature sign, String data) {
        assertClientInfo();
        if (data == null || data.isEmpty()) {
            // return Response.ok().entity("There is no valid request data").type(MediaType.TEXT_PLAIN).build();
            throw new RuntimeException("There is no valid request data");
        }
        // 确定数据传输格式
        boolean isJson = data.startsWith("<xml>") ? false : true;
        // --------------------------------服务器验证------------------------------------//
        if (isEncrypt && (config.getToken() == null || !sign.isValid())) {
            // 没有签名信息
            return Response.ok().entity("Illegal request").type(MediaType.TEXT_PLAIN).build();
        }
        if (sign.getSignature() != null) {
            // 服务器验证
            String signature = WxCrypto.genSHA1(config.getToken(), sign.getTimestamp(), sign.getNonce());
            if (!signature.equals(sign.getSignature())) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                return Response.ok().entity("Illegal request").type(MediaType.TEXT_PLAIN).build();
            }
            if (sign.getEchostr() != null && !sign.getEchostr().isEmpty()) {
                // 仅仅用来验证的请求，回显echostr
                return Response.ok().entity(sign.getEchostr()).type(MediaType.TEXT_PLAIN).build();
            }
        }
        // --------------------------------消息签名验证------------------------------------//
        // 处理消息内容
        WxCrypto wxCrypt = null;
        String content;
        if (WxConsts.ENCRYPT_TYPE_AES.equals(sign.getEncryptType())) {
            // 使用AES加密
            wxCrypt = new WxCrypto(config.getToken(), config.getEncodingAesKey(), config.getAppId());
            // 解析网络数据
            EncryptMessage encryptMsg = WxMsgCrFactory.str2Bean(data, EncryptMessage.class, isJson);
            // 验证数据签名
            String signature = WxCrypto.genSHA1(wxCrypt.getToken(), sign.getTimestamp(), sign.getNonce(), encryptMsg.getEncrypt());
            if (!signature.equals(sign.getMsgSignature())) {
                return Response.ok().entity("Data signature exception").type(MediaType.TEXT_PLAIN).build();
            }
            content = wxCrypt.decrypt(encryptMsg.getEncrypt());
        } else {
            // raw 明文传输
            content = data;
        }
        // --------------------------------消息内容处理------------------------------------//
        // 解析消息内容
        IMessage message = str2Bean(content, isJson); // 转换为bean
        if (message == null) {
            // return Response.ok().entity("Message content can not be resolved").type(MediaType.TEXT_PLAIN).build();
            throw new RuntimeException("Message content can not be resolved:" + content);
        }
        // message.setJson(isJson); // 告诉系统数据的来源格式
        // 通过监听器处理消息内容
        Object bean = listenerManager.acceptmsg(message); // 得到处理的结构
        if (bean == null) {
            // return Response.ok().entity("Message content can not be answered").type(MediaType.TEXT_PLAIN).build();
            throw new RuntimeException("Message content can not be answered:" + content);
        }
        if (bean instanceof IMessage) {
            isJson = ((IMessage)bean).isJson(); // 用新的格式要求替换
        }
        // --------------------------------响应结果解析------------------------------------//
        // 分析结果
        String reault = bean instanceof String ? bean.toString() : bean2Str(bean, isJson);
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
            reault = bean2Str(encryptMsg, isJson);
        }
        // --------------------------------返回处理的结果------------------------------------//
        return Response.ok().entity(reault).type(isJson ? MediaType.APPLICATION_JSON : MediaType.APPLICATION_XML).build();
    }

}
