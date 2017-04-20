package com.suisrc.jaxrsapi.core.util;

import java.util.Collection;

import javax.ws.rs.Path;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ConfigurationBuilder;

public class Utils {

	/**
	 * 获取一个包内容中的所有Rest Api接口
	 * @param packageName
	 * @return
	 */
	public static Collection<Class<?>> getRestApiClasses(String packageName, boolean honorInherited) {
		ConfigurationBuilder config = new ConfigurationBuilder();
		config.forPackages(packageName);
		config.setScanners(new TypeAnnotationsScanner());
		Reflections reflections = new Reflections(config);
		return reflections.getTypesAnnotatedWith(Path.class, honorInherited);
	}
}
