package com.suisrc.weixin;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 测试使用
 * 
 * @author Y13
 *
 */
@ApplicationPath("s")
public class JaxrsActivator extends Application {

	// /**
	// * 获取开发的restful api接口
	// */
	// @Override
	// public Set<Class<?>> getClasses() {
	// Set<Class<?>> classes = new HashSet<>();
	// // Provider
	// classes.add(ResponseExceptionMapper.class);
	// classes.add(RequestFilter.class);
	// classes.add(ResponseFilter.class);
	// // rest api
	// classes.add(TApi.class);
	//
	// return classes;
	// }

	public static void main(String[] args) {
		new JaxrsActivator().forEach(System.out::println);
	}

	public void forEach(Consumer<JaxrsActivator> action) {
		action.accept(this);
	}
}
