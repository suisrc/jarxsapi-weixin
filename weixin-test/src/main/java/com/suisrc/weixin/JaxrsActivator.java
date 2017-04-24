package com.suisrc.weixin;

import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.google.common.collect.Sets;
import com.suisrc.weixin.mp.WxBinding;

/**
 * 测试使用
 * @author Y13
 *
 */
@ApplicationPath("t")
public class JaxrsActivator extends Application {

	/**
	 * 获取开发的restful api接口
	 */
	@Override
	public Set<Class<?>> getClasses() {
		return Sets.newHashSet(WxBinding.class);
	}
}
