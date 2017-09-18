package com.qq.weixin.mp.param.kf.xcx;

import com.qq.weixin.mp.param.kf.media.MiniprogrampageInfo;
import com.qq.weixin.mp.param.kf.msg.KfReplyBaseMessage;

/**
 * 发送小程序卡片（要求小程序与公众号已关联）
 * 客户端效果如下图：
 * 
 * 接口调用示例：
 * {
 *     "touser":"OPENID",
 *     "msgtype":"miniprogrampage",
 *     "miniprogrampage":
 *     {
 *         "title":"title",
 *         "appid":"appid",
 *         "pagepath":"pagepath",
 *         "thumb_media_id":"thumb_media_id"
 *     }
 * }
 * 
 * JSON解析
 */
public class KfReplyMiniprogrampageMessage extends KfReplyBaseMessage {

    /**
     * 微信小程序信息
     */
    private MiniprogrampageInfo miniprogrampage;

    public KfReplyMiniprogrampageMessage() {
        // setMsgtype(WxMsgType.miniprogrampage.name());
        setMsgtype("miniprogrampage");
    }

    /**
     * 获取微信小程序信息
     * @return the miniprogrampage
     */
    public MiniprogrampageInfo getMiniprogrampage() {
        return miniprogrampage;
    }

    /**
     * 设定微信小程序信息
     * @param miniprogrampage the miniprogrampage to set
     */
    public void setMiniprogrampage(MiniprogrampageInfo miniprogrampage) {
        this.miniprogrampage = miniprogrampage;
    }
    
    
}
