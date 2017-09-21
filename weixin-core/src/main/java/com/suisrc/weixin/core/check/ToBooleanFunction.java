package com.suisrc.weixin.core.check;

/**
 * 系统中没有 ToBooleanFunction接口
 * 
 * @author Y13
 *
 */
@FunctionalInterface
public interface ToBooleanFunction<T> {

    /**
     * 接口方法，返回值为boolean
     * @param t
     * @return
     */
    boolean applyAsBoolean(T t);
}
