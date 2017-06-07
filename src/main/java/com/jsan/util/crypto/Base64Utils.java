package com.jsan.util.crypto;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Base64Utils {

	/**
	 * Base64 编码。
	 * 
	 * @param bytes
	 * @return
	 */
	public static String encode(byte[] bytes) {

		String str = null;

		if (bytes != null) {
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				str = encoder.encode(bytes);
			} catch (Exception e) {
				// logging
				e.printStackTrace();
			}
		}

		return str;
	}

	/**
	 * Base64 编码。
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {

		if (str != null) {
			str = encode(str.getBytes());
		}

		return str;
	}

	/**
	 * Base64 编码，去除换行符（\r\n）。
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeWithoutWrap(byte[] bytes) {

		String str = encode(bytes);

		if (str != null) {
			str = str.replaceAll("\r|\n", "");
		}

		return str;
	}

	/**
	 * Base64 编码，去除换行符（\r\n）。
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeWithoutWrap(String str) {

		if (str != null) {
			str = encodeWithoutWrap(str.getBytes());
		}

		return str;
	}

	/**
	 * Base64 编码，去除换行符（\r\n）和填补符号（=）。
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeWithoutSymbol(byte[] bytes) {

		String str = encode(bytes);

		if (str != null) {
			str = str.replaceAll("\r|\n|=", "");
		}

		return str;
	}

	/**
	 * Base64 编码，去除换行符（\r\n）和填补符号（=）。
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeWithoutSymbol(String str) {

		if (str != null) {
			str = encodeWithoutSymbol(str.getBytes());
		}

		return str;
	}

	/**
	 * Base64 编码（URL 模式），去除换行符（\r\n）和填补符号（=），并将 "+" 替换成 "-" 、 "/" 替换成 "_" 。
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeForURL(byte[] bytes) {

		String str = encodeWithoutSymbol(bytes);

		if (str != null) {
			str = str.replaceAll("\\+", "-");
			str = str.replaceAll("/", "_");
		}

		return str;
	}

	/**
	 * Base64 编码（URL 模式），去除换行符（\r\n）和填补符号（=），并将 "+" 替换成 "-" 、 "/" 替换成 "_" 。
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeForURL(String str) {

		if (str != null) {
			str = encodeForURL(str.getBytes());
		}

		return str;
	}

	/**
	 * Base64 解码，对于长度不足 4 倍数的加上填补符号（=）补足后再进行解码。
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] decode(String str) {

		byte[] buff = null;

		if (str != null) {

			int length = str.replaceAll("\r|\n", "").length() % 4;

			if (length == 2) {
				str += "==";
			}
			if (length == 3) {
				str += "=";
			}

			try {
				BASE64Decoder decoder = new BASE64Decoder();
				buff = decoder.decodeBuffer(str);
			} catch (Exception e) {
				// logging
				e.printStackTrace();
			}
		}

		return buff;
	}

	/**
	 * Base64 解码，对于长度不足 4 倍数的加上填补符号（=）补足后再进行解码。
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeToString(String str) {

		byte[] buff = decode(str);

		if (buff != null) {
			str = new String(buff);
		}

		return str;
	}

	/**
	 * Base64 解码（URL 模式）。
	 * 
	 * @param str
	 * @return
	 */
	public static String decodeToStringForURL(String str) {

		if (str != null) {
			str = str.replace('-', '+');
			str = str.replace('_', '/');
			str = decodeToString(str);
		}

		return str;
	}

}
