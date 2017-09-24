package com.suisrc.weixin.core.listener;

/***
 * 监听接口
 * 
 * 监听器使用的时候，需要通过@Named注解标记监听的对象的类型， 必须是类型的全名（包+类型） 
 * 使用String类型标记而不使用Class,是为了解除项目之间的强引用
 * 
 * @author Y13
 *
 */
public interface Listener<T> {
    
    /**
     * 接受监听对象
     * 
     * @param bean null表示对该条内容没有记性处理
     * @return
     */
    Object accept(T bean);

    /**
     * 接受监听对象
     * 
     * @param bean null表示对该条内容没有记性处理
     * @return
     */
    default Object accept(T bean, Object owner) {
        return accept(bean);
    }

    /**
     * 监听是否有效
     * 
     * @return
     */
    default boolean effect() {
        return true;
    }

    /**
     * 监听接受的顺序，默认排序为N,为以后扩展留下空间
     * 
     * @return
     */
    default String priority() {
        return "N";
    }
    
    /**
     * 获取监听的类型
     * 
     * @return
     */
    @Deprecated
    default Class<T> type() {
        // return T.class;
        return null;
    }

}
