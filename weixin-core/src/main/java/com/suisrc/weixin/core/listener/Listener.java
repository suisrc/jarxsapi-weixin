package com.suisrc.weixin.core.listener;

/***
 * 监听接口
 * @author Y13
 *
 */
public interface Listener<T> {
	
	/**
	 * 接受监听对象
	 * @param bean null表示对该条内容没有记性处理
	 * @return
	 */
	Object accept(T bean);
	
	/**
	 * 监听是否有效
	 * @return
	 */
	default boolean effect() {
		return true;
	}
	
	/**
	 * 监听接受的顺序
	 * @return
	 */
	default int priority() {
		return 0;
	}

}
