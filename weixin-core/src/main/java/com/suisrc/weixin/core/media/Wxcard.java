package com.suisrc.weixin.core.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 微信卡券信息
 * @author Y13
 *
 */
public class Wxcard {

	@JacksonXmlCData
	@JacksonXmlProperty(localName="CardId")
	private String cardId;

	@JsonProperty("card_id")
	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	
}
