package com.umpay.rms.gpd.user.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: SHA1DigestUtil
 * @Description: 一平台同步数据的密码加密方式
 * @author: huzhenxing
 * @date: 2017年5月23日 下午6:09:41
 */
public class SHA1DigestUtil {

	private static final Logger logger = LoggerFactory.getLogger( SHA1DigestUtil.class );

	public static String encryptToSHA( String password ) {
		byte[] digesta = null;
		String result = null;
		try {
			// 得到一个MD5的消息摘要
			MessageDigest mdi = MessageDigest.getInstance( "SHA-1" );
			// 添加要进行计算摘要的信息
			mdi.update( password.getBytes( ) );
			// 得到该摘要
			digesta = mdi.digest( );
			result = byteToHex( digesta );
		} catch( NoSuchAlgorithmException e ) {
			logger.error( "无此加密方式：", e );
		}
		return result;
	}


	public static String byteToHex( byte[] pwd ) {
		StringBuilder hs = new StringBuilder( "" );
		String temp = "";
		for( int i = 0; i < pwd.length; i++ ) {
			temp = Integer.toHexString( pwd[i] & 0XFF );
			if( temp.length( ) == 1 ) {
				hs.append( "0" ).append( temp );
			} else {
				hs.append( temp );
			}
		}
		return hs.toString( ).toUpperCase( );
	}
	
	public static void main( String[] args ) {
		String digestPass = encryptToSHA( "admin" );
		System.out.println(digestPass);
	}
}
