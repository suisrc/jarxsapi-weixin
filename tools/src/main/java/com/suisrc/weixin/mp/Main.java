package com.suisrc.weixin.mp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 初始化构建需要加载的扩展对象
 * @author Y13
 *
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("T:/errcode_cn_2.properties");
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		
		FileInputStream fis = new FileInputStream("T:/errcode_cn.properties");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String line1, line2;
		while( ( line1 = br.readLine() ) != null && ( line2 = br.readLine() ) != null ) {
			osw.write(line1 + "=" + line2 + "\n");
		}
		osw.flush();
		osw.close();
		br.close();
	}

}
