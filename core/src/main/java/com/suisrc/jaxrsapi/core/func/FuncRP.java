package com.suisrc.jaxrsapi.core.func;

/**
 * 与java.lang.Runnable相比，增加回调的时候参数和返回值
 * @author Y13
 *
 * @param <R>
 * @param <P>
 */
@FunctionalInterface
public interface FuncRP<R, P> {
	
	/**
	 * 执行的方法（回调的方法）
	 * @param parameter
	 */
	 R exec(P parameter);
}
