package com.qq.weixin.mp.result;

/**
 * 异常获取，最好所有的返回值都集成该对象，这样对于异常的返回也可以正常处理
 * 判断异常只需要返回值，有限判断errcode是否为null即可
 * @author Y13
 *
 */
public class ErrCode {
	
	private String errcode = null;
	
	private String errmsg = null;
	
	public String getErrcode() {
		return errcode;
	}
	
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	
	public String getErrmsg() {
		return errmsg;
	}
	
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
