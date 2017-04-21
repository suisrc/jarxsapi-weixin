package com.qq.weixin.mp;

import java.io.IOException;
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
import com.qq.weixin.mp.result.ErrCode;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.runtime.NSCFactory;
import com.suisrc.jaxrsapi.core.util.Utils;

/**
 * 程序入口配置
 * @author Y13
 * https://api.weixin.qq.com/cgi-bin
 */
@Named("com.qq.weixin.api")
@ApplicationScoped
public class ServerActivator implements ApiActivator {

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
	protected String aesKey;
	
	/**
	 * 获取基础路径地址
	 */
	protected String baseUrl;
	
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
		token = System.getProperty("com.qq.weixin.endpoint.access_token");
		aesKey = System.getProperty("com.qq.weixin.endpoint.aes_key");
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

	@Override
	public String getBaseUrl() {
		return baseUrl;
	}
	
	@Override
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
			return (T) appId;
		case WxConsts.APP_SECRET:// 设置微信公众号的app corpSecret
			return (T) appSecret;
		case WxConsts.ACCESS_TOKEN:// 设置微信公众号的token
			return (T) token;
		case WxConsts.AES_KEY:// 设置消息加解密密钥
			return (T) aesKey;
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
	
	@Override
	public <T> void setAdapter(Class<T> type, T value) {
		if( type == ResteasyProviderFactory.class ) {
			providerFactory = (ResteasyProviderFactory) value;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException { 
		NSCFactory.build(true, ServerActivator.class);
		ServerActivator activator = NSCFactory.get(ServerActivator.class);
		activator.token = "1243";
		
		UserRest userRest = NSCFactory.get(UserRest.class);
		
		ErrCode content = userRest.getUserGet(null, null);
//		UserInfoParam param = new UserInfoParam();
//		param.setOpenid("123456");
//		String content = userRest.getUserInfo(param);
		System.out.println(content);
	}
	
	
}
