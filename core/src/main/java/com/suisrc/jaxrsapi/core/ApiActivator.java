package com.suisrc.jaxrsapi.core;

import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

/**
 * 激活接口适配
 * @author Y13
 *
 */
public interface ApiActivator {
	
	/**
	 * 获取接口列表
	 * @return
	 */
	Set<Class<?>> getClasses();
	
	/**
	 * 通过适配器获取数据
	 * @param key
	 * @return
	 */
	default
	<T> T getAdapter(String key) {
		return null;
	}
	
	/**
	 * 设定适配器内容
	 * @param key
	 * @param value
	 */
	default
	<T> void setAdapter(String key, T value) {
	}
	
	/**
	 * 通过适配器获取对象
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default
	<T> T getAdapter( Class<T> type ) {
		if( type == Client.class ) { 
			// 客户端默认不断新建，为了达到最优访问速度，  最好不使用默认，重写该方法
			return (T) ClientBuilder.newClient();
		}
		return null;
	}
	
	/**
	 * 设定适配器对象
	 * @param type
	 * @param value
	 */
	default
	<T> void setAdapter( Class<T> type, T value ) {
	}

}
