package com.jsan.util.upload;

public interface NamingAdapter {

	/**
	 * 返回文件名（不含扩展名）。
	 * 
	 * @param fieldName
	 * @param fileName
	 * @param number
	 * @return
	 */
	public String getName(String fieldName, String fileName, int number);
}
