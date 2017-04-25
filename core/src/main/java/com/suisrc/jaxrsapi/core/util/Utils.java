package com.suisrc.jaxrsapi.core.util;

import java.lang.annotation.Annotation;
import java.util.Collection;

import javax.ws.rs.Path;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;

public class Utils {

	/**
	 * 获取一个包内容中的所有Rest Api接口
	 * @param packageName
	 * @return
	 */
	public static Collection<Class<?>> getRestApiClasses(String packageName, Class<? extends Annotation> anno, boolean honorInherited) {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.forPackages(packageName);
		config.setScanners(new TypeAnnotationsScanner(), new SubTypesScanner());
		Reflections reflections = new Reflections(config);
		return reflections.getTypesAnnotatedWith(anno != null ? anno : Path.class, honorInherited);
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
}
