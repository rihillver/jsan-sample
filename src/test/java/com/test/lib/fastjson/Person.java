package com.test.lib.fastjson;

public class Person {

	String country;

	transient int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRace() {

		return "yellow";
	}

	public String getLanguage() {

		// throw new RuntimeException("error...");
		return "zh-cn";
	}

}
