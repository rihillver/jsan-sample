package com.jsan.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 路径工具类。
 *
 */

public class PathUtils {

	private static final URL url = Object.class.getResource("/");

	/**
	 * 返回 Web 根目录路径。
	 * 
	 * @return
	 */
	public static String getWebRootPath() {

		try {
			return getWebRootFile().getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回 Web 根目录 File 。
	 * 
	 * @return
	 */
	public static File getWebRootFile() {

		return getWebInfFile().getParentFile();
	}

	/**
	 * 返回 WEB-INF 目录路径。
	 * 
	 * @return
	 */
	public static String getWebInfPath() {

		try {
			return getWebInfFile().getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回 WEB-INF 目录 File 。
	 * 
	 * @return
	 */
	public static File getWebInfFile() {

		File file = new File(url.getPath());
		return file.getParentFile();
	}

	/**
	 * 返回 classes 根目录路径。
	 * 
	 * @return
	 */
	public static String getClassesRootPath() {
	
		try {
			return getClassesRootFile().getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回 classes 根目录 File 。
	 * 
	 * @return
	 */
	public static File getClassesRootFile() {
	
		return new File(url.getPath());
	}

	/**
	 * 返回指定类所在目录的路径。
	 * 
	 * @param clazz
	 * @return
	 */
	public static String getClassDirPath(Class<?> clazz) {

		try {
			return getClassDirFile(clazz).getCanonicalPath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回指定类所在目录的 File 。
	 * 
	 * @param clazz
	 * @return
	 */
	public static File getClassDirFile(Class<?> clazz) {

		URL url = clazz.getResource("");
		return new File(url.getPath());
	}

}
