package com.qq.weixin.mp;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Application;

import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;

import com.qq.weixin.mp.api.UserInfoRest;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.annotation.Server;
import com.suisrc.jaxrsapi.core.runtime.ServiceClientProcessor;

/**
 * 程序入口配置
 * @author Y13
 * https://api.weixin.qq.com/cgi-bin
 */
//@Server( baseUrl = "${com.qq.weinxin.api}" )
@Server( baseUrl = "https://api.weixin.qq.com" )
@ApplicationPath("cgi-bin")
public class ServerActivator extends Application implements ApiActivator {

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
	 * 客户端构造器
	 */
	private ClientBuilder clientBuilder;
	
	/**
	 * 构造后被系统调用
	 * 进行内容初始化
	 */
	@PostConstruct
	public void initialized() {
		appId = System.getProperty("system.weixin.endpoint.app_id");
		appSecret = System.getProperty("system.weixin.endpoint.app_secret");
		token = System.getProperty("system.weixin.endpoint.access_token");
		aesKey = System.getProperty("system.weixin.endpoint.aes_key");
		
		clientBuilder = ClientBuilder.newBuilder();
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<>();
		set.add(UserInfoRest.class);
//		set.addAll(Utils.getRestApiClasses(UserInfoRest.class.getPackage().getName(), true));
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
		if( type == Client.class ) { 
			// 客户端默认不断新建，为了达到最优访问速度，  最好不使用默认，重写该方法
			return (T) clientBuilder.build();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException { 
		ServerActivator sa = new ServerActivator();
		Indexer indexer = new Indexer();
		ClassLoader cl = ServerActivator.class.getClassLoader();
		for( Class<?> clazz : sa.getClasses() ) {
			InputStream is = cl.getResourceAsStream(clazz.getName().replace('.', '/') + ".class");
			indexer.index(is);
			is.close();
		}
		InputStream is = ServerActivator.class.getResourceAsStream(ServerActivator.class.getSimpleName() + ".class");
		indexer.index(is);
		is.close();
		ServiceClientProcessor pr = new ServiceClientProcessor();
		Index index = indexer.complete();
		pr.processMetadata(index);
	}
	
	
}
