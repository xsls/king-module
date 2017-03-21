package com.king.common.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 采用AES算法的对称加密和解密
 */
public class AES {

    // 由于加解密涉及字符的编码方式，为了避免散落在代码各处不利于维护和保持统一，也为了便于切换为其它编码方式，所以提取出来
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    // 加解密密钥的偏移量，必须为16位
    private static final String IV = "1234567890123456";


    /**
     * 使用 AES 进行对称加密
     * @param plainText 原文
     * @param secretKey 密钥
     */
    public static String encrypt(String plainText, String secretKey) {
        try {
            // 根据secretKey生成加解密密钥
            SecretKeySpec key = generateSecretKey(secretKey);

            // 根据指定算法AES生成密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.ENCRYPT_MODE, key, generateIv());

            // 对原文进行加密
            byte[] b = cipher.doFinal(plainText.getBytes(CHARSET));
            // 对加密结果进行Base64编码
            byte[] b64 = Base64.getEncoder().encode(b);
            return new String(b64, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     * 使用 AES 进行对称解密
     * @param cipherText 密文
     * @param secretKey 密钥
     */
    public static String decrypt(String cipherText, String secretKey) {
        try {
            // 根据secretKey生成加解密密钥
            SecretKeySpec key = generateSecretKey(secretKey);

            // 根据指定算法AES生成密码器
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            // 初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(Cipher.DECRYPT_MODE, key, generateIv());

            byte[] b = Base64.getDecoder().decode(cipherText.getBytes(CHARSET));
            byte[] bb = cipher.doFinal(b);
            return new String(bb, CHARSET);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     * 根据一个字符串(secretKey)生成加解密密钥
     * 这是AES加解密都要用到的公共方法
     * @param secretKey 加解密的密码，类似于种子，用它来生成真正的加解密密钥，切记，secretKey并非真正的密钥
     */
    private static SecretKeySpec generateSecretKey(String secretKey) throws NoSuchAlgorithmException {
        // 创建密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // 初始化密钥生成器，秘钥长度为128位
//        keyGen.init(128, new SecureRandom(secretKey.getBytes(CHARSET)));

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(secretKey.getBytes(CHARSET));
        keyGen.init(128, secureRandom);

        // 利用秘钥生成器生成原始对称密钥
        SecretKey originalKey = keyGen.generateKey();
        // 获得原始对称密钥的字节数组
        byte[] b = originalKey.getEncoded();
        // 根据字节数组生成真正的AES密钥
        return new SecretKeySpec(b, "AES");
    }


    /**
     * 设置加解密的偏移量
     * 有些加密模式用不着偏移量，比如ECB
     */
    private static IvParameterSpec generateIv() {
        return new IvParameterSpec(IV.getBytes(CHARSET));
    }

}