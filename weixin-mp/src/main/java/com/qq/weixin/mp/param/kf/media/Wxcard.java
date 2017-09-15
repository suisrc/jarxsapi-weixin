package com.qq.weixin.mp.param.kf.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信卡券信息
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class Wxcard {

    /**
     * 卡券ID
     */
    @JsonProperty("card_id")
    private String cardId;

    /**
     * 获取卡券ID
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设定卡券ID
     * @param cardId the cardId to set
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
