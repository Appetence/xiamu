package com.umpay.rms.gpd.user.util.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.net.URLDecoder;

/**
 * Created by shixiangdong on 2017/8/10.
 */
public class EncryptUtils {
    /**
     * 密钥
     */
    private static final String KEY = "abcdefgabcdefg12";

    /**
     * 算法
     */
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    /**
     * aes解密
     * @param encrypt   内容
     * @return
     * @throws Exception
     */
    public static String aesDecrypt(String encrypt) throws Exception {
        if(StringUtils.isBlank(encrypt)){
            return "";
        }
//        System.out.println("前台传的加密数据："+encrypt);
        String str = URLDecoder.decode(encrypt,"UTF-8");
//        System.out.println("decode后的加密数据："+str);
        return aesDecrypt(str, KEY);
    }

    /**
     * aes加密
     * @param content
     * @return
     * @throws Exception
     */
    public static String aesEncrypt(String content) throws Exception {
        return aesEncrypt(content, KEY);
    }

    /**
     * 将byte[]转为各种进制的字符串
     * @param bytes byte[]
     * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
     * @return 转换后的字符串
     */
    public static String binary(byte[] bytes, int radix){
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    public static byte[] base64Decode(String base64Code) throws Exception{
        return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }


    /**
     * AES加密
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的byte[]
     * @throws Exception
     */
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }


    /**
     * AES加密为base 64 code
     * @param content 待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(content, encryptKey));
    }

    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes,"UTF-8");
    }


    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
    }

    public static void main(String[] args) throws Exception {
//        String username = "admin";
//        String pwd = "admin1356";
//        System.out.println("加密前username：" + username);
//        System.out.println("加密前pwd：" + pwd);
//
//        System.out.println("加密密钥和解密密钥：" + KEY);
//
//        String encryptUsername = aesEncrypt(username);
//        String encryptPwd = aesEncrypt(pwd);
//        System.out.println("加密后username：" + encryptUsername);
//        System.out.println("加密后pwd：" + encryptPwd);
//
//        String decryptUsername = aesDecrypt(encryptUsername);
//        String decryptPwd = aesDecrypt(encryptPwd);
//        System.out.println("解密后username：" + decryptUsername);
//        System.out.println("解密后pwd：" + decryptPwd);
//        String str = URLEncoder.encode("+20quuvA0M77gghHtd1a5lc4Lp1sLu5oxGpAqPomB0xuqV14yP3m22iHiczIT2YJ");
//        System.out.println("解密strss：" + aesDecrypt(str));
//        String decStr = "%252B20quuvA0M77gghHtd1a5lc4Lp1sLu5oxGpAqPomB0xuqV14yP3m22iHiczIT2YJ";
//        String en1Str =URLDecoder.decode(decStr,"UTF-8");
//        System.out.println("en1Str:::"+en1Str);
//        String en2Str =URLDecoder.decode(en1Str,"UTF-8");
//        System.out.println("en2Str:::"+en2Str);
//        System.out.println("aesDecrypt:::"+aesDecrypt(en1Str));
//        System.out.println("解密strss：" + aesDecrypt("dxc0mwMN1bl8cOhC51mGi21uPUzdCKXduAiTAvnSeixtTpEGEuFEm139Zoa7+i1EBHQUqPSwrSabGjGI1nJ3Vdi4HthViGMOF/uTip7SLu8="));
//        System.out.println("解密strss：" + aesDecrypt("rl8wU1bnAlodL%2FSAtmaueCaAqTKY72xeKxjw8MsO5CZZYK3ZIs%2FBu6zTT00P5CxnBadVGaYeDfRMD6aYuIWbuQ%3D%3D"));
//        System.out.println("解密strss：" + aesDecrypt("pHhYkokjrjbi%2F8DrVFQUMg%3D%3D"));


        //1c265c4b6c5c28a37e1590963ac53847
        //md5 98efbf3999b04a168be9f7635641b123

//        System.out.println("解密strss：" + aesDecrypt("7e2babd855ccc02621292c1dc47b3e8d"));loginMD5pwd

//        String loginMD5pwd = MD5Util.getLoginMD5pwd("98efbf3999b04a168be9f7635641b123", "7e2babd855ccc02621292c1dc47b3e8d");
//        System.out.println(loginMD5pwd);

//        String loginPwd = MD5Util.getMD5pwd("98efbf3999b04a168be9f7635641b123", "qKaXrj9C");
//        System.out.println(loginPwd);

        String s = "('c54f470fe7554d75bab02bcd65e5b317', '姚维铜11', '13846326727', 'b04cb1e0f5a746d1bcdbe6ad02d0a835', '843632617@qq.com', 19950529, '53b7d6686e2843cc84b3fde379e53aab', '{}', '5bd03fa8266b429c8aefe09985594c04', '5bd03fa8266b429c8aefe09985594c04', now(), now(), 0 ), ('0e6f2bc938a34742bcad287c1cfdeb3e', '姚维铜12', '13846326728', 'b04cb1e0f5a746d1bcdbe6ad02d0a835', '843632618@qq.com', 19950529, '53b7d6686e2843cc84b3fde379e53aab', '{}', '5bd03fa8266b429c8aefe09985594c04', '5bd03fa8266b429c8aefe09985594c04', now(), now(), 1 ), ('5bc668ab91c14345923592a49799f3eb', '姚维铜13', '13846326729', 'b04cb1e0f5a746d1bcdbe6ad02d0a835', '843632619@qq.com', 19950529, '53b7d6686e2843cc84b3fde379e53aab', '{}', '5bd03fa8266b429c8aefe09985594c04', '5bd03fa8266b429c8aefe09985594c04', now(), now(), 2 ), ('214adbe4aeae45398c4f67956d2df808', '姚维铜14', '13846326730', 'b04cb1e0f5a746d1bcdbe6ad02d0a835', '843632620@qq.com', 19950529, '53b7d6686e2843cc84b3fde379e53aab', '{}', '5bd03fa8266b429c8aefe09985594c04', '5bd03fa8266b429c8aefe09985594c04', now(), now(), 3 )";
//        2 ),
        String replace = s.replace(" ),", ", '2'),");
        System.out.println(replace);

    }
}
