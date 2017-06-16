package com.sample.util.discard.upload;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间顺序数命名规则。
 *
 */

public class NamedTimeImpl implements Named {

	protected static SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyyMMddHHmmssS");
	
	@Override
	public String getName(String fieldName, String fileName, int number) {
		
		
		return simpleDateFormat.format(new Date())+number;
	}

}
