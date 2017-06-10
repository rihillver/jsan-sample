package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

public class TempTest extends TestCase{

	public void testFoo() throws IOException{
		
		File file = new File("d:/haha/abc.txt");

		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}

		boolean existFlag = file.exists() ? true : false; // 文件是否已存在
		
		System.out.println(existFlag);
		
		FileOutputStream out = new FileOutputStream(file);
		
		out.write(4);
		
		out.close();
	}
}
