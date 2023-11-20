/*
 * Copyright (c) , ssit-xm Ltd. All rights reserved.
 * Use is subject to license terms.
 */
package org.cmms.modules.qxfk.qx.util;



import org.cmms.modules.qxfk.qx.constant.AppConstant;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 * <pre>
 * 加密工具类
 * </pre>
 *
 * @author qiongwei.cai 2019年1月16日
 */
public class EncryptUtil {

	/**
	 * MD5加密
	 * 
	 * @author qiongwei.cai 2017年9月16日下午2:26:05
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		return encrypt(str, "md5");
	}

	/**
	 * SHA512加密
	 * 
	 * @author qiongwei.cai 2017年9月16日下午2:26:09
	 * @param str
	 * @return
	 */
	public static String sha512(String str) {
		return encrypt(str, "SHA-512");
	}

	/**
	 * ha256加密
	 * 
	 * @Description sha256加密
	 *              <p>
	 * @param plaintext 加密的明文,字符编码默认 UTF-8
	 * @return String 加密的密文
	 * @author 1032582419@qq.com 齐永攀
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String sha256(String plaintext) {
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-256");
			crypt.reset();
			crypt.update(plaintext.getBytes(AppConstant.CHARSET));
			return byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("加密字符串时出现异常.没有算法[hash]", e);
		} catch (UnsupportedEncodingException e) {
			// 抛出异常,避免不同的编码导致的签名不同
			throw new RuntimeException("加密字符串时出现异常.字符串获取UTF-8编码的Bytes时错误", e);
		}
	}

	/**
	 * 加密字符串,需指定加密类型
	 * 
	 * @author qiongwei.cai 2017年9月16日下午2:27:07
	 * @param str
	 * @param encryptType
	 * @return
	 */
	private static String encrypt(String str, String encryptType) {
		if (BaseUtil.isNotEmpty(str)) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance(encryptType);
				try {
					messageDigest.update(str.getBytes(AppConstant.CHARSET));
				} catch (UnsupportedEncodingException e) {
					// 抛出异常,避免不同的编码导致的签名不同
					throw new RuntimeException("加密字符串时出现异常.字符串获取UTF-8编码的Bytes时错误", e);
				}
				byte[] byteBuffer = messageDigest.digest();

				StringBuilder strHexString = new StringBuilder();
				for (int i = 0; i < byteBuffer.length; i++) {
					String hex = Integer.toHexString(0xff & byteBuffer[i]);
					if (hex.length() == 1) {
						strHexString.append('0');
					}
					strHexString.append(hex);
				}
				return strHexString.toString();
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("加密字符串时出现异常.没有算法[" + encryptType + "]", e);
			}
		}
		return null;
	}

	/**
	 * @Description 字节转化为哈希字符串
	 *              <p>
	 * @param hash
	 * @return String
	 * @author 1032582419@qq.com 齐永攀
	 */
	public static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

}
