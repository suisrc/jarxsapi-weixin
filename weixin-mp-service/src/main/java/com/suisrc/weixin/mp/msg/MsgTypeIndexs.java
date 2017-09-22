package com.suisrc.weixin.mp.msg;

import com.suisrc.weixin.core.msg.AbstractMsgTypeIndexs;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.annotation.MpMsgType;

/**
 * 用户构建消息类型的索引，主要处理Event, EventKey, MsgType中的内容
 * 
 * @author Y13
 *
 */
public class MsgTypeIndexs extends AbstractMsgTypeIndexs<MsgTypeInfo> {
    
    /**
     * 索引的构造方法
     * @param packages
     */
    public MsgTypeIndexs(String... packages) {
        super(MsgTypeIndexs::newMsgTypeInfo, packages);
    }
    
    /**
     * 根据类型创建消息描述对象
     * @param clazz
     * @return
     */
    private static MsgTypeInfo newMsgTypeInfo(Class<? extends IMessage> clazz) {
        MpMsgType msgtype = clazz.getAnnotation(MpMsgType.class);
        if (msgtype == null || !msgtype.effect()) {
            return null; // 不存在解析类型或者失效，跳过
        }
        return new MsgTypeInfo(clazz);
    }

}
