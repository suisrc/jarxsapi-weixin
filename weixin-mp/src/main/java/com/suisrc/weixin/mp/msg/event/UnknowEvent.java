package com.suisrc.weixin.mp.msg.event;

import com.suisrc.weixin.core.check.TypeRegexAssert;
import com.suisrc.weixin.mp.annotation.MpMsgType;

/**
 * 未知事件类型
 * @author Y13
 *
 */
@MpMsgType(value = ".+", priority = 4096, handler = TypeRegexAssert.class)
public class UnknowEvent {

}
