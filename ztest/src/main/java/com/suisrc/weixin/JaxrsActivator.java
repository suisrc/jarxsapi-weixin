package com.suisrc.weixin;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * 测试使用
 * @author Y13
 *
 */
@ApplicationPath("t")
public class JaxrsActivator extends Application {

//	/**
//	 * 获取开发的restful api接口
//	 */
//	@Override
//	public Set<Class<?>> getClasses() {
//		return Sets.newHashSet(TApi.class);
//	}
//	
//	@Override
//	public Set<Object> getSingletons() {
//		return Sets.newHashSet(new SyRequestFilter());
//	}
}
