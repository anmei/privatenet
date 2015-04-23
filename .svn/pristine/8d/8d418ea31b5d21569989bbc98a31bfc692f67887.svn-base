package com.rhcheng.util.digest;

import java.io.IOException;
import java.security.Key;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.lang.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class DES {
	private final static String DES = "DES";


	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @param key
	 *            加密键byte数组
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {

		byte[] bt = encrypt(data.getBytes(), key.getBytes());

		String strs = new BASE64Encoder().encode(bt);

		return strs;

	}

	/**
	 * 
	 * Description 根据键值进行解密
	 * 
	 * @param data
	 * 
	 * @param key
	 *            加密键byte数组
	 * 
	 * @return
	 * 
	 * @throws IOException
	 * 
	 * @throws Exception
	 */

	public static String decrypt(String data, String key) throws IOException,

	Exception {

		if (data == null)

			return null;

		BASE64Decoder decoder = new BASE64Decoder();

		byte[] buf = decoder.decodeBuffer(data);

		byte[] bt = decrypt(buf, key.getBytes());

		return new String(bt);

	}

	/**
	 * 
	 * Description 根据键值进行加密 059
	 * 
	 * @param data
	 *            060
	 * @param key
	 *            加密键byte数组 061
	 * @return 062
	 * @throws Exception
	 *             063
	 */

	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {

		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作

		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);

	}

	/**
	 * Description 根据键值进行解密 087
	 * 
	 * @param data
	 *            088
	 * @param key
	 *            加密键byte数组 089
	 * @return 090
	 * @throws Exception
	 *             091
	 */

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {

		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密钥数据创建DESKeySpec对象

		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作

		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(data);
	}

	// ///////////////////////////////////////////////////////////////////////////////
	/**
	 * DES ALGORITHM 算法 <br>
	 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR) <br>
	 * 
	 * <pre>
	 * 
	 * DES                  key size must be equal to 56  
	 * DESede(TripleDES)    key size must be equal to 112 or 168  
	 * AES                  key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available  
	 * Blowfish             key size must be multiple of 8, and can only range from 32 to 448 (inclusive)  
	 * RC2                  key size must be between 40 and 1024 bits  
	 * RC4(ARCFOUR)         key size must be between 40 and 1024 bits
	 * </pre>
	 */
	// ///////////////////////////////////////////////////////////////////////////////
	/**
	 * 生成密钥
	 * 
	 * @param seed
	 *            密钥
	 * @param algorithm
	 *            算法
	 * @throws Exception
	 */
	public static byte[] initDESKey(String seed, String algorithm)
			throws Exception {
		SecureRandom secureRandom = null;
		if (StringUtils.isBlank(seed)) {
			secureRandom = new SecureRandom(seed.getBytes());
		} else {
			secureRandom = new SecureRandom();
		}
		KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 转换密钥
	 * 
	 * @param key
	 * @param algorithm
	 *            算法
	 * @throws Exception
	 */
	private static Key toKey(byte[] key, String algorithm) throws Exception {
		SecretKey secretKey = null;
		if (algorithm.equals("DES")) {
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(algorithm);
			secretKey = keyFactory.generateSecret(dks);
		} else {
			// 除了DES外,其用算法用以下构造密钥
			secretKey = new SecretKeySpec(key, algorithm);
		}
		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @throws Exception
	 */
	public static byte[] desDecrypt(byte[] data, byte[] key, String algorithm)
			throws Exception {
		Key k = toKey(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @throws Exception
	 */
	public static byte[] desEncrypt(byte[] data, byte[] key, String algorithm)
			throws Exception {
		Key k = toKey(key, algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	

	public static void main(String[] args) throws Exception {
//		String data = "hwt_211";
//		String key = "8lPj0L783JlHTVMvifbqEA34";
//		System.err.println(encrypt(data, key));
//		System.out.println(decrypt("3lWnFIni4aA=", key));
		// System.err.println(decrypt(encrypt(data, key), key));
	}

}
