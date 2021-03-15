package com.umpay.rms.gpd.user.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

public class MD5Util {

	private static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
	public static String MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch ( Exception e ) {
			logger.error(e.getMessage(), e);
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for ( int i = 0; i < charArray.length; i++ ){
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();

		for ( int i = 0; i < md5Bytes.length; i++ ) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if ( val < 16 ) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}

		return hexValue.toString();
	}

	/**
	 * MD5加密
	 * 
	 * @param str1
	 *            盐
	 * @param pwd
	 *            密码
	 * @return MD5
	 */
	public static String getMD5pwd(String str1, String pwd) {
		String md5 = MD5(pwd);
		return MD5(str1 + md5);
	}

	/**
	 * MD5加密
	 *
	 * @param str1
	 *            盐
	 * @param md5Pwd
	 *            md5加密后的密码
	 * @return MD5
	 */
	public static String getLoginMD5pwd(String str1, String md5Pwd) {
		return MD5(str1 + md5Pwd);
	}

	public static void main(String[] args) {
//		String username = "user1";
		String pwd = "user1234";
		String uuid = UUIDUtil.getUUID();
		System.out.println("uuid: " + uuid);
		System.out.println("md5(pwd + uuid) : "+ getLoginMD5pwd(uuid, MD5(pwd)));

		/**
		 *
		 * 张三127
		 * id	00657e8a89b24a91a39c7f89ca2aff64
		 * pwd	92a1ea4642ae647c2a48cb8b31b14dde
		 * md5	ff06bc6545e840eca2e97f7da4afb973
		 *
		 *
		 update tb_ec_user set password='ae867f8923b2583d999092d93e15c478' where id='17679431837745a4841d1b4b9a3388c0';

		 update tb_ec_user_md5 set user_md5='5af6d01635094b8b8725be6cf7731b82' where user_id='17679431837745a4841d1b4b9a3388c0';

         */

	}

}
