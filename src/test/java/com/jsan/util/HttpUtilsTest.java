package com.jsan.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import junit.framework.TestCase;

public class HttpUtilsTest extends TestCase {

	public void testFoo() throws UnsupportedEncodingException {

		String url = "http://scm.taixingmart.com/app/query.do?key=" + URLEncoder.encode("可乐", "utf-8");
		System.out.println(HttpUtils.getString(url));

		String imgUrl = "https://www.baidu.com/img/bd_logo1.png";
		System.out.println(HttpUtils.getFile(imgUrl, "d:/baidu.png"));
		
		String httpsUrl = "https://www.baidu.com/";		
		System.out.println(HttpUtils.getString(httpsUrl));

	}
}
