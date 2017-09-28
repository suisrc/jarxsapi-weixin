package com.qq.weixin.mp.param.msg;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * media_id         是.         用于群发的消息的media_id
 * title            否.         消息的标题
 * description      否.         消息的描述
 * thumb_media_id   是.         视频缩略图的媒体ID
 * @author Y13
 *
 */
public class MassSendItemWxcard implements MassSendItem {
    
    /**
     * 卡券消息
     */
    @JsonProperty("card_id")
    private String cardId;
    
    /**
     * 卡券扩展
     */
    @JsonProperty("card_ext")
    private String cardExt;

    /**
     * 获取卡券消息
     * @return the cardId
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设定卡券消息
     * @param cardId the cardId to set
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取卡券扩展
     * @return the cardExt
     */
    public String getCardExt() {
        return cardExt;
    }

    /**
     * 设定卡券扩展
     * 
     * {\"code\":\"\",\"openid\":\"\",\"timestamp\":\"1402057159\",\"signature\":\"017bb17407c8e0058a66d72dcc61632b70f511ad\"}
     * @param cardExt the cardExt to set
     */
    public void setCardExt(String cardExt) {
        this.cardExt = cardExt;
    }
    
    /**
     * 设定卡券扩展
     */
    public void assignCardExt(String code, String openid, String timestamp, String signature) {
        this.cardExt = String.format(
                "{\"code\":\"%s\",\"openid\":\"%s\",\"timestamp\":\"%s\",\"signature\":\"%s\"}", 
                code != null ? code : "", openid != null ? openid : "", 
                timestamp != null ? timestamp : "", signature != null ? signature : "");
    }
    
//    public static void main(String[] args) {
//        MassSendItemWxcard card = new MassSendItemWxcard();
//        card.setCardId("123");
//        card.assignCardExt(null, null, "1402057159", "017bb17407c8e0058a66d72dcc61632b70f511ad");
//        String json = WxMsgCrFactory.bean2Json(card);
//        System.out.println(json);
//    }
}
