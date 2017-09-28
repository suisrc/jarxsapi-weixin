package com.suisrc.weixin.mp.msg.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.suisrc.weixin.mp.annotation.MpEvent;
import com.suisrc.weixin.mp.msg.base.WxEventMessage;
import com.suisrc.weixin.mp.msg.media.CopyrightCheckResult;

/**
 * 事件推送群发结果
 * 
 * 由于群发任务提交后，群发任务可能在一定时间后才完成，因此，群发接口调用时，仅会给出群发任务是否提交成功的提示，
 * 若群发任务提交成功，则在群发任务结束时，会向开发者在公众平台填写的开发者URL（callback URL）推送事件。
 * 
 * 需要注意，由于群发任务彻底完成需要较长时间，将会在群发任务即将完成的时候，就推送群发结果，此时的推送人数数据将会与实际情形存在一定误差
 * 
 * 推送的XML结构如下（发送成功时），已增加原创校验结果：
 * <xml>
 *   <ToUserName><![CDATA[gh_4d00ed8d6399]]></ToUserName>
 *   <FromUserName><![CDATA[oV5CrjpxgaGXNHIQigzNlgLTnwic]]></FromUserName>
 *   <CreateTime>1481013459</CreateTime>
 *   <MsgType><![CDATA[event]]></MsgType>
 *   <Event><![CDATA[MASSSENDJOBFINISH]]></Event>
 *   <MsgID>1000001625</MsgID>
 *   <Status><![CDATA[err(30003)]]></Status>
 *   <TotalCount>0</TotalCount>
 *   <FilterCount>0</FilterCount>
 *   <SentCount>0</SentCount>
 *   <ErrorCount>0</ErrorCount>
 *   <CopyrightCheckResult>
 *     <Count>2</Count>
 *     <ResultList>
 *       <item>
 *         <ArticleIdx>1</ArticleIdx>
 *         <UserDeclareState>0</UserDeclareState>
 *         <AuditState>2</AuditState>
 *         <OriginalArticleUrl><![CDATA[Url_1]]></OriginalArticleUrl>
 *         <OriginalArticleType>1</OriginalArticleType>
 *         <CanReprint>1</CanReprint>
 *         <NeedReplaceContent>1</NeedReplaceContent>
 *         <NeedShowReprintSource>1</NeedShowReprintSource>
 *       </item>
 *       <item>
 *         <ArticleIdx>2</ArticleIdx>
 *         <UserDeclareState>0</UserDeclareState>
 *         <AuditState>2</AuditState>
 *         <OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl>
 *         <OriginalArticleType>1</OriginalArticleType>
 *         <CanReprint>1</CanReprint>
 *         <NeedReplaceContent>1</NeedReplaceContent>
 *         <NeedShowReprintSource>1</NeedShowReprintSource>
 *       </item>
 *     </ResultList>
 *     <CheckState>2</CheckState>
 *   </CopyrightCheckResult>
 * </xml>
 * 
 * 参数.            说明
 * ToUserName      公众号的微信号
 * FromUserName    公众号群发助手的微信号，为mphelper
 * CreateTime      创建时间的时间戳
 * MsgType         消息类型，此处为event
 * Event           事件信息，此处为MASSSENDJOBFINISH
 * MsgID           群发的消息ID
 * Status          群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。
 *                 err(num)是审核失败的具体原因，可能的情况如下：
 *                  err(10001), //涉嫌广告 
 *                  err(20001), //涉嫌政治 
 *                  err(20004), //涉嫌社会 
 *                  err(20002), //涉嫌色情 
 *                  err(20006), //涉嫌违法犯罪 
 *                  err(20008), //涉嫌欺诈 
 *                  err(20013), //涉嫌版权 
 *                  err(22000), //涉嫌互推(互相宣传) 
 *                  err(21000), //涉嫌其他
 *                  err(30001) // 原创校验出现系统错误且用户选择了被判为转载就不群发
 *                  err(30002) // 原创校验被判定为不能群发
 *                  err(30003) // 原创校验被判定为转载文且用户选择了被判为转载就不群发
 * TotalCount      tag_id下粉丝数；或者openid_list中的粉丝数
 * FilterCount     过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
 * SentCount       发送成功的粉丝数
 * ErrorCount      发送失败的粉丝数
 * ResultList      各个单图文校验结果
 * 
 * ArticleIdx      群发文章的序号，从1开始
 * UserDeclareState 用户声明文章的状态
 * AuditState      系统校验的状态
 * OriginalArticleUrl 相似原创文的url
 * OriginalArticleType 相似原创文的类型
 * CanReprint      是否能转载
 * NeedReplaceContent 是否需要替换成原创文内容
 * NeedShowReprintSource 是否需要注明转载来源
 * 
 * CheckState      整体校验结果. 1-未被判为转载，可以群发，2-被判为转载，可以群发，3-被判为转载，不能群发
 * 
 * 
 * @author Y13
 *
 */
@MpEvent("MASSSENDJOBFINISH")
@JacksonXmlRootElement(localName="xml")
public class MasssendjobfinishEvent extends WxEventMessage {

    /**
     * 群发的消息ID
     */
    @JacksonXmlProperty(localName = "MsgID")
    @JsonProperty("MsgID")
    private Long msgId;
    
    /**
     * 群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Status")
    @JsonProperty("Status")
    private String status;
    
    /**
     * tag_id下粉丝数；或者openid_list中的粉丝数
     */
    @JacksonXmlProperty(localName = "TotalCount")
    @JsonProperty("TotalCount")
    private Integer totalCount;

    /**
     * 过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
     */
    @JacksonXmlProperty(localName = "FilterCount")
    @JsonProperty("FilterCount")
    private Integer filterCount;

    /**
     * 发送成功的粉丝数
     */
    @JacksonXmlProperty(localName = "SentCount")
    @JsonProperty("SentCount")
    private Integer sentCount;
    
    /**
     * 发送失败的粉丝数
     */
    @JacksonXmlProperty(localName = "ErrorCount")
    @JsonProperty("ErrorCount")
    private Integer errorCount;
    

    /**
     * 图文校验结果
     */
    @JacksonXmlProperty(localName = "CopyrightCheckResult")
    @JsonProperty("CopyrightCheckResult")
    private CopyrightCheckResult copyrightCheckResult;


    /**
     * 获取群发的消息ID
     * @return the msgId
     */
    public Long getMsgId() {
        return msgId;
    }


    /**
     * 设定群发的消息ID
     * @param msgId the msgId to set
     */
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }


    /**
     * 获取群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。
     * @return the status
     */
    public String getStatus() {
        return status;
    }


    /**
     * 设定群发的结构，为“send success”或“send fail”或“err(num)”。但send success时，也有可能因用户拒收公众号的消息、系统错误等原因造成少量用户接收失败。
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * 获取tag_id下粉丝数；或者openid_list中的粉丝数
     * @return the totalCount
     */
    public Integer getTotalCount() {
        return totalCount;
    }


    /**
     * 设定tag_id下粉丝数；或者openid_list中的粉丝数
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }


    /**
     * 获取过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
     * @return the filterCount
     */
    public Integer getFilterCount() {
        return filterCount;
    }


    /**
     * 设定过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数，原则上，FilterCount = SentCount + ErrorCount
     * @param filterCount the filterCount to set
     */
    public void setFilterCount(Integer filterCount) {
        this.filterCount = filterCount;
    }


    /**
     * 获取发送成功的粉丝数
     * @return the sentCount
     */
    public Integer getSentCount() {
        return sentCount;
    }


    /**
     * 设定发送成功的粉丝数
     * @param sentCount the sentCount to set
     */
    public void setSentCount(Integer sentCount) {
        this.sentCount = sentCount;
    }


    /**
     * 获取发送失败的粉丝数
     * @return the errorCount
     */
    public Integer getErrorCount() {
        return errorCount;
    }


    /**
     * 设定发送失败的粉丝数
     * @param errorCount the errorCount to set
     */
    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }


    /**
     * 获取图文校验结果
     * @return the copyrightCheckResult
     */
    public CopyrightCheckResult getCopyrightCheckResult() {
        return copyrightCheckResult;
    }


    /**
     * 设定图文校验结果
     * @param copyrightCheckResult the copyrightCheckResult to set
     */
    public void setCopyrightCheckResult(CopyrightCheckResult copyrightCheckResult) {
        this.copyrightCheckResult = copyrightCheckResult;
    }
    
//    public static void main(String[] args) {
//        MasssendjobfinishEvent event = new MasssendjobfinishEvent();
//        CopyrightCheckResult ccr = new CopyrightCheckResult();
//        List<CheckResult> crs = new ArrayList<>();
//        CheckResult cr = new CheckResult();
//        cr.setArticleIdx(123);
//        crs.add(cr);
//        ccr.setResults(crs);
//        event.setCopyrightCheckResult(ccr);
//
//        String json = com.suisrc.weixin.core.WxMsgCrFactory.bean2Json(event);
//        String xml = com.suisrc.weixin.core.WxMsgCrFactory.bean2Xml(event);
//        System.err.println(json);
//        System.out.println(xml);
//    }
    
//    public static void main(String[] args) {
//        String xml="<xml><ToUserName><![CDATA[gh_4d00ed8d6399]]></ToUserName><FromUserName><![CDATA[oV5CrjpxgaGXNHIQigzNlgLTnwic]]>"
//                + "</FromUserName><CreateTime>1481013459</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[MASSSENDJOBFINISH]]>"
//                + "</Event><MsgID>1000001625</MsgID><Status><![CDATA[err(30003)]]></Status><TotalCount>0</TotalCount><FilterCount>0</FilterCount>"
//                + "<SentCount>0</SentCount><ErrorCount>0</ErrorCount><CopyrightCheckResult><Count>2</Count><ResultList><item><ArticleIdx>1</ArticleIdx>"
//                + "<UserDeclareState>0</UserDeclareState><AuditState>2</AuditState><OriginalArticleUrl><![CDATA[Url_1]]></OriginalArticleUrl>"
//                + "<OriginalArticleType>1</OriginalArticleType><CanReprint>1</CanReprint><NeedReplaceContent>1</NeedReplaceContent><NeedShowReprintSource>1"
//                + "</NeedShowReprintSource></item><item><ArticleIdx>2</ArticleIdx><UserDeclareState>0</UserDeclareState><AuditState>2</AuditState>"
//                + "<OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl><OriginalArticleType>1</OriginalArticleType><CanReprint>1</CanReprint>"
//                + "<NeedReplaceContent>1</NeedReplaceContent><NeedShowReprintSource>1</NeedShowReprintSource></item></ResultList><CheckState>2</CheckState>"
//                + "</CopyrightCheckResult></xml>";
//        
//        MasssendjobfinishEvent event = com.suisrc.weixin.core.WxMsgCrFactory.xml2Bean(xml, MasssendjobfinishEvent.class);
//        System.out.println(event);
//    }
}
