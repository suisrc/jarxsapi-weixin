package com.suisrc.weixin.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ClientBuilderFactory;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.weixin.core.bean.WxAccessToken;
import com.suisrc.weixin.core.bean.WxAccessToken.Status;
import com.suisrc.weixin.core.filter.WxClientResponseFilter;

/**
 * 程序入口配置抽象
 * 
 * @author Y13
 */
public abstract class AbstractWeixinActivator implements ApiActivator, WxConfig {

    /**
     * access token需要记性同步处理
     */
    protected static final Map<String, AtomicReference<WxAccessToken>> accessTokenMap = new ConcurrentHashMap<>(1, 1); // 线程安全

    // ------------------------------------------------------------------------------------------------------------------//

    /**
     * 微信公众号的appid
     */
    protected String appId;

    /**
     * 微信公众号的app corpSecret
     */
    protected String appSecret;

    /**
     * 微信公众号的token
     */
    protected String token;

    /**
     * 消息加解密密钥
     */
    protected String encodingAesKey;

    /**
     * 获取基础路径地址
     */
    protected String baseUrl;

    /**
     * 提供器工厂
     */
    protected ResteasyProviderFactory providerFactory = null;

    /**
     * 异步多线程访问缓冲
     */
    protected ExecutorService executor = null;

    /**
     * 访问的客户端
     */
    protected Client client;

    /**
     * access token 强原子操作
     */
    // protected volatile WxAccessToken accessToken;
    protected AtomicReference<WxAccessToken> accessToken = null;

    /**
     * 构造方法
     */
    protected AbstractWeixinActivator() {
        /*
         * 写在这里是方便后面初始化方法进行替换,更新
         */
        appId = System.getProperty(WxConsts.KEY_CORE_APP_ID);
        appSecret = System.getProperty(WxConsts.KEY_CORE_APP_SECRET);
        token = System.getProperty(WxConsts.KEY_CORE_TOKEN);
        encodingAesKey = System.getProperty(WxConsts.KEY_CORE_ENCODING_AES_KEY);
    }

    // ----------------------------------------------------------------ZERO ApiActivator
    /**
     * 构造后被系统调用 进行内容初始化
     */
    @Override
    public void init() {
        // 初始化
        initAccessToken();
        // 构建客户端创建器
        client = getTargetClient();
    }

    /**
     * 初始化远程访问的客户端
     */
    protected Client getTargetClient() {
        ClientBuilder clientBuilder = createClientBuilder();// 配置网络通信内容
        if (clientBuilder instanceof ResteasyClientBuilder) {
            ResteasyClientBuilder rcBuilder = (ResteasyClientBuilder) clientBuilder;
            if (executor != null) {
                rcBuilder.asyncExecutor(executor); // 配置线程池，默认使用线程池为固定大小最大10个线程
            }
            if (providerFactory != null) {
                rcBuilder.providerFactory(providerFactory);
            }
            ClientBuilderFactory.initHttpEngineThreadSaft(rcBuilder);
        }
        Client client = clientBuilder.build();
        // 加入Produces矫正监听器
        client.register(WxClientResponseFilter.class);
        return client;
    }

    /**
     * 获取一个Client Builder
     */
    protected ClientBuilder createClientBuilder() {
        // return ClientBuilder.newBuilder();
        return ClientBuilderFactory.newBuilder();
    }

    /**
     * 获取系统中常用的数据配置 返回系统中常量数据
     */
    @SuppressWarnings("unchecked")
    public <T> T getAdapter(String key) {
        switch (key) {
            case WxConsts.APP_ID:// 设置微信公众号的appid
                return (T) getAppId();
            case WxConsts.APP_SECRET:// 设置微信公众号的app corpSecret
                return (T) getAppSecret();
            case WxConsts.ACCESS_TOKEN:// 设置微信公众号的 access token
                return (T) getAccessToken();
            case WxConsts.TOKEN:// 设置微信公众号的token
                return (T) getToken();
            case WxConsts.ENCODING_AES_KEY:// 设置消息加解密密钥
                return (T) getEncodingAesKey();
            case WxConsts.BASE_URL: // 获取基础地址
                return (T) getBaseUrl();
            default:
                return ApiActivator.super.getAdapter(key);
        }
    }

    /**
     * 获取系统的对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getAdapter(Class<T> type) {
        if (type == WebTarget.class) {
            return (T) client.target(getBaseUrl());
        } else if (type == Client.class) {
            return (T) client;
        } else if (type == ResteasyProviderFactory.class) {
            return (T) providerFactory;
        }
        return null;
    }

    /**
     * 主要是为了防止不支持javaee7.0标准的反向内容注入
     */
    public <T> void setAdapter(Class<T> type, T value) {
        if (type == ResteasyProviderFactory.class) {
            providerFactory = (ResteasyProviderFactory) value;
        }
    }

    //--------------------------------------------------ZERO WxConfig
    
    public String getBaseUrl() {
        return baseUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getToken() {
        return token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    //--------------------------------------------------ZERO AccessToken
    
    /**
     * 初始化构造AccessToken
     */
    protected void initAccessToken() {
        String key = null;
        if (getAppId() != null && getAppSecret() != null) {
            key = getAppId() + "$#->" + getAppSecret();
            accessToken = accessTokenMap.get(key);
            if (accessToken != null) {
                return;
            }
        }

        if (accessToken == null) {
            accessToken = new AtomicReference<>();
            WxAccessToken token = (WxAccessToken) readTempObject(); // 读取系统文件中的access token
            if (token == null) {
                token = new WxAccessToken();
            } // 初始化一个无效凭证
            accessToken.set(token);
        }
        if (key != null) {
            accessTokenMap.put(key, accessToken);
        }
    }

    /**
     * 获取access token
     */
    public String getAccessToken() {
        Status status = accessToken.get().checkValid();
        switch (status) {
            case NONE:
            case EXPIRED:// 同步刷新
                newAccessToken();
                break;
            case WILL_EXPIRE:// 异步刷新
                if (!accessToken.get().getSync().get()) {
                    CompletableFuture.runAsync(this::newAccessToken, executor);
                }
            case VALID:
                break; // access token 正常有效
        }
        return accessToken.get().getAccessToken();
    }

    /**
     * 清空access token
     */
    public void clearAccessToken() {
        accessToken.set(new WxAccessToken()); // 清空access token
    }

    /**
     * 获取 weixin access token, 
     * 不需要保证线程安全，框架中已经控制了线程安全
     * 获取新的token对象
     * 
     * @return
     */
    protected WxAccessToken getWxAccessToken() {
        throw new RuntimeException("method 'getWxAccessToken' isn't implement!");
    }

    /**
     * 更新access token
     */
    private synchronized void newAccessToken() {
        if (accessToken.get().checkValid() == Status.VALID) {
            return;
        } // 已经被其他线程同步过
        try {
            accessToken.get().getSync().set(true); // 同步标识打开
            WxAccessToken token = getWxAccessToken();
            accessToken.set(token);
        } finally {
            accessToken.get().getSync().set(false); // 同步表示关闭
        }
        writeTempObject(accessToken.get());
    }

    /**
     * 临时缓存文件的名字
     * 
     * @return
     */
    protected String getTempFileName() {
        return null;
    }

    /**
     * 把access token写入文件中，避免测试中频繁调用
     * 
     * @param token
     */
    protected void writeTempObject(Object obj) {
        if (!WxConsts.DEBUG) {
            return;
        }

        String filename = getTempFileName();
        if (filename == null || filename.isEmpty()) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中读入access token
     * 
     * @return
     */
    protected Object readTempObject() {
        if (!WxConsts.DEBUG) {
            return null;
        }

        String filename = getTempFileName();
        if (filename == null || filename.isEmpty()) {
            return null;
        }
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            ois.close();
            fis.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
