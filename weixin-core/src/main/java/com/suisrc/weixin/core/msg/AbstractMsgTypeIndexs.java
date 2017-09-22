package com.suisrc.weixin.core.msg;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.listener.MsgTypeIndexs;

/**
 * 用户构建消息类型的索引，主要处理Event, EventKey, MsgType中的内容
 * 
 * @author Y13
 *
 */
public abstract class AbstractMsgTypeIndexs<T extends AbstractMsgTypeInfo<T>> {
    
    /**
     * 消息类型链， 所有的消息内容, 初始化后，该内容不可更改
     * 
     * 使用buildIndexs进行初始化
     */
    private final List<T> indexs;
    
    /**
     * 快速检索MAP
     */
    private Map<String, T> indexsMap;
    
    /**
     * 快速检索LIST
     */
    private List<T> indexList;

    /**
     * 索引的构造方法
     * @param packages
     */
    public AbstractMsgTypeIndexs(Function<Class<? extends IMessage>, T> creater, String... packages) {
        if (packages.length == 0) {
            throw new RuntimeException("Unable to build message type index object:" + MsgTypeIndexs.class.getName());
        }
        ArrayList<T> infos = new ArrayList<>();
        // 查询指定包内所有继承接口
        Set<Class<? extends IMessage>> classes = JaxrsapiUtils.getSubclasses(IMessage.class, packages);
        for (Class<? extends IMessage> clazz : classes) {
            if (Modifier.isAbstract(clazz.getModifiers())) {
                continue; // 抽象类跳过
            }
            T info = creater.apply(clazz);
            if (info != null) {
                infos.add(info);
            }
        }
        // 执行排序
        infos.sort((l, r) -> l.getPriority().compareTo(r.getPriority()));
        // indexs中的内容不可修改
        infos.trimToSize();
        indexs = Collections.unmodifiableList(infos);
        
        // 初始化
        initialize();
    }
    
    /**
     * 初始化
     * 
     * 初始化快速检索器
     */
    protected void initialize() {
        HashMap<String, T> imap = new HashMap<>();
        ArrayList<T> ilist = new ArrayList<>();
        
        T left = null;
        T prefix = null;
        for( T current : indexs ) {
            // 普通链条
            if (left != null) {
                current.setLeft(left);
                left.setRight(current);
            }
            left = current;
            // 有效导内容
            if (prefix != null) {
                current.setPrefix(prefix);
            }
            String identity = current.getIdentity();
            if (identity == null) {
                prefix = current;
                ilist.add(current);
            } else {
                imap.put(identity, current);
            }
        }
        ilist.trimToSize();
        indexList = Collections.unmodifiableList(ilist);
        indexsMap = Collections.unmodifiableMap(imap);
    }

    /**
     * 搜索第一匹配的类型
     * @param node
     * @return
     */
    public Class<? extends IMessage> searchFirstClass(String msgtype, String event, String eventkey) {
        List<T> list = new ArrayList<>();
        List<String> indexKey = getIndexKey(msgtype, event, eventkey);
        for (String key : indexKey) {
            T info = indexsMap.get(key);
            if (info != null) {
                list.add(info);
            }
        }
        if (list.isEmpty()) {
            for (T info : indexList) {
                if (info.match(msgtype, event, eventkey)) {
                    return info.getTargetClass();
                }
            }
        }
        list.sort((l, r) -> l.getPriority().compareTo(r.getPriority()));
        T info = list.get(0);
        T prefix = info.getPrefixInfo(msgtype, event, eventkey);
        // 返回查询的类型
        return (prefix != null ? prefix : info).getTargetClass();
    }
    
    /**
     * 搜索第一匹配的类型
     * @param node
     * @return
     */
    @Deprecated
    public Class<? extends IMessage> searchFirstClass2(String msgtype, String event, String eventkey) {
        for (T info : indexs) {
            if (info.match(msgtype, event, eventkey)) {
                return info.getTargetClass();
            }
        }
        return null;
    }
    
    /**
     * 获取索引key
     */
    public List<String> getIndexKey(String msgtype, String event, String eventKey) {
        List<String> keys = new ArrayList<>();
        keys.add(msgtype);
        if (event == null || event.isEmpty()) {
            return keys;
        }
        keys.add(msgtype + "|" + event);
        if (eventKey == null || eventKey.isEmpty()) {
            return keys;
        }
        keys.add(msgtype + "|" + event + "|" + eventKey);
        
        // 返回检索key集合
        return keys;
    }

}
