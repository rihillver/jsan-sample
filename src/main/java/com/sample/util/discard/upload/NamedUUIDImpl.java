package com.sample.util.discard.upload;

import java.util.UUID;

/**
 * UUID 随机数命名规则。
 *
 */

public class NamedUUIDImpl implements Named {

	@Override
	public String getName(String fieldName, String fileName, int number) {
		
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
