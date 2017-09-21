package com.suisrc.weixin.mp.msg;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.suisrc.jaxrsapi.core.util.JaxrsapiUtils;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.annotation.MpMsgType;

/**
 * 用户构建消息类型的索引，主要处理Event, EventKey, MsgType中的内容
 * 
 * @author Y13
 *
 */
public class MsgTypeIndexs {
    
    /**
     * 消息类型链， 所有的消息内容, 初始化后，该内容不可更改
     * 
     * 使用buildIndexs进行初始化
     */
    private final List<MsgTypeInfo> indexs;

    /**
     * 索引的构造方法
     * @param packages
     */
    public MsgTypeIndexs(String... packages) {
        if (packages.length == 0) {
            throw new RuntimeException("Unable to build message type index object:" + MsgTypeIndexs.class.getName());
        }
        ArrayList<MsgTypeInfo> infos = new ArrayList<>();
        // 查询指定包内所有继承接口
        Set<Class<? extends IMessage>> classes = JaxrsapiUtils.getSubclasses(IMessage.class, packages);
        for (Class<? extends IMessage> clazz : classes) {
            if (Modifier.isAbstract(clazz.getModifiers())) {
                continue; // 抽象类跳过
            }
            MpMsgType msgtype = clazz.getAnnotation(MpMsgType.class);
            if (msgtype == null || !msgtype.effect()) {
                continue; // 不存在解析类型或者失效，跳过
            }
            infos.add(new MsgTypeInfo(msgtype, clazz));
        }
        // 执行排序
        infos.sort((l, r) -> l.getPriority().compareTo(r.getPriority()));
        // indexs中的内容不可修改
        indexs = Collections.unmodifiableList(infos);
    }

    /**
     * 搜索第一匹配的类型
     * @param node
     * @return
     */
    public Class<? extends IMessage> searchFirstClass(String msgtype, String event, String eventkey) {
        for (MsgTypeInfo info : indexs) {
            if (info.match(msgtype, event, eventkey)) {
                return info.getTargetClass();
            }
        }
        return null;
    }

}
