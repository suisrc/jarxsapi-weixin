package com.suisrc.jaxrsapi.core.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import com.suisrc.jaxrsapi.core.annotation.RemoteApi;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Utils {

	/**
	 * 获取一个包内容中的所有Remote Restful Api接口
	 * 这里默认使用了RemoteApi接口
	 * @param packageName
	 * @return
	 */
	public static Set<Class<?>> getRemoteApiClasses(Class<? extends Annotation> anno, boolean honorInherited, String... pkgs ) {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.forPackages(pkgs);
		config.setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
		Reflections reflections = new Reflections(config);
		return reflections.getTypesAnnotatedWith(anno != null ? anno : RemoteApi.class, honorInherited);
	}

	/**
	 * 获取一个接口的所有实现
	 * @param packageName
	 * @return
	 */
	public static <T> Set<Class<? extends T>> getSubclasses(Class<T> iface, String... pkgs ) {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.forPackages(pkgs);
		config.setScanners(new SubTypesScanner());
		Reflections reflections = new Reflections(config);
		return reflections.getSubTypesOf(iface);
	}
	
	/**
	 * 获取CtClass,如果找不到，尝试增加后，再次查找
	 * 解决发布到发布到Jboss, Tomcat上，发生javassist.NotFoundException异常
	 * @param pool
	 * @param clazz
	 * @return
	 * @throws NotFoundException 
	 */
	public static synchronized CtClass getCtClass( ClassPool pool, Class<?> clazz ) throws NotFoundException {
		try {
			return pool.get(clazz.getName());
		} catch (NotFoundException e) {
			ClassClassPath classPath = new ClassClassPath(clazz);  
			pool.insertClassPath(classPath);
			return pool.get(clazz.getName());
		}
	}

	/**
	 * 获取CtClass,如果找不到，尝试增加后，再次查找
	 * 解决发布到发布到Jboss, Tomcat上，发生javassist.NotFoundException异常
	 * @param pool
	 * @param clazz
	 * @return
	 * @throws NotFoundException 
	 */
	public static synchronized CtClass getCtClass(ClassPool pool, String className) throws NotFoundException, ClassNotFoundException {
		try {
			return pool.get(className);
		} catch (NotFoundException e) {
			ClassClassPath classPath = new ClassClassPath(Class.forName(className));  
			pool.insertClassPath(classPath);
			return pool.get(className);
		}
	}

	/**
	 * 获取消息的内容
	 * @param inputStream
	 * @return
	 */
	public static String getContent(InputStream inputStream) {
		try {
			InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
			char[] bufs = new char[1024];
			int len = 0;
			StringBuilder sbir = new StringBuilder();
			while( (len = isr.read(bufs)) > 0 ) {
				sbir.append(bufs, 0, len);
			}
			return sbir.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
