package com.suisrc.weixin.core.listener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.msg.IMessage;

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
     * 监听创建者
     */
    private ListenerInstance instance = ListenerInstance.DEFAULT;
    
    /**
     * 监听消息的类型解析器
     */
    private MsgTypeIndexs indexs = null;
    
    /**
     * 分析时候，获取的索引监听对象
     */
    private List<Class<? extends Listener>> indexsClasses = null;
    
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
     * 创建构建器的实体
     * @param listenerCreater
     */
    public void setListenerInstance(ListenerInstance instance) {
        this.instance = instance;
    }
    
    /**
     * 接受消息对象, 执行内容
     * 
     * @param bean
     * @return
     */
    @SuppressWarnings("deprecation")
    public Object acceptmsg(IMessage msg) {
        // 执行一级匹配
        if (indexs != null) {
            Listener ltmsg = indexs.searchFirstV(msg.getMsgType(), msg.getEvent(), msg.getEventKey());
            if (ltmsg != null) {
                return ltmsg.accept(msg, owner);
            }
        }
        // 执行二级匹配
        Listener[] listeners = get(msg.getClass());
        if (listeners == null) {
            return null;
        }
        // 执行监听中的内容
        for (Listener listener : listeners) {
            Object obj = listener.accept(msg, owner);
            if (obj != null) {
                return obj;
            }
        }
        return null;
    }
    
    /**
     * 接受对象, 执行内容
     * 
     * 170925 目前舍弃不再使用
     * 
     * 请使用acceptmsg
     * 
     * @param bean
     * @return
     */
    @Deprecated
    protected Object accept(Object bean) {
        
        Listener[] listeners = get(bean.getClass());
        if (listeners == null) {
            return null;
        }
        // 执行监听中的内容
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
    protected Object accept2(Object bean) {
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
    private Set<Class> getKey(Class<? extends Listener> clazz) {
        ListenerRest rest = clazz.getAnnotation(ListenerRest.class);
        if (rest == null) {
            // 泛型监听
        } else if (ownerType != null && rest.value() == ownerType) {
            // TODO 监听类型对比
            // @Named标记的内容必须是监听所有这的名字
        } else {
            return null; // 鉴别条件验证没有通过
        }
        Class genericType = getGenericKey(clazz);
        if (genericType == null) {
            // 查询监听的内容类型
            return null;
        }
        // 类型集合
        Set types = new LinkedHashSet<>();
        // 查询包含关系
        ListenerInclude include = clazz.getAnnotation(ListenerInclude.class);
        if (include != null && include.value().length > 0) {
            for (Class type : include.value()) {
                if (genericType.isAssignableFrom(type)) {
                    // 监听的类型可以使用
                    types.add(type);
                } else {
                    // 指定的类型不可用
                    System.err.println(String.format("listener type '%s' is not assignable form '%s' in [%s], ignore.", 
                            genericType.getCanonicalName(), type.getCanonicalName(), clazz.getCanonicalName()));
                }
            }
        }
        // if (types.isEmpty() && (genericType.getModifiers() & (Modifier.ABSTRACT | Modifier.INTERFACE)) != 0) {
        if (!types.isEmpty()){
            return Sets.newLinkedHashSet(types);
        }
        ListenerMsgType msgType = clazz.getAnnotation(ListenerMsgType.class);
        if (msgType == null) {
            return Sets.newHashSet(genericType);
        }
        if (indexsClasses == null) {
            indexsClasses = new ArrayList<>();
        }
        indexsClasses.add(clazz);
        return null;
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
            classes = getKey((Class<Listener>)listener.getClass());
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
                    Arrays.sort(listeners, (l, r) -> l.priority().compareTo(r.priority()));
                }
                return olistener;
            }
        }
        // 加入新数据
        listeners = Arrays.copyOf(listeners, listeners.length + 1);
        listeners[listeners.length - 1] = listener;
        Arrays.sort(listeners, (l, r) -> l.priority().compareTo(r.priority()));
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
            Listener listener = instance.newInstance(listenerClass);
            addListeners(listener, classes);
            return true;
        } catch (RuntimeException e) {
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
    
    /**
     * 构建检索索引
     */
    public void buildMsgTypeIndexs() {
        if (indexsClasses == null || indexsClasses.isEmpty()) {
            return;
        }
        List<Listener> list = new ArrayList<>();
        for (Class<? extends Listener> clazz : indexsClasses) {
            Listener listener = instance.newInstance(clazz);
            if (listener != null) {
                list.add(listener);
            }
        }
        if (list.isEmpty()) {
            return;
        }
        indexs = new MsgTypeIndexs(list);
    }

}
