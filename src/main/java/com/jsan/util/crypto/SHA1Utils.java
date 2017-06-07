package com.jsan.util.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 * SHA1 散列算法工具类。
 *
 */

public class SHA1Utils {

	/**
	 * 返回 SHA1 散列码。
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encrypt(byte[] bytes) {

		String str = null;

		if (bytes != null) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
				messageDigest.update(bytes);
				byte[] buff = messageDigest.digest();
				str = parseByteToHexString(buff);
			} catch (Exception e) {
				// logging
				e.printStackTrace();
			}
		}

		return str;
	}

	/**
	 * 返回 SHA1 散列码。
	 * 
	 * @param str
	 * @return
	 */
	public static String encrypt(String str) {

		if (str != null) {
			str = encrypt(str.getBytes());
		}

		return str;
	}

	/**
	 * 返回 SHA1 散列码。
	 * 
	 * @param filePath
	 * @return
	 */
	public static String encryptByFile(String filePath) {

		File file = new File(filePath);
		return encryptByFile(file);
	}

	/**
	 * 返回 SHA1 散列码。
	 * 
	 * @param file
	 * @return
	 */
	public static String encryptByFile(File file) {

		String str = null;

		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] buffer = new byte[1024 * 4];

			MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

			while (inputStream.read(buffer) != -1) {
				messageDigest.update(buffer);
			}

			byte[] buff = messageDigest.digest();
			str = parseByteToHexString(buff);
		} catch (Exception e) {
			// logging
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// logging
					e.printStackTrace();
				}
			}
		}

		return str;
	}

	/**
	 * 将 byte[] 转换成 16 进制字符串。
	 * 
	 * @param bytes
	 * @return
	 */
	public static String parseByteToHexString(byte[] bytes) {

		if (bytes == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder(bytes.length * 2);

		for (int i = 0; i < bytes.length; i++) {
			sb.append(Character.forDigit((bytes[i] & 240) >> 4, 16));
			sb.append(Character.forDigit(bytes[i] & 15, 16));
		}

		return sb.toString();
	}

}
