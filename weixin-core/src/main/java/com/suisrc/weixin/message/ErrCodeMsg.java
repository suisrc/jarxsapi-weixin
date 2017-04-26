package com.suisrc.weixin.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * 错误编码的多国语言
 * 
 * 暂时只支持中文
 * @author Y13
 *
 */
public class ErrCodeMsg {
	
	/**
	 * 中文库
	 */
	private static final HashMap<String, String> errcodeMap;
	static {
		errcodeMap = new HashMap<>();
		Properties properties = new Properties();
		try {
			properties.load(ErrCodeMsg.class.getResourceAsStream("errcode_zh.properties"));
			properties.forEach((key,value) -> errcodeMap.put(key.toString(), value.toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回错误编码的内容
	 * @param code
	 * @param def
	 * @return
	 */
	public static String getErrCodeMsg(String code, String def) {
		if( code == null ) { code = "0"; }
		String msg = errcodeMap.get(code);
		return msg != null ? msg : def;
	}

	/**
	 * 返回错误编码的内容
	 * @param code
	 * @param def
	 * @return
	 */
	public static String getErrCodeMsg(String code) {
		return getErrCodeMsg(code, "未知");
	}
}
