package com.suisrc.weixin.mp.msg.media;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 
 * <Count>2</Count>
 * <ResultList>
 *   <item>
 *     <ArticleIdx>1</ArticleIdx>
 *     <UserDeclareState>0</UserDeclareState>
 *     <AuditState>2</AuditState>
 *     <OriginalArticleUrl><![CDATA[Url_1]]></OriginalArticleUrl>
 *     <OriginalArticleType>1</OriginalArticleType>
 *     <CanReprint>1</CanReprint>
 *     <NeedReplaceContent>1</NeedReplaceContent>
 *     <NeedShowReprintSource>1</NeedShowReprintSource>
 *   </item>
 *   <item>
 *     <ArticleIdx>2</ArticleIdx>
 *     <UserDeclareState>0</UserDeclareState>
 *     <AuditState>2</AuditState>
 *     <OriginalArticleUrl><![CDATA[Url_2]]></OriginalArticleUrl>
 *     <OriginalArticleType>1</OriginalArticleType>
 *     <CanReprint>1</CanReprint>
 *     <NeedReplaceContent>1</NeedReplaceContent>
 *     <NeedShowReprintSource>1</NeedShowReprintSource>
 *   </item>
 * </ResultList>
 * <CheckState>2</CheckState>
 * 
 * ResultList      各个单图文校验结果
 * CheckState      整体校验结果. 1-未被判为转载，可以群发，2-被判为转载，可以群发，3-被判为转载，不能群发
 * @author Y13
 *
 */
public class CopyrightCheckResult {

    /**
     * 图文校验结果数量
     */
    @JacksonXmlProperty(localName = "Count")
    @JsonProperty("Count")
    private Integer count;
    
    /**
     * 整体校验结果. 1-未被判为转载，可以群发，2-被判为转载，可以群发，3-被判为转载，不能群发
     */
    @JacksonXmlProperty(localName = "CheckState")
    @JsonProperty("CheckState")
    private Integer checkState;
    
    /**
     * 图文校验结果
     */
    @JacksonXmlElementWrapper(localName = "ResultList")
    @JacksonXmlProperty(localName = "item")
    @JsonProperty("ResultList")
    private List<CheckResult> results;

    /**
     * 获取图文校验结果数量
     * @return the count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设定图文校验结果数量
     * @param count the count to set
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取整体校验结果. 1-未被判为转载，可以群发，2-被判为转载，可以群发，3-被判为转载，不能群发
     * @return the checkState
     */
    public Integer getCheckState() {
        return checkState;
    }

    /**
     * 设定整体校验结果. 1-未被判为转载，可以群发，2-被判为转载，可以群发，3-被判为转载，不能群发
     * @param checkState the checkState to set
     */
    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }

    /**
     * 获取图文校验结果
     * @return the results
     */
    public List<CheckResult> getResults() {
        return results;
    }

    /**
     * 设定图文校验结果
     * @param results the results to set
     */
    public void setResults(List<CheckResult> results) {
        this.results = results;
    }
    
}
