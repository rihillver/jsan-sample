package com.jsan.util.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES 加密工具类。
 *
 */

public class AESUtils {

	/**
	 * 加密。
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static byte[] encrypt(byte[] content, String password) {

		byte[] result = null;

		if (content != null && password != null) {
			try {
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(128, new SecureRandom(password.getBytes()));
				SecretKey secretKey = keyGenerator.generateKey();
				byte[] encoded = secretKey.getEncoded();
				SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
				result = cipher.doFinal(content);
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 加密。
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encrypt(String content, String password) {

		String result = null;

		if (content != null && password != null) {
			try {
				byte[] contents = content.getBytes();
				byte[] results = encrypt(contents, password);
				result = parseByteToHexString(results);
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 解密。
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {

		byte[] result = null;

		if (content != null && password != null) {
			try {
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(128, new SecureRandom(password.getBytes()));
				SecretKey secretKey = keyGenerator.generateKey();
				byte[] encoded = secretKey.getEncoded();
				SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
				result = cipher.doFinal(content);
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 解密。
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String decrypt(String content, String password) {

		String result = null;

		if (content != null && password != null) {
			byte[] contents = parseHexStringToByte(content);
			byte[] results = decrypt(contents, password);
			result = new String(results);
		}

		return result;
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
	
	/*
	public static void main(String[] args) {

		String content = "我是谁啊";
		String password = "abc2008";

		long l = System.currentTimeMillis();

		String ciphertext = encrypt(content, password);

		System.out.println("---加密---");
		System.out.println("密文=" + ciphertext);
		System.out.println("耗时=" + (System.currentTimeMillis() - l));

		l = System.currentTimeMillis();

		String text = decrypt(ciphertext, password);

		System.out.println("---解密---");
		System.out.println("原文=" + text);
		System.out.println("耗时=" + (System.currentTimeMillis() - l));

	}
	*/

}
