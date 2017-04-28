package com.suisrc.jaxrsapi.core.func;

/**
 * 如何修正数据
 * @author Y13
 *
 */
public interface ValueHandler<T> {

	/**
	 * 具体如何修正数据
	 * @param value
	 * @return
	 */
	public T revise( T value );
}
