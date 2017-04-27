package com.suisrc.weixin.mp;

import java.io.FileInputStream;
import java.io.IOException;

import com.suisrc.jaxrsapi.core.util.Utils;

/**
 * 创建Bean
 * @author Y13
 *
 */
public class CreateBean {
	
	private static String module = "\n" +
"	/**\n" +
"	 * 字段名:%s\n" +
"	 * 必填:%s\n" +
"	 * 示例值:%s\n" +
"	 * 类型:%s\n" +
"	 * 说明:%s\n" +
"	 */\n" +
"	@JacksonXmlCData\n" +
"	@JacksonXmlProperty(localName=\"%s\")\n" +
"	private String %s;\n";

	public static void main(String[] args) throws IOException {
		String content = Utils.getContent(new FileInputStream("D:/content.txt"));
		String[] css = content.split("[\\r\\n\\t]+");
		StringBuilder sbir = new StringBuilder();
		for(int index = 0; index + 5 < css.length; index += 6) {
			String fn = css[1 + index];
			String[] fns = fn.split("_");
			StringBuilder sbr = new StringBuilder(fns[0]);
			for( int i = 1; i< fns.length; i++ ) {
				sbr.append(fns[i].substring(0, 1).toUpperCase() + fns[i].substring(1));
			}
			String text = String.format(module, css[0 + index], css[2 + index], css[3 + index], css[4 + index], css[5 + index], fn, sbr.toString());
			sbir.append(text);
		}
		System.out.println(sbir.toString());
	}
}
