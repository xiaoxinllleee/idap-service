package org.cmms.modules.util.sm4;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sm4Utils {
	private static String secretKey = "JeA8U9wHFOMfs2V8";
	private static String iv = "U3SwD9fW6c6h9SNS";
	private static boolean hexString = false;
	private static Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	public Sm4Utils() {

	}

	public static String encryptData_ECB(String plainText) {

		try {
			Sm4Context ctx = new Sm4Context();
			ctx.isPadding = true;
			ctx.mode = Sm4.SM4_ENCRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = Util.hexStringToBytes(secretKey);
			} else {
				keyBytes = secretKey.getBytes();
			}

			Sm4 Sm4 = new Sm4();
			Sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = Sm4.sm4_crypt_ecb(ctx, plainText.getBytes("GBK"));
			String cipherText = new BASE64Encoder().encode(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0) {

				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decryptData_ECB(String cipherText) {
		try {
			Sm4Context ctx = new Sm4Context();
			ctx.isPadding = true;
			ctx.mode = Sm4.SM4_DECRYPT;

			byte[] keyBytes;
			if (hexString) {
				keyBytes = Util.hexStringToBytes(secretKey);
			} else {
				keyBytes = secretKey.getBytes();
			}

			Sm4 Sm4 = new Sm4();
			Sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = Sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
			return new String(decrypted, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String encryptData_CBC(String plainText) {
		try {
			Sm4Context ctx = new Sm4Context();
			ctx.isPadding = true;
			ctx.mode = Sm4.SM4_ENCRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = Util.hexStringToBytes(secretKey);
				ivBytes = Util.hexStringToBytes(iv);
			} else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			Sm4 Sm4 = new Sm4();
			Sm4.sm4_setkey_enc(ctx, keyBytes);
			byte[] encrypted = Sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("GBK"));
			String cipherText = new BASE64Encoder().encode(encrypted);
			if (cipherText != null && cipherText.trim().length() > 0) {

				Matcher m = p.matcher(cipherText);
				cipherText = m.replaceAll("");
			}
			return cipherText;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decryptData_CBC(String cipherText) {
		try {
			Sm4Context ctx = new Sm4Context();
			ctx.isPadding = true;
			ctx.mode = Sm4.SM4_DECRYPT;

			byte[] keyBytes;
			byte[] ivBytes;
			if (hexString) {
				keyBytes = Util.hexStringToBytes(secretKey);
				ivBytes = Util.hexStringToBytes(iv);
			} else {
				keyBytes = secretKey.getBytes();
				ivBytes = iv.getBytes();
			}

			Sm4 Sm4 = new Sm4();
			Sm4.sm4_setkey_dec(ctx, keyBytes);
			byte[] decrypted = Sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(cipherText));
			return new String(decrypted, "GBK");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws IOException {
		String name = "邓健";
		String idn = "430482199803196893";

		String s = Sm4Utils.encryptData_CBC(name);
		String s2 = Sm4Utils.encryptData_CBC(idn);

		System.out.println(s);
		System.out.println(s2);

		String name2 = s;
		String idn3 = s2;
		String s11 = Sm4Utils.decryptData_CBC(name2);
		String s22 = Sm4Utils.decryptData_CBC(idn3);
		System.out.println(s11);
		System.out.println(s22);

	}
}
