package com.suisrc.weixin.mp.msg;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.qq.weixin.mp.MpWxConsts;
import com.suisrc.weixin.core.msg.IMessage;
import com.suisrc.weixin.mp.msg.event.UnknowEvent;
import com.suisrc.weixin.mp.msg.msg.UnknowMessage;

/**
 * 默认的微信消息解析器
 * 
 * 可以通过yaml文件中的配置替换该默认配置文件
 * @author Y13
 *
 */
@ApplicationScoped
public class DefaultWxMsgTypeParser implements WxMsgTypeParser {

    /**
     * 公众号消息类型索引
     */
    private MsgTypeIndexs indexs;
    
    /**
     * 执行构造初始化操作
     */
    @PostConstruct
    protected void initialized() {
        String[] pkgs = {UnknowMessage.class.getPackage().getName(), UnknowEvent.class.getPackage().getName()}; // 系统默认
        // 读取系统配置
        String content = System.getProperty(MpWxConsts.KEY_WEIXIN_MSG_TYPE_PACKAGES);
        if (content != null && !(content = content.trim()).isEmpty()) {
            // 用户的配置会替换掉所有已有的配置
            pkgs = content.split(" *, *");
        }
        indexs = new MsgTypeIndexs(pkgs);
    }
    
    /**
     * 执行微信内容解析内容
     */
    @Override
    public Class<? extends IMessage> parser(String msgtype, String event, String eventkey) {
        return indexs.searchFirstV(msgtype, event, eventkey);
    }

}
