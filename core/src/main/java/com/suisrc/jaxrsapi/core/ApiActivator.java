package com.suisrc.jaxrsapi.core;

import java.util.Set;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * 激活接口适配
 * @author Y13
 *
 */
public interface ApiActivator {
	
	/**
	 * 初始化
	 */
	void initialized();
	/**
	 * 获取基础路径地址
	 * @return
	 */
	String getBaseUrl();
	
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
		if( type == WebTarget.class ) { 
			// 客户端默认不断新建，为了达到最优访问速度，  最好不使用默认，重写该方法
			return (T) ClientBuilder.newClient().target(getBaseUrl());
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
