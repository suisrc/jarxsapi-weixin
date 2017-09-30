package com.qq.weixin.mp.param.template;

/**
 * 小程序信息
 * appid       是.       所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
 * pagepath    是.       所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
 * 
 * @author Y13
 *
 */
public class MiniprogramInfo {
    
    /**
     * 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
     */
    private String appid;
    
    /**
     * 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
     */
    private String pagepath;

    /**
     * 获取所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
     * @return the appid
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设定所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
     * @param appid the appid to set
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 获取所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
     * @return the pagepath
     */
    public String getPagepath() {
        return pagepath;
    }

    /**
     * 设定所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
     * @param pagepath the pagepath to set
     */
    public void setPagepath(String pagepath) {
        this.pagepath = pagepath;
    }
    
}
