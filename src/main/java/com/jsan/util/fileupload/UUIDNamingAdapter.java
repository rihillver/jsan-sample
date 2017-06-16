package com.jsan.util.fileupload;

import java.util.UUID;

public class UUIDNamingAdapter implements NamingAdapter {

	private boolean unsigned;

	public UUIDNamingAdapter() {

	}

	public UUIDNamingAdapter(boolean unsigned) {

		this.unsigned = unsigned;
	}

	@Override
	public String getName(String fieldName, String fileName, int number) {

		String name = UUID.randomUUID().toString();

		if (unsigned) {
			return name.replaceAll("-", "");
		} else {
			return name;
		}
	}

}
