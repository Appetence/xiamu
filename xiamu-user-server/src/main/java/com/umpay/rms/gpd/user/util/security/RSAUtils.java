package com.umpay.rms.gpd.user.util.security;

import com.umpay.rms.gpd.user.util.rsa.AttributeConstant;
import com.umpay.rms.gpd.user.util.rsa.Base64Utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 中文类名: RSA密码加密工具类
 * 中文描述: RSA密码加密工具类
 *
 * @author zhouxinlei
 * @date 2019-09-11 10:30:43
 */
public class RSAUtils {
    private static final Logger logger = LoggerFactory.getLogger(RSAUtils.class);
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 获取密钥对
     *
     * @return java.security.KeyPair
     * @author zhouxinlei
     * @date 2019-09-12 15:25:55
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(AttributeConstant.ALGORITHM_NAME);
        generator.initialize(1024);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return java.security.PrivateKey
     * @author zhouxinlei
     * @date 2019-09-12 15:26:15
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(AttributeConstant.ALGORITHM_NAME);
        byte[] decodedKey = Base64Utils.decoder(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return java.security.PublicKey
     * @author zhouxinlei
     * @date 2019-09-12 15:26:40
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(AttributeConstant.ALGORITHM_NAME);
        byte[] decodedKey = Base64Utils.decoder(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data      待加密数据
     * @param publicKey 公钥
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019-09-12 15:27:07
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AttributeConstant.ALGORITHM_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64Utils.encoder(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data       待解密数据
     * @param privateKey 私钥
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019-09-12 15:27:29
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AttributeConstant.ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        //对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data       待签名数据
     * @param privateKey 私钥
     * @return java.lang.String
     * @author zhouxinlei
     * @date 2019-09-12 15:24:08
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(AttributeConstant.ALGORITHM_NAME);
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance(AttributeConstant.MD5_RSA);
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64Utils.encoder(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData   原始字符串
     * @param publicKey 公钥
     * @param sign      签名
     * @return boolean 是否验签通过
     * @author zhouxinlei
     * @date 2019-09-12 15:23:38
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(AttributeConstant.ALGORITHM_NAME);
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance(AttributeConstant.MD5_RSA);
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64Utils.decoder(sign.getBytes()));
    }

    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey = new String(Base64Utils.encoder(keyPair.getPrivate().getEncoded()));
            String publicKey = new String(Base64Utils.encoder(keyPair.getPublic().getEncoded()));
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
        //    Long time = System.currentTimeMillis();
           // System.out.println("加密前时间："+time);
            // RSA加密
          //  String data = "123456"+',' +time ;
            String data = "123abc" ;
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);



            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            String longtime = StringUtils.substringAfterLast(decryptData, ",");
            System.out.println("时间 加密后:" + longtime);

            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            System.out.print("加解密异常");
        }
       /*
私钥:MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJLKRnMY8ryjnUIT20RFc9Nce0i9WQDjGge0tBIqfk/iFcgHuWVkfSEp5BqniqTJGPTZfw9zKv13eXXjTwK8Vq5SpZ5cQrtGGZeIG2/CBnsUvHu/r+EyYKkMK2QABpdSFYJjNq24jKmkG6xlfsVcbLnux7zUepcgShNBo4NxSaSHAgMBAAECgYB//c+f3EkLIr6kNxwhU03DJgh5TGCm3V59CZtZm6O3k7LaT6HgPta8bAJxG1VQiegw1tRrbEg36k0F9cWW5RHObDfyvKeMqzevkEZCO4YPNHcsumewQR1jgTWpbL/Mk6JrOvCIzMWKLrvVLbUTIEn/q44uNTgakMTS9J0vo87QgQJBAMULNTqdiz+L70rX7n5YvzigLAFHALdoeRMtwDYHfiplvpRMama0dzQxtrd5T6nAIEfl5clQO+VEWBZnEjxhnIkCQQC+tdUYNZQBOsjIFDjA5lkA9ewafzFdswhgYcNRGudSsr6flyKJ8MBpy1XdkekBNgIZOBjmpx0nK4+LyVfdJJSPAkBhRpE2qMQv0QOHbWaeFW4BpIFU+BqQcREkuLdldPmCMMyPJdJ5dapLqCdOGKONZ/4HwKVaZzPKkZzzIltMV9wRAkBkeOl2YqvB5hVni3fG8NRNNEKaRw5b/flYxcifzxu1ruPMslQI9iR42B45qChoceqzHKHJypsKSuHKVF4M7LuzAkAauqOc0reqNy6BRtHi0YwBus9BZMp1RlO6HUJGp/90OeilPmYQ5O2xergvQo02kmFAsNWctGkr2TuoZJI3UtOV
公钥:MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCSykZzGPK8o51CE9tERXPTXHtIvVkA4xoHtLQSKn5P4hXIB7llZH0hKeQap4qkyRj02X8Pcyr9d3l1408CvFauUqWeXEK7RhmXiBtvwgZ7FLx7v6/hMmCpDCtkAAaXUhWCYzatuIyppBusZX7FXGy57se81HqXIEoTQaODcUmkhwIDAQAB
加密后内容:Yk7tVr5l0QW2jXY6RlB+4mZwbOCXDAr9mn7dINrT3h+ePlQs/GpAcNN4+qjYW3FciQU0vs5GcKesqxNrKzCv9AvGL/W/Vr0PMpoOOc4td51PYA78nB8H11Mvdw4mdKkux6LSOMdfIECq2/iGUBkJuJyfhTyQNckCrpPUx9/0w1Q=*/
     /*   try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
            String privateKey ="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALB3izcGZkHf8nNIAzn6eEXU548sYa4zA2bjcEBOQOykZvPso3aAfCkLcvjUsV8B7OB7mLGjBC1vTTP5ReSS2qjQBvdT5yyJ50iYrVTJLYtsgmQ6S3IYmnU+NKeugtFR3jZ+eJisRffrmr6O0M+sOnUJLEOD4COahfOz/GOfz6QzAgMBAAECgYARQc+KdQTBAm6KWYt/OwBSC+0NMlDnITP+9tT+ZOiye61Zw1WE/DGoLedzcBFccirBFTUsgf2hl9Xb9E+id74MRIJqhcLrSGu6emJf/+GcohwEaEfQEm+raRq+rDBwBF4z4HDLV8Rw+nLg59Jt7GiYL9SAllHz9Tu10fI8tcOMgQJBAOVtJixELFaEEpbCOf9qtVdTrGeu6INHqigvdpoAPgKkOvQc2oN6DZXh/MLYtMsPJnuyuczBE8+WbEUi5Onw5kECQQDE6BDRddKSQPF1mgDatnkayeESofSpqkbC2dyPxbZ3wr2P2ARHPguIVhTkUk7uzywvGZOCNtExfBbYVsXFPPVzAkEA3aefasMOpyOApiDOUeNxggY4GhAVWchiL+LzTgQP5RNwVaqOvtmryHQGdOVTfp+LiZT6B0U+858byYvV5+MzQQJAOQpk/Nid5GJN0FyJjNI5aOo8ejJSduft0bpc64VB45WVZlsUejLeEmOlb7LsTL/VM9u74IfSt/wTfh488nNDQwJAFqns8zZddPw7aXKBJSDJMavql44MpUSiRBKqrLlnp+7C/c7AUINUemMNJCuqDmJElTi5wYaFCO8T0DufJKBpbA==";
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClbnU03PmJED/I43F97IT328xQoTA4oTsbnTIrZ9heirBrjKOGGw0c0F1Tqkoczf4OdrCcu4kEb1I9OXOl5CREBthC5Dieqp4lVP1Qkgz8Sv75OPNj0wtrl8Hojc9kTGeQvEMi64RzDzlO3JJmoexOMItBTM8yBqQ1oz6sui09bQIDAQAB";
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);

            // RSA加密
            String data = "123";
//            String encryptData ="qNx3DloleU0FJHDNRLoD91SH+opU+Kr25/qCN9nkKdYgyHP7vPAbyLvS5NVijqSqU15TwwVVHCoeBpFX393BLP05cAMcqTe+mqwlh3fOkzhXinWc6IYFU5v96YLt2KHytb+XqEDYu2VDCE8gLtu7hFlWk7JVwJWcEsXK/0qqofc=";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);
            String longtime = StringUtils.substringAfterLast(decryptData, ",");
            System.out.println("时间 加密后:" + longtime);

            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            System.out.print("加解密异常");
        }*/
    }
}