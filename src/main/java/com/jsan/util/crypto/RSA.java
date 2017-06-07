package com.jsan.util.crypto;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.crypto.Cipher;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * RSA 加密类（明文长度不可超过 117 字节）。<br>
 * <br>
 *
 * 注：JavaEE 默认算法是 RSA/None/PKCS1Padding ，不同 JDK 默认的补位方式可能不同，例如 Android 默认是 RSA/None/NoPadding 。
 *
 */

public class RSA {

	private Cipher cipher;

	private KeyPair keyPair;

	private RSAPublicKey publicKey;

	private RSAPrivateKey privateKey;

	/**
	 * JDK 默认服务提供者、JDK 默认算法、密钥长度 1024。
	 * 
	 */
	public RSA() {

		this(null);
	}

	/**
	 * 指定服务提供者（JDK 默认算法、密钥长度 1024）
	 * 
	 * @param provider
	 */
	public RSA(Provider provider) {

		this(provider, "RSA");
	}

	/**
	 * 指定服务提供者、算法（密钥长度 1024）。
	 * 
	 * @param provider
	 * @param algorithm
	 */
	public RSA(Provider provider, String algorithm) {

		this(provider, algorithm, 1024);
	}

	/**
	 * 指定服务提供者、算法、密钥长度。
	 * 
	 * @param provider
	 * @param algorithm
	 * @param size
	 */
	public RSA(Provider provider, String algorithm, int size) {

		try {
			KeyPairGenerator keyPairGenerator = null;
			if (provider == null) {
				keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				cipher = Cipher.getInstance(algorithm);
			} else {
				keyPairGenerator = KeyPairGenerator.getInstance("RSA", provider);
				cipher = Cipher.getInstance(algorithm, provider);
			}
			keyPairGenerator.initialize(size);
			keyPair = keyPairGenerator.generateKeyPair();
			publicKey = (RSAPublicKey) keyPair.getPublic();
			privateKey = (RSAPrivateKey) keyPair.getPrivate();
		} catch (Exception e) {
			// logging...
			e.printStackTrace();
		}
	}

	/**
	 * 返回 KeyPair 密钥对。
	 * 
	 * @return
	 */
	public KeyPair getKeyPair() {

		return keyPair;
	}

	/**
	 * 返回公钥。
	 * 
	 * @return
	 */
	public RSAPublicKey getPublicKey() {

		return publicKey;
	}

	/**
	 * 返回私钥。
	 * 
	 * @return
	 */
	public RSAPrivateKey getPrivateKey() {

		return privateKey;
	}

	/**
	 * 公钥加密。
	 * 
	 * @param content
	 * @return
	 */
	public byte[] encryptByPublicKey(byte[] content) {

		if (content != null) {
			try {
				cipher.init(Cipher.ENCRYPT_MODE, publicKey);
				content = cipher.doFinal(content);
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return content;
	}

	/**
	 * 私钥加密。
	 * 
	 * @param content
	 * @return
	 */
	public byte[] encryptByPrivateKey(byte[] content) {

		if (content != null) {
			try {
				cipher.init(Cipher.ENCRYPT_MODE, privateKey);
				content = cipher.doFinal(content);
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return content;
	}

	/**
	 * 公钥解密。
	 * 
	 * @param content
	 * @return
	 */
	public byte[] decryptByPublicKey(byte[] content) {

		if (content != null) {
			try {
				cipher.init(Cipher.DECRYPT_MODE, publicKey);
				content = cipher.doFinal(content);

			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return content;
	}

	/**
	 * 私钥解密。
	 * 
	 * @param content
	 * @return
	 */
	public byte[] decryptByPrivateKey(byte[] content) {

		if (content != null) {
			try {
				cipher.init(Cipher.DECRYPT_MODE, privateKey);
				content = cipher.doFinal(content);
			} catch (Exception e) {
				// logging...
				e.printStackTrace();
			}
		}

		return content;
	}

	/*
	public static void main(String[] args) {

		long start = System.currentTimeMillis();

		RSA rsa = new RSA();
		// RSA rsa = new RSA(new BouncyCastleProvider());
		// RSA rsa = new RSA(new BouncyCastleProvider(), "RSA/ECB/PKCS1Padding");
		// RSA rsa = new RSA(new BouncyCastleProvider(), "RSA/ECB/NoPadding");
		// RSA rsa = new RSA(new BouncyCastleProvider(), "RSA/ECB/NoPadding", 600); // 这种补位方式方式下明文位长度 <= 模长(bit)-11 ，即 <= 73.625 字节((600-11)/8)
		// RSA rsa = new RSA(new BouncyCastleProvider(), "RSA/ECB/PKCS1Padding", 600); // 这种补位方式方式下明文字节长度 <= 模长(byte)-11 ，即 <= 64 字节(600/8-11)

		System.out.println("公钥：" + rsa.getPublicKey());
		System.out.println("私钥模：" + rsa.getPrivateKey().getModulus());
		System.out.println("私钥指数：" + rsa.getPrivateKey().getPrivateExponent());
		System.out.println("私钥模长度（bit）：" + rsa.getPrivateKey().getModulus().bitLength());

		// String str = "abcdefghijklmnabcdefghijklmnabcdefghijklmnabcdefghijklmnabcdefghijklmnabcdefghiabcdefghijklmnabcdefghijklmnaaaaaaaaaa";
		// String str = "阿斯科利电梯井阿萨德刚的法规和的废话飞机规划局规划军阀割据更何况规划局法规和了";
		String str = "阿斯科利电梯井阿萨德刚的法规和的废话飞机水a";

		System.out.println("明文：" + str);
		System.out.println("明文长度：" + str.getBytes().length);

		byte[] content = rsa.encryptByPublicKey(str.getBytes());

		System.out.println("密文：" + new String(content));
		System.out.println("密文(十六进制)：" + com.jsan.utils.ConvertUtil.parseByteToHexString(content));
		System.out.println("密文(Base64)：" + Base64Util.encodeWithoutWrap(content));

		content = rsa.decryptByPrivateKey(content);

		System.out.println("解密：" + new String(content));

		System.out.println("耗时：" + (System.currentTimeMillis() - start));

	}
	*/

}
