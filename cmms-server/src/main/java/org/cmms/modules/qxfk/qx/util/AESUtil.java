package org.cmms.modules.qxfk.qx.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by 承宗 on 2016/12/5.
 */
public class AESUtil {
	private static String MODEL = "AES/ECB/PKCS5Padding";

	public static String encrypt(String content, String key) throws Exception {
		byte[] encryptResult = null;
		String result = null;
		try {
			byte[] contentBytes = content.getBytes("UTF-8");
			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
			Cipher cipher = Cipher.getInstance(MODEL);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			encryptResult = cipher.doFinal(contentBytes);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("AESUtil加密异常，error=" + ex.getMessage());
		}
		if (encryptResult != null) {
			result = Base64Util.encode(encryptResult);
		}
		// 替换\r \n
		result = result.replace("\n", "").replace("\r", "");
		return result;
	}

	public static String decrypt(String content, String key) throws Exception {
		String result = null;
		byte[] decryptResult = null;
		byte[] contentBytes = Base64Util.decode(content);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance(MODEL);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		decryptResult = cipher.doFinal(contentBytes);
		if (decryptResult != null) {
			result = new String(decryptResult, "UTF-8");
		}
		return result;

	}

}
