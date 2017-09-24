package com.suisrc.weixin.mp.msg;

import java.lang.reflect.Modifier;
import java.util.Set;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.msg.AbstractMsgTypeIndexs;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.annotation.MpMsgType;

/**
 * 用户构建消息类型的索引，主要处理Event, EventKey, MsgType中的内容
 * 
 * @author Y13
 *
 */
public class MsgTypeIndexs extends AbstractMsgTypeIndexs<MsgTypeInfo, Class<? extends IMessage>> {
    
    /**
     * 索引的构造方法
     * @param packages
     */
    public MsgTypeIndexs(String... packages) {
        super(MsgTypeIndexs::newMsgTypeInfo, MsgTypeIndexs.getClasses(packages));
    }
    
    /**
     * 根据类型创建消息描述对象
     * @param clazz
     * @return
     */
    private static MsgTypeInfo newMsgTypeInfo(Class<? extends IMessage> clazz) {
        if (Modifier.isAbstract(clazz.getModifiers())) {
            return null; // 抽象类跳过
        }
        MpMsgType msgtype = clazz.getAnnotation(MpMsgType.class);
        if (msgtype == null || !msgtype.effect()) {
            return null; // 不存在解析类型或者失效，跳过
        }
        return new MsgTypeInfo(clazz);
    }
    
    /**
     * 获取所有文件中的监听类型
     * @param packages
     * @return
     */
    private static Set<Class<? extends IMessage>> getClasses(String[] packages) {
        if (packages.length == 0) {
            throw new RuntimeException("Unable to build message type index object:" + MsgTypeIndexs.class.getName());
        }
        // 查询指定包内所有继承接口
        Set<Class<? extends IMessage>> classes = JaxrsapiUtils.getSubclasses(IMessage.class, packages);
        return classes;
    }

}
