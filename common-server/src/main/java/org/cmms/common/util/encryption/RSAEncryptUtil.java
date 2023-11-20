package org.cmms.common.util.encryption;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import sun.misc.BASE64Encoder;

import java.security.KeyPair;

/**
 * RSA 加密
 */
public class RSAEncryptUtil {
    /**
     * 加密方法
     * @param data  要加密的数据
     * @param publicKey  公钥
     * @return 加密的结果
     * @throws Exception
     */
    public static String encrypt(String data, String publicKey) {
        try {
            RSA rsa = new RSA(null, publicKey);
            byte[] encryptBytes = rsa.encrypt(data, KeyType.PublicKey);
            return Base64.encode(encryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密方法
     * @param data 要解密的数据
     * @param primaryKey 私钥
     * @return 解密的结果
     * @throws Exception
     */
    public static String desEncrypt(String data, String primaryKey) {
        try {
			RSA rsa = new RSA(primaryKey, null);
            byte[] decryptBytes = rsa.decrypt(data, KeyType.PrivateKey);
            return StrUtil.str(decryptBytes, CharsetUtil.CHARSET_UTF_8);
//            return Base64.encode(decryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用默认的公钥加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) {
        return encrypt(data, EncryptedString.RSA_PUBLIC_KEY);
    }

    /**
     * 使用默认的私钥解密
     * @param data
     * @return
     * @throws Exception
     */
    public static String desEncrypt(String data) {
        return desEncrypt(data, EncryptedString.RSA_PRIMARY_KEY);
    }



    /**
     * 测试
     */
    public static void main(String args[]) throws Exception {
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        System.out.println("公钥："+ Base64.encode(pair.getPublic().getEncoded()));

        System.out.println("私钥："+ Base64.encode(pair.getPrivate().getEncoded()));
        String test1 = "asdas";
        String test =new String(test1.getBytes(),"UTF-8");
        String data = null;
        // /g2wzfqvMOeazgtsUVbq1kmJawROa6mcRAzwG1/GeJ4=
        RSA rsa = new RSA();
        data = encrypt(test);
        System.out.println("数据："+test1);
        System.out.println("加密："+data);
        String jiemi =desEncrypt(data).trim();
        System.out.println("解密："+jiemi);


//公钥加密，私钥解密
        byte[] encrypt = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PublicKey);
        String ddasd = Base64.encode(encrypt);
        System.out.println("加密："+ddasd);
        byte[] decrypt = rsa.decrypt(ddasd, KeyType.PrivateKey);
        System.out.println(StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));
//Junit单元测试
//Assert.assertEquals("我是一段测试aaaa", StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8));

//私钥加密，公钥解密
        byte[] encrypt2 = rsa.encrypt(StrUtil.bytes("我是一段测试aaaa", CharsetUtil.CHARSET_UTF_8), KeyType.PrivateKey);
        byte[] decrypt2 = rsa.decrypt(encrypt2, KeyType.PublicKey);


        System.out.println("===============");
        System.out.println(desEncrypt("DKkrwT0RlN731p4ZBlOOac7E06vNksmvH1wMbbAXsXTLDVPRSUygkU8ZSmuSal 9roXPCQLMgZdAuA9ayLl5k mIx2A99yCcAlsIahuvisr3lgBn1eH9oi/4nt7CzL1asF7Kegez9vQJZ9n9zArbQImH9xym3c7ySQ37Zw54xaY="));
    }
}
