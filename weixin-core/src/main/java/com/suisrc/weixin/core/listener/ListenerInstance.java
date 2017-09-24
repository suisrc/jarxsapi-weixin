package com.suisrc.weixin.core.listener;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;

/**
 * 监听器的创建器
 * @author Y13
 *
 */
@SuppressWarnings("rawtypes")
@FunctionalInterface
public interface ListenerInstance {

    /**
     * 创建一个类型的实体对象
     * @param clazz
     * @return
     */
    Listener newInstance(Class<? extends Listener> clazz);
    
    /**
     * 默认的构建器
     */
    ListenerInstance DEFAULT = clazz -> JaxrsapiUtils.newInstance(clazz);
}
