package com.suisrc.weixin.core.listener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;

import javax.inject.Named;

/**
 * 监听事件管理器
 * 用于管理微信中所有回调监听
 * @author Y13
 *
 */
@SuppressWarnings("rawtypes")
public class ListenerManager extends HashMap<Class, Listener[]>{
	private static final long serialVersionUID = 2802236925094985275L;
	
	/**
	 * 鉴别符， 命名
	 */
	private String named = null;
	
	public String getNamed() {
		return named;
	}
	
	public void setNamed(String named) {
		this.named = named;
	}
	
	/**
	 * 增加监听
	 * @param listener
	 */
	public <T> boolean put(Listener<T> listener) {
		if( !listener.effect() ) { return false; } // 监听失效
		Class<?> clazz = getListenerType(listener.getClass());
		if( clazz == null ) { return false; } // 无法找到监听对象
		
		Listener<?>[] listeners = get(clazz); // 查看原有监听
		if( listeners == null ) {
			listeners = new Listener[]{ listener };
		} else {
			listeners = Arrays.copyOf(listeners, listeners.length + 1);
			listeners[listeners.length - 1] = listener;
			Arrays.sort(listeners, (l, r) -> l.priority() - r.priority());
		}
		put(clazz, listeners);
		return true;
	}
	
	/**
	 * 获取监听的类型
	 * @param clazz
	 * @return
	 */
	private Class<?> getListenerType( Class<?> clazz ) {
		if( named != null ) {
			Named named = clazz.getAnnotation(Named.class);
			if( named == null || !named.equals(named.value()) ) {
				return null; // 鉴别条件验证没有通过
			}
		}
		Class clz = clazz;
		while( clz != Object.class ) {
			for( Type type : clz.getGenericInterfaces() ) {
				if( !(type instanceof ParameterizedType) ) { continue; }
				
				ParameterizedType pType = ((ParameterizedType) type);
				if( pType.getRawType() != Listener.class ) { continue; }
				// 断言监听的类型
				if( pType.getActualTypeArguments().length > 0 ) { 
					return (Class<?>) pType.getActualTypeArguments()[0]; 
				} else {
					return null;
				}
			}
			clz = clz.getSuperclass();
		}
		return null;
	}
	
	/**
	 * 接受对象
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Object accept(T bean) {
		Listener<T>[] listeners = get(bean.getClass());
		if( listeners == null ) { return null; }
		
		for( Listener<T> listener : listeners ) {
			Object obj = listener.accept(bean);
			if( obj != null ) { return obj; }
		}
		return null;
	}

}
