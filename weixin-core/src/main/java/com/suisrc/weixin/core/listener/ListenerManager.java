package com.suisrc.weixin.core.listener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Named;

import com.google.common.collect.Sets;
import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;

/**
 * 监听事件管理器 用于管理微信中所有回调监听
 * 
 * 监听器只能进行初始化构建（build）,如果监听内容发生变化，需要重新启动服务进行重新构建。 监听器的扫描是自动的，可以通过build中的参数指定扫描器扫描的范围
 * 
 * @author Y13
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class ListenerManager<C> extends HashMap<Class, Listener[]> {
    private static final long serialVersionUID = 2802236925094985275L;

    /**
     * 监控管理器的所有者 如果所有者为null，为公共监听器， 监听的内容可以为所有没有@Named标记的共通监听
     */
    private C owner;
    /**
     * 所属类型，解决实体和接口标记的问题
     */
    private Class<C> ownerType;
    
    /**
     * 构造
     */
    public ListenerManager() {}
    /**
     * 构造
     * 
     * @param owner
     */
    public ListenerManager(C owner) {
        this.owner = owner;
        this.ownerType = owner == null ? null : (Class<C>)owner.getClass();
    }
    /**
     * 构造
     * 
     * @param owner
     * @param ownerType 
     */
    public ListenerManager(C owner, Class<C> ownerType) {
        this.owner = owner;
        this.ownerType = ownerType;
    }
    
    /**
     * 获取所属者
     */
    public C getOwner() {
        return owner;
    }
    
    /**
     * 获取所属者的类型
     */
    public Class<C> getOwnerType() {
        return ownerType;
    }

    /**
     * 接受对象, 执行内容
     * 
     * @param bean
     * @return
     */
    public Object accept(Object bean) {
        Listener[] listeners = get(bean.getClass());
        if (listeners == null) {
            return null;
        }

        for (Listener listener : listeners) {
            Object obj = listener.accept(bean, owner);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }

    /**
     * 接受对象, 执行内容 该方式会遍历所有接口的继承关系， 但是在我们后期开发的时候，
     * 认为@Include功能能够满足这里的需求， 并且提供更快的处理过程， 所以这里不再需要该方法
     * 
     * 170915 目前舍弃不再使用
     * @param bean
     * @return
     */
    @Deprecated
    public Object accept2(Object bean) {
        Class subClass = bean.getClass();
        while (subClass != null) {
            Object obj = accept2(bean, subClass);
            if (obj != null) {
                return obj;
            }
            for (Class iface : subClass.getInterfaces()) {
                obj = accept2(bean, iface);
                if (obj != null) {
                    return obj;
                }
            }
            subClass = subClass.getSuperclass();
        }
        return null;
    }

    /**
     * 接受对象, 执行内容
     * 与public Object accept2(Object bean)方法成对使用
     * 
     * 170915 目前舍弃不再使用
     * @param bean
     * @return
     */
    @Deprecated
    private Object accept2(Object bean, Class clazz) {
        Listener[] listeners = get(clazz);
        if (listeners != null) {
            for (Listener listener : listeners) {
                Object obj = listener.accept(bean, owner);
                if (obj != null) {
                    return obj;
                }
            }
        }
        return null;
    }

    /**
     * 获取监听的类型
     * 
     * @param clazz
     * @return
     */
    private Set<Class> getKey(Class<?> clazz) {
        Named named = clazz.getAnnotation(Named.class);
        if (named == null) {
            // 泛型监听
        } else if (ownerType != null && named.value().equals(ownerType.getCanonicalName())) {
            // @Named标记的内容必须是监听所有这的名字
        } else {
            return null; // 鉴别条件验证没有通过
        }
        Class genericType = getGenericKey(clazz);
        if (genericType == null) {
            // 查询监听的内容类型
            return null;
        }
        // 查询包含关系
        Include include = clazz.getAnnotation(Include.class);
        if (include == null || include.value().length == 0) {
            return Sets.newHashSet(genericType);
        }
        // 当前genericType失效，不再监听，如果需要进行，需要重新指定到Include中
        // 记性过滤， 防止指定的类型不可用
        Set types = new LinkedHashSet<>(include.value().length, 1);
        for (Class type : include.value()) {
            if (genericType.isAssignableFrom(type)) {
                // 监听的类型可以使用
                types.add(type);
            } else {
                System.err.println(String.format("listener type '%s' is not assignable form '%s' in [%s], ignore.", 
                        genericType.getCanonicalName(), type.getCanonicalName(), clazz.getCanonicalName()));
            }
        }
        return types;
    }

    /**
     * 查询标记绑定的泛型类型
     * 
     * @param clazz
     * @return
     */
    private Class getGenericKey(Class<?> clazz) {
        Class clz = clazz;
        while (clz != Object.class) {
            for (Type type : clz.getGenericInterfaces()) {
                if (!(type instanceof ParameterizedType)) {
                    continue;
                }

                ParameterizedType pType = ((ParameterizedType) type);
                if (pType.getRawType() != Listener.class) {
                    continue;
                }
                // 断言监听的类型
                if (pType.getActualTypeArguments().length > 0) {
                    return (Class) pType.getActualTypeArguments()[0];
                } else {
                    return Object.class;
                }
            }
            clz = clz.getSuperclass();
        }
        return null;
    }

    /**
     * 增加监听对象
     * 
     * @param listener
     */
    private void addListeners(Listener listener, Set<Class> classes) {
        if (listener == null || !listener.effect()) {
            // 监听失效
            return;
        }
        if (classes == null) {
            classes = getKey(listener.getClass());
            if (classes == null) {
                // 无法找到监听对象
                return;
            }
        }
        classes.forEach(clazz -> addListener(listener, clazz));
    }

    /**
     * 增加监听对象 不推荐使用该方法，该方法对监听的有效性和监听内容可行性都没有判断 推荐使用addListeners(Listener listener, Set<Class>
     * classes)作为代替
     * 
     * @param listener 监听对象
     * @param clazz 监听的类型
     * @return Listener 系统中已经存在的监听
     */
    private <T> Listener addListener(Listener<T> listener, Class<T> clazz) {
        Listener[] listeners = get(clazz); // 查看原有监听
        if (listeners == null) {
            put(clazz, new Listener[] {listener});
            return null;
        }
        // 监听重复检测
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i].getClass() == listener.getClass()) {
                // 类型相同，值保留同一个监听,这里保留后加入的，把旧的返回
                Listener olistener = listeners[i];
                listeners[i] = listener;
                if (olistener.priority() != listener.priority()) {
                    // 需要重新排序
                    Arrays.sort(listeners, (l, r) -> l.priority() - r.priority());
                }
                return olistener;
            }
        }
        // 加入新数据
        listeners = Arrays.copyOf(listeners, listeners.length + 1);
        listeners[listeners.length - 1] = listener;
        Arrays.sort(listeners, (l, r) -> l.priority() - r.priority());
        put(clazz, listeners);
        return null;
    }

    /**
     * 通过类型增加监听对象
     * 
     * @param clazz
     * @return
     */
    public boolean addListener(Class<? extends Listener> listenerClass) {
        try {
            Set<Class> classes = getKey(listenerClass);
            if (classes == null || classes.isEmpty()) {
                // 无法找到监听对象
                return false;
            }
            Listener listener = listenerClass.newInstance();
            addListeners(listener, classes);
            return true;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 构建监听器，通过包扫描，增加监听
     */
    public void scanPackages(String... pkgs) {
        Collection<Class<? extends Listener>> listenters = JaxrsapiUtils.getSubclasses(Listener.class, pkgs);
        if (listenters == null || listenters.isEmpty()) {
            return;
        }
        listenters.forEach(this::addListener);
    }

    /**
     * 通过系统环境变量指定的内容指定扫描监听的范围
     * 
     * @param key
     */
    public void addPackagesBySysProp(String key) {
        String content = System.getProperty(key);
        if (content == null || (content = content.trim()).isEmpty()) {
            return;
        }
        String[] pkgs = content.split(" *, *");
        scanPackages(pkgs);
    }

    /**
     * 通过系统环境变量指定监听的对象
     * 
     * @param key
     */
    public void addClassesBySysProp(String key) {
        String content = System.getProperty(key);
        if (content == null || (content = content.trim()).isEmpty()) {
            return;
        }
        String[] classes = content.split(" *, *");
        for (String classname : classes) {
            if (classname.isEmpty()) {
                continue;
            }
            try {
                Class clazz = Class.forName(classname);
                if (Listener.class.isAssignableFrom(clazz)) {
                    addListener(clazz);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
