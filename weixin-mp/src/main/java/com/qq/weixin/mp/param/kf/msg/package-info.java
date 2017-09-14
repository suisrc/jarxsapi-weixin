/**
 * 当用户和公众号产生特定动作的交互时（具体动作列表请见下方说明），微信将会把消息数据推送给开发者，
 * 开发者可以在一段时间内（目前修改为48小时）调用客服接口，通过POST一个JSON数据包来发送消息给普通用户。
 * 此接口主要用于客服等有人工消息处理环节的功能，方便开发者为用户提供更加优质的服务。
 * 
 * 
 * @see https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140547
 * 
 * @author Y13
 */
package com.qq.weixin.mp.param.kf.msg;