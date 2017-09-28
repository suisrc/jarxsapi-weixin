package com.suisrc.weixin.mp.msg.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 
 * <ArticleIdx>2</ArticleIdx>
 * <UserDeclareState>0</UserDeclareState>
 * <AuditState>2</AuditState>
 * <OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl>
 * <OriginalArticleType>1</OriginalArticleType>
 * <CanReprint>1</CanReprint>
 * <NeedReplaceContent>1</NeedReplaceContent>
 * <NeedShowReprintSource>1</NeedShowReprintSource>
 * 
 * ArticleIdx      群发文章的序号，从1开始
 * UserDeclareState 用户声明文章的状态
 * AuditState      系统校验的状态
 * OriginalArticleUrl 相似原创文的url
 * OriginalArticleType 相似原创文的类型
 * CanReprint      是否能转载
 * NeedReplaceContent 是否需要替换成原创文内容
 * NeedShowReprintSource 是否需要注明转载来源

 * @author Y13
 *
 */
@JsonRootName("item")
public class CheckResult {

    /**
     * 群发文章的序号，从1开始
     */
    @JacksonXmlProperty(localName = "ArticleIdx")
    @JsonProperty("ArticleIdx")
    private Integer articleIdx;
    
    /**
     * 用户声明文章的状态
     */
    @JacksonXmlProperty(localName = "UserDeclareState")
    @JsonProperty("UserDeclareState")
    private Integer userDeclareState;
    
    /**
     * 系统校验的状态
     */
    @JacksonXmlProperty(localName = "AuditState")
    @JsonProperty("AuditState")
    private Integer auditState;
    
    /**
     * 相似原创文的url
     */
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "OriginalArticleUrl")
    @JsonProperty("OriginalArticleUrl")
    private String originalArticleUrl;
    
    /**
     * 相似原创文的类型
     */
    @JacksonXmlProperty(localName = "OriginalArticleType")
    @JsonProperty("OriginalArticleType")
    private Integer originalArticleType;
    
    /**
     * 是否能转载
     */
    @JacksonXmlProperty(localName = "CanReprint")
    @JsonProperty("CanReprint")
    private Integer canReprint;
    
    /**
     * 是否需要替换成原创文内容
     */
    @JacksonXmlProperty(localName = "NeedReplaceContent")
    @JsonProperty("NeedReplaceContent")
    private Integer needReplaceContent;
    
    /**
     * 是否需要注明转载来源
     */
    @JacksonXmlProperty(localName = "NeedShowReprintSource")
    @JsonProperty("NeedShowReprintSource")
    private Integer needShowReprintSource;

    /**
     * 获取群发文章的序号，从1开始
     * @return the articleIdx
     */
    public Integer getArticleIdx() {
        return articleIdx;
    }

    /**
     * 设定群发文章的序号，从1开始
     * @param articleIdx the articleIdx to set
     */
    public void setArticleIdx(Integer articleIdx) {
        this.articleIdx = articleIdx;
    }

    /**
     * 获取用户声明文章的状态
     * @return the userDeclareState
     */
    public Integer getUserDeclareState() {
        return userDeclareState;
    }

    /**
     * 设定用户声明文章的状态
     * @param userDeclareState the userDeclareState to set
     */
    public void setUserDeclareState(Integer userDeclareState) {
        this.userDeclareState = userDeclareState;
    }

    /**
     * 获取系统校验的状态
     * @return the auditState
     */
    public Integer getAuditState() {
        return auditState;
    }

    /**
     * 设定系统校验的状态
     * @param auditState the auditState to set
     */
    public void setAuditState(Integer auditState) {
        this.auditState = auditState;
    }

    /**
     * 获取相似原创文的url
     * @return the originalArticleUrl
     */
    public String getOriginalArticleUrl() {
        return originalArticleUrl;
    }

    /**
     * 设定相似原创文的url
     * @param originalArticleUrl the originalArticleUrl to set
     */
    public void setOriginalArticleUrl(String originalArticleUrl) {
        this.originalArticleUrl = originalArticleUrl;
    }

    /**
     * 获取相似原创文的类型
     * @return the originalArticleType
     */
    public Integer getOriginalArticleType() {
        return originalArticleType;
    }

    /**
     * 设定相似原创文的类型
     * @param originalArticleType the originalArticleType to set
     */
    public void setOriginalArticleType(Integer originalArticleType) {
        this.originalArticleType = originalArticleType;
    }

    /**
     * 获取是否能转载
     * @return the canReprint
     */
    public Integer getCanReprint() {
        return canReprint;
    }

    /**
     * 设定是否能转载
     * @param canReprint the canReprint to set
     */
    public void setCanReprint(Integer canReprint) {
        this.canReprint = canReprint;
    }

    /**
     * 获取是否需要替换成原创文内容
     * @return the needReplaceContent
     */
    public Integer getNeedReplaceContent() {
        return needReplaceContent;
    }

    /**
     * 设定是否需要替换成原创文内容
     * @param needReplaceContent the needReplaceContent to set
     */
    public void setNeedReplaceContent(Integer needReplaceContent) {
        this.needReplaceContent = needReplaceContent;
    }

    /**
     * 获取是否需要注明转载来源
     * @return the needShowReprintSource
     */
    public Integer getNeedShowReprintSource() {
        return needShowReprintSource;
    }

    /**
     * 设定是否需要注明转载来源
     * @param needShowReprintSource the needShowReprintSource to set
     */
    public void setNeedShowReprintSource(Integer needShowReprintSource) {
        this.needShowReprintSource = needShowReprintSource;
    }
    
}
