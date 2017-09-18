package com.suisrc.weixin.mp.msg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import com.suisrc.weixin.mp.annotation.MpMsgType;
import com.suisrc.weixin.mp.msg.base.BaseMessage;

/**
 * 用于存储和转换注解
 * Event EventKey MsgType
 * @author Y13
 *
 */
public class MpTypeAnnoInfo {

    /**
     * 默认消息类型处理集合
     * 
     * 这里可以使用顺序结构，依次遍历，但是感觉这样太慢了。所以这里改变方式，
     * 默认方式采用HashMap作为索引进行快速遍历，默认方式为等于
     */
    private Map<String, MpTypeAnnoInfo> defaultMsgTypes = null;
    
    /**
     * 前期消息类型处理集合
     */
    private List<MpTypeAnnoInfo> prefixMsgTypes = null;
    
    /**
     * 后期消息类型处理集合
     */
    private List<MpTypeAnnoInfo> suffixMsgTypes = null;

    /**
     * 目标类型
     * 当该类型为集合类型时候，其中targetClass为空
     */
    private Class<? extends BaseMessage> targetClass;

    /**
     * 注解对象构建
     * @param msgType
     * @param messageClass 
     */
    public MpTypeAnnoInfo(MpMsgType msgType, Class<? extends BaseMessage> messageClass) {
        // TODO Auto-generated constructor stub
    }

    /**
     * 优先级
     * @return
     */
    public String getPriority() {
        // TODO Auto-generated method stub
        return "";
    }

    /**
     * 进行内容匹配
     * @param typeName
     * @param eventMatcher
     * @param eventKeyMatcher
     * @return
     */
    public Class<? extends BaseMessage> get(String typeName, Supplier<Matcher> eventMatcher, Supplier<Matcher> eventKeyMatcher) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 增加一个解析类型
     * @param msgType
     * @param messageClass
     */
    public void add(MpMsgType msgType, Class<? extends BaseMessage> messageClass) {
        // TODO Auto-generated method stub
        
    }

}
