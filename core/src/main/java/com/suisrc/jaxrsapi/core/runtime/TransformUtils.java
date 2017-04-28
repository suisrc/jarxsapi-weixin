package com.suisrc.jaxrsapi.core.runtime;

/**
 * 类型转换
 * @author Y13
 */
public class TransformUtils {

	/**
	 * 数据转换
	 * @param type   返回的类型
	 * @param value  数值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T transform(Class<T> type, String value) {
		if( value.isEmpty() || value.toLowerCase().equals("null") ) {
			return null;
		}
		if( type == String.class ) {
			return (T) value;
		} else if( type == int.class || type == Integer.class ){
			return  (T) Integer.valueOf(value);
		} else if( type == double.class || type == Double.class ){
			return (T) Double.valueOf(value);
		} else if( type == boolean.class || type == Boolean.class ){
			return (T) Boolean.valueOf(value);
		} else if( type == long.class || type == Long.class ){
			return (T) Long.valueOf(value);
		} else {
			return null;
		}
	}
}
