package com.test;

import java.io.IOException;
import java.util.UUID;

public class TempTest {

	public static void main(String[] args) throws IOException {

		UUID uuid  =  UUID.randomUUID(); 
		String s = UUID.randomUUID().toString();//用来生成数据库的主键id非常不错。。
		
		System.out.println(s);
		
	}
}
