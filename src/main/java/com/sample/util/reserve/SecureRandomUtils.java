package com.sample.util.reserve;

import java.security.SecureRandom;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class SecureRandomUtils {

	private static final Random RANDOM = new SecureRandom();

	/**
	 * 返回大小写的英文、数字、反斜杠、加号、等号组成的杂乱随机字符串，重复概率低，同UUID一样。
	 * 
	 * @return
	 */
	public static String getString() {

		byte[] randomBytes = new byte[16];
		RANDOM.nextBytes(randomBytes);

		return DatatypeConverter.printBase64Binary(randomBytes);
	}

	public static void main(String[] args) {

		for (int i = 0; i < 1000; i++) {
			System.out.println(getString());
		}
	}

}
