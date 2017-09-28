package com.qq.weixin.mp.result.msg;

import com.suisrc.weixin.core.bean.WxErrCode;

/**
 * 上传图片返回值内容
 * {
 *     "url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"
 * }
 * @author Y13
 *
 */
public class UploadimgResult extends WxErrCode {
    private static final long serialVersionUID = 156015189735611641L;
    
    /**
     * 图片地址
     */
    private String url;

    /**
     * 获取图片地址
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设定图片地址
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
}
