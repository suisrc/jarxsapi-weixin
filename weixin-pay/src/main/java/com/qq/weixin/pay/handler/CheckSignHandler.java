package com.qq.weixin.pay.handler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.TreeMap;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.qq.weixin.pay.PayWxConsts;
import com.suisrc.jaxrsapi.core.ServiceClient;
import com.suisrc.jaxrsapi.core.function.ReviseHandler;
import com.suisrc.weixin.core.crypto.WxCrypto;

/**
 * 对红包数据内容进行签名
 * @author Y13
 *
 */
public class CheckSignHandler implements ReviseHandler<Object> {
	
	/**
	 * 远程访问客户端
	 */
	private ServiceClient client;
	
	public CheckSignHandler(ServiceClient client) {
		this.client = client;
	}
	
	/**
	 * 获取商铺KEY
	 * @return
	 */
	private String getMchKey() {
		return client.getAdapter(PayWxConsts.MCH_KEY);
	}

	@Override
	public Object accept(Object value) {
		try {
			TreeMap<String, String> datas = new TreeMap<>();
			// 判断是否有sign字段
			Method signMethod = null;
			Class<?> clazz = value.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for( Field field : fields ) {
				JacksonXmlProperty xmlProperty = field.getAnnotation(JacksonXmlProperty.class);
				if( xmlProperty == null ) { continue; }
				String key = xmlProperty.localName();
				if( key == null ) { key = field.getName(); }
				String name = field.getName();
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				if( key.equals("sign") ) {
					Method method = clazz.getMethod("get" + name);
					if( method.invoke(value) != null ) { 
						// 签名字段已经有数据了，就不再对数据进行生成
						return value; 
					} 
					signMethod = clazz.getMethod("set" + name, field.getType());
				} else {
					Method method = clazz.getMethod("get" + name);
					if( method == null && (field.getType() == Boolean.class || field.getType() == boolean.class)) {
						method = clazz.getMethod("is" + name);
					}
					if( method == null ) { continue; }
					
					Object data = method.invoke(value);
					if( data == null ) { continue; }
					datas.put(key, data.toString());
				}
			}
			if( signMethod != null ) {
				String sign = WxCrypto.genMD5(datas, getMchKey());
				signMethod.invoke(value, sign);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
