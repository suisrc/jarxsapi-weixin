package com.suisrc.weixin.mp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class CopyTools {
	
	public static void main(String[] args) throws Exception {
		File folder = new File("T:/lib");
		if( !folder.exists() ) { 
			folder.mkdirs();
		}
		FileInputStream fis = new FileInputStream("D:/directory.txt");
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader reader = new BufferedReader(isr);
		String lineContent;
		while( (lineContent = reader.readLine()) != null ) {
			lineContent = lineContent.replace('\\', '/');
			FileInputStream file = new FileInputStream(lineContent);
			String filename = lineContent.substring(lineContent.lastIndexOf('/') + 1);
			FileOutputStream output = new FileOutputStream(folder.getAbsolutePath() + "/" + filename);
			byte[] bufs = new byte[1024];
			int len = 0;
			while( (len = file.read(bufs)) > 0 ) {
				output.write(bufs, 0, len);
			}
			output.close();
			file.close();
			System.out.println(lineContent);
		}
		reader.close();
	}

}
