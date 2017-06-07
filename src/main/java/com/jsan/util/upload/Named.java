package com.jsan.util.upload;

/**
 * 上传文件命名接口（通过实现该接口自定义上传文件命名）。
 *
 */

public interface Named {
	
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
