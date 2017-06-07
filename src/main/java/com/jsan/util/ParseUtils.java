package com.jsan.util;

import java.math.BigInteger;

/**
 * 常用转换工具类。
 *
 */

public class ParseUtils {

	/**
	 * 将首字母变小写。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseFirstCharToLowerCase(String str) {

		if (str == null) {
			return null;
		}

		char firstChar = str.charAt(0);

		if (firstChar >= 'A' && firstChar <= 'Z') {
			char[] arr = str.toCharArray();
			arr[0] += ('a' - 'A');
			return new String(arr);
		}

		return str;

		// if (str != null && str.length() > 0) {
		// return Character.toLowerCase(str.charAt(0)) + str.substring(1);
		// } else {
		// return str;
		// }

	}

	/**
	 * 将首字母变大写。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseFirstCharToUpperCase(String str) {

		if (str == null) {
			return null;
		}

		char firstChar = str.charAt(0);

		if (firstChar >= 'a' && firstChar <= 'z') {
			char[] arr = str.toCharArray();
			arr[0] -= ('a' - 'A');
			return new String(arr);
		}

		return str;

		// if (str != null && str.length() > 0) {
		// return Character.toUpperCase(str.charAt(0)) + str.substring(1);
		// } else {
		// return str;
		// }
	}

	/**
	 * 将 byte[] 制转换成 16 进制字符串。
	 * 
	 * @param bytes
	 * @return
	 */
	public static String parseByteToHexString(byte[] bytes) {

		if (bytes == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}

		return sb.toString();
	}

	/**
	 * 将 16 进制字符串转换为 byte[] 。
	 * 
	 * @param hexString
	 * @return
	 */
	public static byte[] parseHexStringToByte(String hexString) {

		if (hexString == null) {
			return null;
		}

		byte[] result = new byte[hexString.length() / 2];

		for (int i = 0; i < hexString.length() / 2; i++) {
			int high = Integer.parseInt(hexString.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexString.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}

		return result;
	}

	/**
	 * 将 16 进制字符串转换成大整数。
	 * 
	 * @param hexString
	 * @return
	 */
	public static BigInteger parseHexStringToBigInteger(String hexString) {

		BigInteger bigInteger = null;

		try {
			bigInteger = new BigInteger(hexString, 16);
		} catch (Exception e) {
			// logging...
			// e.printStackTrace();
		}

		return bigInteger;
	}

	/**
	 * 将大整数转换成 16 进制字符串。
	 * 
	 * @param bigInteger
	 * @return
	 */
	public static String parseBigIntegerToHexString(BigInteger bigInteger) {

		if (bigInteger == null) {
			return null;
		}

		return bigInteger.toString(16);
	}

	/**
	 * 将 10 进制字符串转换成大整数。
	 * 
	 * @param hexString
	 * @return
	 */
	public static BigInteger parseStringToBigInteger(String str) {

		BigInteger bigInteger = null;

		try {
			bigInteger = new BigInteger(str);
		} catch (Exception e) {
			// logging...
			// e.printStackTrace();
		}

		return bigInteger;
	}

	/**
	 * 将大整数转换成 10 进制字符串。
	 * 
	 * @param bigInteger
	 * @return
	 */
	public static String parseBigIntegerToString(BigInteger bigInteger) {

		if (bigInteger == null) {
			return null;
		}

		return bigInteger.toString();
	}

	/**
	 * 将字符串转换成 Unicode 形式的字符串。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseStringToUnicode(String str) {

		return parseStringToUnicode(str, false);
	}

	/**
	 * 将字符串转换成 Unicode 形式的字符串，并指定是否不包括 ASCII 编码的转换（false：包括、true：不包括）。
	 * 
	 * @param str
	 * @param excludeASCII
	 * @return
	 */
	public static String parseStringToUnicode(String str, boolean excludeASCII) {

		if (str != null) {

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (excludeASCII && c < 128) {
					sb.append(c);
				} else {
					sb.append(String.format("\\u%04x", (int) c));
				}
			}

			str = sb.toString();
		}

		return str;
	}

	/**
	 * 将 Unicode 形式的字符串转换成字符串。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseUnicodeToString(String str) {

		if (str != null) {

			if (str.contains("\\u")) {

				StringBuilder sb = new StringBuilder();

				String[] hexs = str.split("\\\\u");

				for (int i = 1; i < hexs.length; i++) {

					if (hexs[i].length() > 4) {
						int c = Integer.parseInt(hexs[i].substring(0, 4), 16);
						sb.append((char) c);
						sb.append(hexs[i].substring(4));
					} else {
						int c = Integer.parseInt(hexs[i], 16);
						sb.append((char) c);
					}
				}

				str = sb.toString();
			}
		}

		return str;
	}

	/**
	 * 将驼峰形式的字符串转换成带下划线形式的字符串。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseToUnderlineString(String str) {

		if (str == null) {
			return null;
		}

		int length = str.length();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					sb.append('_');
				}
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

	/**
	 * 将含下划线的字符串转换成驼峰形式的字符串（小驼峰）。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseToLowerCamelCaseString(String str) {

		return parseToCamelCaseString(str, false);
	}

	/**
	 * 将含下划线的字符串转换成驼峰形式的字符串（大驼峰）。
	 * 
	 * @param str
	 * @return
	 */
	public static String parseToUpperCamelCaseString(String str) {

		return parseToCamelCaseString(str, true);
	}

	/**
	 * 将含下划线的字符串转换成驼峰形式的字符串。
	 * 
	 * @param str
	 * @param firstCharacterUppercase
	 * @return
	 */
	public static String parseToCamelCaseString(String str, boolean firstCharacterUppercase) {

		if (str == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		boolean nextUpperCase = false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '_') {
				nextUpperCase = true;
				continue;
			}

			if (nextUpperCase) {
				sb.append(Character.toUpperCase(c));
				nextUpperCase = false;
			} else {
				sb.append(c);
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		} else {
			sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		}

		return sb.toString();
	}

}
