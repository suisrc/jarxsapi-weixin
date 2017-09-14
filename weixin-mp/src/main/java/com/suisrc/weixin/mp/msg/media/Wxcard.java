package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信卡券信息
 * @author Y13
 *
 */
@JsonInclude(Include.NON_NULL)
public class Wxcard {

    @JacksonXmlCData
    @JacksonXmlProperty(localName = "CardId")
    @JsonProperty("card_id")
    private String cardId;

    /**
     * 获取
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设定
     * @param cardId the cardId to set
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}
