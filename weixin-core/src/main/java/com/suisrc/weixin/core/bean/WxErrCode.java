package com.suisrc.weixin.core.bean;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * 异常获取，最好所有的返回值都集成该对象，这样对于异常的返回也可以正常处理 判断异常只需要返回值，判断errcode是否为null即可
 * 
 * @author Y13
 *
 */
@JacksonXmlRootElement(localName="xml")
public class WxErrCode implements Serializable {
    private static final long serialVersionUID = 1237064203482294732L;

    /**
     * 返回码
     */
    private Long errcode = null;

    /**
     * 对返回码的文本描述内容
     */
    private String errmsg = null;

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

}
