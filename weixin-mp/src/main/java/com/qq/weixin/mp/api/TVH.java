package com.qq.weixin.mp.api;

import com.suisrc.jaxrsapi.core.func.ValueHandler;

public class TVH implements ValueHandler<String> {

	@Override
	public String revise(String value) {
		if( value == null ) {
			return null;
		}
		return value + "123";
	}
	
	public String proxy(String url) {
		return null;
	}


}
