package com.test.lib.log4j;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.helpers.ISO8601DateFormat;

import junit.framework.TestCase;

public class ISO8601DateFormatTest extends TestCase {

	public void testFoo() throws ParseException {

		String str = "1996-01-01T00:00:00+08:00";

		DateFormat dateFormat = ISO8601DateFormat.getDateInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.format(dateFormat.parse(str)));
	}

}
