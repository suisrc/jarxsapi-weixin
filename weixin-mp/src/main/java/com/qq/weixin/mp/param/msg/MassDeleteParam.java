package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 删除群发【订阅号与服务号认证后均可用】
 * {
 *    "msg_id":30124,
 *    "article_idx":2
 * }
 * 
 * 参数.      是否必须.     说明
 * msg_id      是.        发送出去的消息ID
 * article_idx 否.        要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
 * 
 * @author Y13
 *
 */
public class MassDeleteParam {

    /**
     * 是.        发送出去的消息ID
     */
    @JsonProperty("msg_id")
    private Long msgId;
    
    /**
     * 否.        要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     */
    @JsonProperty("article_idx")
    private Integer articleIdx;

    /**
     * 获取是. 发送出去的消息ID
     * @return the msgId
     */
    public Long getMsgId() {
        return msgId;
    }

    /**
     * 设定是. 发送出去的消息ID
     * @param msgId the msgId to set
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    /**
     * 获取否. 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return the articleIdx
     */
    public Integer getArticleIdx() {
        return articleIdx;
    }

    /**
     * 设定否. 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @param articleIdx the articleIdx to set
     */
    public void setArticleIdx(Integer articleIdx) {
        this.articleIdx = articleIdx;
    }
    
}
