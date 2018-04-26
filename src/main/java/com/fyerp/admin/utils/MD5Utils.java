/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：MD5Utils.java
 * 作者：xuda
 * 时间：18-4-11 上午11:47
 *
 */

package com.fyerp.admin.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	private static final String SALT = "tamboo";

	public static String encode(String password) {
		password = password + SALT;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		char[] charArray = password.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}

			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static void main(String[] args) {
		System.out.println(MD5Utils.encode("abel"));


	}
}