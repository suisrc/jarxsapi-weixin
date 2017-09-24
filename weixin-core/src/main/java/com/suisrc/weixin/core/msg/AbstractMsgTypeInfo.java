package com.suisrc.weixin.core.msg;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.suisrc.weixin.core.check.TypeAssert;

/**
 * 事件中类型的描述信息
 * 
 * 用于存储描述了响应时间的描述信息 @MpEvent, @MpEvenKye @MpMsgType
 * 
 * @author Y13
 *
 */
public abstract class AbstractMsgTypeInfo<T extends AbstractMsgTypeInfo<T, V>, V> {
    
    /**
     * 消息类型, 该字段是必须字段
     */
    protected String msgType;
    
    /**
     * 事件消息类型
     */
    protected String event = null;
    
    /**
     * 事件消息的KEY
     */
    protected String eventKey = null;
    
    /**
     * 消息类型匹配器
     */
    protected TypeAssert msgTypeAssert = null;
    
    /**
     * 事件类型匹配器
     */
    protected TypeAssert eventAssert = null;
    
    /**
     * 事件KEY匹配器
     */
    protected TypeAssert eventKeyAssert = null;
    
    /**
     * 匹配顺序
     */
    protected String priority;
    
    /**
     * 绑定的类型
     */
    protected V target;
    
    //-------------------------------------------------------以上是注解属性，以下是判定属性
    
    /**
     * 匹配map
     */
    private ArrayList<SimpleEntry<String, TypeAssert>> asserts;
    
    /**
     * 前导对象
     */
    private T left = null;
    
    /**
     * 后导对象
     */
    private T right = null;
    
    /**
     * 有效前导对象
     */
    private T prefix = null;
    /**
     * 前导集合
     */
    private List<T> prefixs = null;
    
    /**
     * 构造方法
     */
    public AbstractMsgTypeInfo(V target) {
        initialize(target);
        initAssertList();
    }

    /**
     * 构造匹配器
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initAssertList() {
        asserts = new ArrayList<>(3);
        asserts.add(new SimpleEntry(msgType, msgTypeAssert));
        if (event == null) {
            return;
        }
        asserts.add(new SimpleEntry(event, eventAssert));
        if (eventKey == null) {
            return;
        }
        asserts.add(new SimpleEntry(eventKey, eventKeyAssert));
    }

    /**
     * 初始化
     * @param msgType
     * @param targetClass
     */
    protected abstract void initialize(V target);

    /**
     * 排序，作用优先级 @MpEvenKye > @MpEvent > @MpMsgType
     * 如果优先级属性为空，取下一级为优先级字段。
     * @return
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 
     * @param msgtype
     * @param event
     * @param eventkey
     * @return
     */
    public boolean match(String msgtype, String event, String eventkey) {
        return match2(msgtype, event, eventkey);
    }
    
    /**
     * 执行匹配
     * @param msgtype
     * @param event
     * @param eventkey
     * @return
     */
    public boolean match2(String msgtype, String event, String eventkey) {
        for (Entry<String, TypeAssert> e : asserts) {
            if (e.getKey() == null) {
                // 匹配结束，结果复合要求
                return true;
            }
            if (e.getValue() == null) {
                if (!e.getKey().equals(msgtype)) {
                    // 匹配结束，结果不复合要求
                    return false;
                }
            } else {
                if (!e.getValue().apply(e.getKey(), msgtype)) {
                    // 匹配结束，结果不复合要求
                    return false;
                }
            }
        }
        // 匹配结束，结果复合要求
        return true;
    }

    /**
     * 绑定的消息类型
     * @return
     */
    public V getTarget() {
        return target;
    }
    
    /**
     * 获取断言器
     * @return
     */
    public ArrayList<SimpleEntry<String, TypeAssert>> getAsserts() {
        return asserts;
    }

    /**
     * 获取前导对象
     * @return the left
     */
    public T getLeft() {
        return left;
    }

    /**
     * 设定前导对象
     * @param left the left to set
     */
    public void setLeft(T left) {
        this.left = left;
    }

    /**
     * 获取后导对象
     * @return the right
     */
    public T getRight() {
        return right;
    }

    /**
     * 设定后导对象
     * @param right the right to set
     */
    public void setRight(T right) {
        this.right = right;
    }

    /**
     * 获取有效前导对象
     * @return the prefix
     */
    public T getPrefix() {
        return prefix;
    }
    
    /**
     * 获取前导集合
     * @return
     */
    public List<T> getPrefixs() {
        return prefixs;
    }

    /**
     * 设定有效前导对象
     * @param prefix the prefix to set
     */
    public void setPrefix(T prefix) {
        this.prefix = prefix;
        ArrayList<T> infos = new ArrayList<>();
        T current = prefix;
        while (current != null) {
            if (compareTo(current) >= 0) {
                infos.add(current);
            }
            current = current.getPrefix();
        }
        if (!infos.isEmpty()) {
            Collections.reverse(infos);
            infos.trimToSize();
            this.prefixs = Collections.unmodifiableList(infos);
        }
    }
    
    /**
     * 对比内容，比较优先级
     * 
     * -1:内容不匹配
     * @param info
     * @return
     */
    public int compareTo(T other) {
        int size = getAsserts().size();
        if (size > other.getAsserts().size()) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            Entry<String, TypeAssert> e1 = this.getAsserts().get(i);
            Entry<String, TypeAssert> e2 = other.getAsserts().get(i);
            if (e1.getValue() == null && e2.getValue() == null && !e1.getKey().equals(e2.getKey())) {
                return -1;
            }
        }
        return 0;
    }

    /**
     * 特性内容，体现为TypeAssert非接口类型
     * @return
     */
    public boolean isIdentityTypeAssert() {
        return msgTypeAssert != null || eventAssert != null || eventKeyAssert != null;
    }
    
    /**
     * 特性内容，体现为TypeAssert非接口类型, 这个时候，返回值为null
     * 
     * 否则返回值非空
     * @return
     */
    public String getIdentity() {
        if (msgTypeAssert != null || eventAssert != null || eventKeyAssert != null) {
            return null;
        }
        String identity = msgType;
        if (event != null) {
            identity += "|" + event;
        }
        if (eventKey != null) {
            identity += "|" + eventKey;
        }
        return identity;
    }

    /**
     * 查询前导优先类型
     * @param msgtype
     * @param event
     * @param eventkey
     * @return
     */
    public T getPrefixInfo(String msgtype, String event, String eventkey) {
        if (prefixs == null) {
            return null;
        }
        for (T info : prefixs) {
            if (info.match(msgtype, event, eventkey)) {
                return info;
            }
        }
        return null;
    }

}
