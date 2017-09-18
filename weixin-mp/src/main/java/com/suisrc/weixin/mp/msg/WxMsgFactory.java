package com.suisrc.weixin.mp.msg;

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.weixin.core.WxMsgCrFactory;
import com.suisrc.weixin.mp.msg.base.BaseMessage;
import com.suisrc.weixin.mp.msg.event.UnknowEvent;
import com.suisrc.weixin.mp.msg.msg.UnknowMessage;

/**
 * 消息工厂
 * 
 * @author Y13
 *
 */
public class WxMsgFactory {
    
    /**
     * 默认顺序
     */
    public final static String DEFAULT_PRIORITY = "n";
    
    /**
     * 类型注解索引
     */
    public final static MpMsgTypeIndex annoIndex;
    static {
        annoIndex = new MpMsgTypeIndex();
        String[] pkgs = {UnknowMessage.class.getPackage().getName(), UnknowEvent.class.getPackage().getName()}; // 系统默认
        // 读取系统配置
        String content = System.getProperty(MpWxConsts.KEY_WEIXIN_MSG_TYPE_PACKAGES);
        if (content != null && !(content = content.trim()).isEmpty()) {
            // 用户的配置会替换掉所有已有的配置
            pkgs = content.split(" *, *");
        }
        annoIndex.buildMsgType(pkgs);
    }

    /**
     * 解析消息的内容，转换为Bean
     * 
     * @param content
     */
    public static BaseMessage strToWxMessage(String content, boolean isJson) {
        Class<? extends BaseMessage> msgType = annoIndex.getMsgType(content, isJson);
        if (msgType == null) {
            return null;
        } // 无法解析内容
        return WxMsgCrFactory.str2Bean(content, msgType, isJson);
    }

}
