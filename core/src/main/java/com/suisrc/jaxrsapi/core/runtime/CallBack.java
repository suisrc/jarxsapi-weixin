package com.suisrc.jaxrsapi.core.runtime;

import javassist.CtClass;

/**
 * 创建接口实现后，回调方法
 * @author Y13
 *
 */
public interface CallBack {
	
	/**
	 * 接受创建结果，进行回调生成的结构，进一步完成初始化
	 * @param iface
	 * @param impl
	 */
	void accept(Class<?> iface, CtClass impl) throws Exception;

}
