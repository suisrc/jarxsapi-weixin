package com.suisrc.weixin.core.listener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import javax.inject.Named;

import com.suisrc.jaxrsapi.core.util.Utils;

/**
 * 监听事件管理器
 * 用于管理微信中所有回调监听
 * 
 * 监听器只能进行初始化构建（build）,如果监听内容发生变化，需要重新启动服务进行重新构建。
 * 监听器的扫描是自动的，可以通过build中的参数指定扫描器扫描的范围
 * @author Y13
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ListenerManager extends HashMap<Class, Listener[]>{
	private static final long serialVersionUID = 2802236925094985275L;
	
	/**
	 * 监控管理器的所有者
	 * 如果所有者为null，为公共监听器，监听的内容可以为所有没有@Named标记的共通监听
	 */
	private Object owner;
	
	/**
	 * 构造
	 * @param owner
	 */
	public ListenerManager(Object owner) {
		this.owner = owner;
	}
	
	/**
	 * 接受对象
	 * @param bean
	 * @return
	 */
	public <T> Object accept(T bean) {
		Listener<T>[] listeners = get(bean.getClass());
		if( listeners == null ) { return null; }
		
		for( Listener<T> listener : listeners ) {
			Object obj = listener.accept(bean);
			if( obj != null ) { return obj; }
		}
		return null;
	}
	
	/**
	 * 获取监听的类型
	 * @param clazz
	 * @return
	 */
	private <T> Class<T> getKey( Class<? extends Listener<T>> clazz ) {
		Named named = clazz.getAnnotation(Named.class);
		if( owner == null && named == null) {
			// 监听没有所属者，采用午标记的监听内容
		}else if( owner != null && named != null && named.value().equals(owner.getClass().getCanonicalName()) ) {
			// @Named标记的内容必须是监听所有这的名字
		} else {
			return null; // 鉴别条件验证没有通过
		}
		// 查询监听的内容类型
		Class clz = clazz;
		while( clz != Object.class ) {
			for( Type type : clz.getGenericInterfaces() ) {
				if( !(type instanceof ParameterizedType) ) { continue; }
				
				ParameterizedType pType = ((ParameterizedType) type);
				if( pType.getRawType() != Listener.class ) { continue; }
				// 断言监听的类型
				if( pType.getActualTypeArguments().length > 0 ) { 
					return (Class<T>) pType.getActualTypeArguments()[0]; 
				} else {
					return null;
				}
			}
			clz = clz.getSuperclass();
		}
		return null;
	}
	
	/**
	 * 增加监听对象
	 * @param listener
	 */
	public <T> boolean addListener(Listener<T> listener, Class<T> clazz) {
		if( !listener.effect() ) { return false; } // 监听失效
		if( clazz == null ) { 
			clazz = getKey((Class) listener.getClass()); 
			if( clazz == null ) { return false;} // 无法找到监听对象
		}
		
		Listener[] listeners = get(clazz); // 查看原有监听
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
	 * 通过类型增加监听对象
	 * @param clazz
	 * @return
	 */
	public <T> boolean addListener(Class<? extends Listener<T>> listenerClass) {
		try {
			Class<T> clazz = getKey(listenerClass);
			if( clazz == null ) { return false;} // 无法找到监听对象
			Listener<T> listener = (Listener<T>) listenerClass.newInstance();
			return addListener(listener, clazz);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 构建监听器，通过包扫描，增加监听
	 */
	public void scanPackages(String... pkgs) {
		Collection<Class<? extends Listener>> listenters = Utils.getSubclasses(Listener.class, pkgs);
		if( listenters == null || listenters.isEmpty() ) { return; }
//		listenters.forEach(this::addListener);
		for( Class<? extends Listener> listener : listenters ) {
			addListener( (Class) listener );
		}
	}
	
	/**
	 * 通过系统环境变量指定的内容指定扫描监听的范围
	 * @param key
	 */
	public void addPackagesBySysProp(String key) {
		String content = System.getProperty(key);
		if( content == null || (content = content.trim()).isEmpty() ) { return; }
		String[] pkgs = content.split(" *, *");
		scanPackages(pkgs);
	}
	
	/**
	 * 通过系统环境变量指定监听的对象
	 * @param key
	 */
	public void addClassesBySysProp(String key) {
		String content = System.getProperty(key);
		if( content == null || (content = content.trim()).isEmpty() ) { return; }
		String[] classes = content.split(" *, *");
		for(String classname : classes ) {
			if( classname.isEmpty() ) { continue; }
			try {
				Class clazz = Class.forName(classname);
				if( Listener.class.isAssignableFrom(clazz) ) {
					addListener(clazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
