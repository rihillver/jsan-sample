package com.jsan.util.fileupload;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampNamingAdapter implements NamingAdapter {

	private SimpleDateFormat sdf = null;

	public TimestampNamingAdapter() {

		sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
	}

	public TimestampNamingAdapter(String pattern) {

		sdf = new SimpleDateFormat(pattern);
	}

	@Override
	public String getName(String fieldName, String fileName, int number) {

		return sdf.format(new Date());
	}

}
