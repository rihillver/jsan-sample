package com.jsan.util.upload;

import java.nio.ByteBuffer;
import java.util.Random;

import sun.misc.BASE64Encoder;

/**
 * 不重复序列码命名规则。
 *
 */

@SuppressWarnings("restriction")
public class NamedSequenceImpl implements Named {

	protected int length = 12;
	protected static final String WORDCHAR_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public NamedSequenceImpl() {

	}

	public NamedSequenceImpl(int length) {

		if (length > this.length) {
			this.length = length;
		}
	}

	@Override
	public String getName(String fieldName, String fileName, int number) {

		return getSequenceCode(length);
	}

	/**
	 * 返回指定大小范围的随机整数值。
	 * 
	 * @param minValue
	 * @param maxValue
	 * @return
	 */
	public static int getInt(int minValue, int maxValue) {

		Random random = new Random();

		int value = maxValue - minValue;
		value = random.nextInt(value + 1);
		value += minValue;

		return value;
	}

	/**
	 * 返回指定长度范围的随机字符。
	 * 
	 * @param str
	 *            指定的待选字符串
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static String getString(String str, int minLength, int maxLength) {

		if (str != null) {

			char[] charArray = str.toCharArray();
			int charLength = charArray.length;

			Random random = new Random();
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < minLength; i++) {
				sb.append(charArray[random.nextInt(charLength)]);
			}

			int value = maxLength - minLength;

			if (value > 0) {
				value = random.nextInt(value + 1);
				for (int i = 0; i < value; i++) {
					sb.append(charArray[random.nextInt(charLength)]);
				}
			}

			return sb.toString();

		} else {
			return "";
		}
	}

	/**
	 * 返回指定长度范围的随机单词字符。<br>
	 * <br>
	 * 
	 * 字符范围："abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	 * 
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	public static String getWordChar(int minLength, int maxLength) {

		return getString(WORDCHAR_STRING, minLength, maxLength);
	}

	/**
	 * 返回指定长度的随机单词字符。<br>
	 * <br>
	 * 
	 * 字符范围："abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
	 * 
	 * @param length
	 * @return
	 */
	public static String getWordChar(int length) {

		return getWordChar(length, length);
	}

	/**
	 * 返回指定长度（不低于11）的随机序列码（基本不重复，长度越大越无重复的可能）。
	 * 
	 * @param length
	 * @return
	 */
	public static String getSequenceCode(int length) {

		String str = parseCurrentTimeMillisToSequenceCode();

		while (str.contains("/") || str.contains("+")) {
			str = parseCurrentTimeMillisToSequenceCode();
		}
		if (length > 11) {
			int len = length - 11;
			str = getWordChar(len) + str;
		}

		return str;
	}

	/**
	 * 将当前时间值转换为序列码（System.currentTimeMillis 乘以一个随机整数后再进行 Base64 编码）。
	 * 
	 * @return
	 */
	private static String parseCurrentTimeMillisToSequenceCode() {

		long l = System.currentTimeMillis();
		int i = getInt(1, 999999);
		l *= i;
		byte[] buffer = parseLongToByte(l);
		String str = parseBase64EncodeWithoutSymbol(buffer);

		return str;
	}

	/**
	 * 将 byte[] 进行 Base64 编码，并去除换行符（\r\n）和填补符号（=）。
	 * 
	 * @param bytes
	 * @return
	 */
	private static String parseBase64EncodeWithoutSymbol(byte[] bytes) {

		String str = null;

		if (bytes != null) {
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				str = encoder.encode(bytes);
				str = str.replaceAll("\r|\n|=", "");
			} catch (Exception e) {
				// logging
				e.printStackTrace();
			}
		}

		return str;
	}

	/**
	 * 将 long 转换为 byte[] 。
	 * 
	 * @param l
	 * @return
	 */
	private static byte[] parseLongToByte(long l) {

		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(0, l);
		return buffer.array();
	}

}
