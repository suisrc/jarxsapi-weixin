package com.suisrc.jaxrsapi.core.runtime;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.jboss.jandex.ClassInfo;
import org.jboss.jandex.DotName;
import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;
import org.jboss.resteasy.client.jaxrs.internal.LocalResteasyProviderFactory;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.suisrc.jaxrsapi.core.ApiActivator;
import com.suisrc.jaxrsapi.core.ServiceClient;

import javassist.ClassPool;
import javassist.CtClass;

/**
 * 执行本地处理
 * 系统中静态存放远程访问实体
 * 
 * 该对象应对系统中没有web容器和自动加载机制的系统中使用
 * @author Y13
 *
 */
@SuppressWarnings("unchecked")
public class NSCFactory /* NativeServiceClientFactory */ {
	
	@SuppressWarnings("rawtypes")
	private static final Map clientImpls = new HashMap<>();
	
	public static <T> T get(Class<T> iface) {
		return (T) clientImpls.get(iface);
	}

	/**
	 * 构建实体远程访问代理对象
	 * @param clazzes
	 */
	public static void build( Class<? extends ApiActivator>... clazzes ) {
		build( false, clazzes );
	}
	/**
	 * 构建实体远程访问代理对象
	 * @param clazzes
	 */
	public static void build( boolean debug, Class<? extends ApiActivator>... clazzes ) {
		if( !clientImpls.isEmpty() ) { clientImpls.clear(); } // 情况原有系统数据

		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Index index = createIndexer(loader, clazzes);
		ClassPool ctPool = ClassPool.getDefault();
		// 前期 clientImpls 中都是ApiActivator对象
		for( Object activatorObj : clientImpls.values().toArray() ) {
			ApiActivator activator = (ApiActivator) activatorObj;
			// 由于默认的provider在本地访问中是失效的，所以在这里提供新的访问方式
			activator.setAdapter(ResteasyProviderFactory.class, getNativeProviderFactory());
			// 初始化
			activator.initialized(); 
			for( Class<?> apiClass : activator.getClasses() ) {
				ClassInfo info = index.getClassByName(DotName.createSimple(apiClass.getName()));
				try {
					// 生成api代理实体
					CtClass ctClass = ClientServiceFactory.createImpl(activator, index, ctPool, 
							apiClass.getCanonicalName() + "_$$jaxrsapi", info);
					Class<?> clazz = ctClass.toClass(loader, null);
					if( debug ) { debugCtClass(ctClass); }
					ctClass.freeze(); // 释放
					Object apiObj = clazz.newInstance(); // 生成通信代理
					if( apiObj instanceof ServiceClient ) {
						ServiceClient sc = (ServiceClient) apiObj;
						sc.setActivator(activator); // 设置激活器
						sc.initialized(); // 执行初始化
					}
					clientImpls.put(apiClass, apiObj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 提供器
	 * @return
	 */
	private static ResteasyProviderFactory getNativeProviderFactory() {
		// create a new one
		ResteasyProviderFactory providerFactory = new LocalResteasyProviderFactory(ResteasyProviderFactory.newInstance());
		RegisterBuiltin.register(providerFactory);
		providerFactory.registerProvider(JacksonJsonProvider.class, true); // 装载翻译器
		return providerFactory;
	}

	/**
	 * 构建Index
	 * @param loader
	 * @param clazzes
	 * @return
	 */
	private static Index createIndexer(ClassLoader loader, Class<? extends ApiActivator>... clazzes) {
		Indexer indexer = new Indexer();
		try {
			Set<Class<?>> useClasses = new HashSet<>();
			for( Class<? extends ApiActivator> activatorClass : clazzes ) {
				ApiActivator activator = activatorClass.newInstance();
				clientImpls.put(activatorClass, activator); // 放入缓存
				for( Class<?> apiClass : activator.getClasses() ) {
					useClasses.add(apiClass);
					for( Method method : apiClass.getMethods() ) {
						for( Class<?> paramType : method.getParameterTypes() ) {
							if( !paramType.isPrimitive() ) {
								useClasses.add(paramType);
							}
						}
					}
				}
			}
			for (Class<?> clazz : useClasses) {
				InputStream is = loader.getResourceAsStream(clazz.getName().replace('.', '/') + ".class");
				indexer.index(is);
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return indexer.complete();
	}
	
	private static void debugCtClass(CtClass ctClass) {
		try {
			ctClass.writeFile("D:/classes");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空
	 */
	public static void destory() {
		clientImpls.clear();
	}

}
