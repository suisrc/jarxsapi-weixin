package com.qq.weixin.mp;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.qq.weixin.mp.api.UserRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.util.Utils;

/**
 * 程序入口配置
 * @author Y13
 * https://api.weixin.qq.com/cgi-bin
 */
@Named("com.qq.weixin.api")
@ApplicationScoped
public class ServerActivator implements ApiActivator, WxConfig {

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
	 * access token
	 */
	private volatile String accessToken;
	
	/**
	 * 访问的客户端
	 */
	private Client client;
	
	/**
	 * 提供器工厂
	 */
	private ResteasyProviderFactory providerFactory;
	
	/**
	 * 构造后被系统调用
	 * 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		appId = System.getProperty("com.qq.weixin.endpoint.app_id");
		appSecret = System.getProperty("com.qq.weixin.endpoint.app_secret");
		token = System.getProperty("com.qq.weixin.endpoint.token");
		encodingAesKey = System.getProperty("com.qq.weixin.endpoint.encoding_aes_key");
		baseUrl = System.getProperty("com.qq.weixin.endpoint.base_url", "https://api.weixin.qq.com/cgi-bin");
		
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();// 配置网络通信内容
		//clientBuilder.asyncExecutor(asyncExecutor); // 配置线程池，默认使用线程池为固定大小最大10个线程
		if( clientBuilder instanceof ResteasyClientBuilder ) {
			ResteasyClientBuilder rcb = (ResteasyClientBuilder) clientBuilder;
			if( providerFactory != null ) {
				rcb.providerFactory(providerFactory);
			}
		}
		client = clientBuilder.build();
	}

	public String getBaseUrl() {
		return baseUrl;
	}
	
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<>();
		set.addAll(Utils.getRestApiClasses(UserRest.class.getPackage().getName(), null, true));
		return set;
		
	}
	
	/**
	 * 获取系统中常用的数据配置
	 * 返回系统中常量数据
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
		default:
			return null;
		}
	}
	
	/**
	 * 获取系统的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAdapter( Class<T> type ) {
		if( type == WebTarget.class ) {
			return (T) client.target(getBaseUrl() );
		} else if( type == Client.class ) { 
			return (T)client;
		}
		return null;
	}
	
	public <T> void setAdapter(Class<T> type, T value) {
		if( type == ResteasyProviderFactory.class ) {
			providerFactory = (ResteasyProviderFactory) value;
		}
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
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
}
