package com.jsan.util.fileupload;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeNamingAdapter implements NamingAdapter {

	private SimpleDateFormat sdf = null;

	public DateTimeNamingAdapter() {

		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
	}

	public DateTimeNamingAdapter(String pattern) {

		sdf = new SimpleDateFormat(pattern);
	}

	@Override
	public String getName(String fieldName, String fileName, int number) {

		return sdf.format(new Date());
	}

}
