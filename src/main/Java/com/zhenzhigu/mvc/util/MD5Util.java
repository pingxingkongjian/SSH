package com.zhenzhigu.mvc.util;

import java.security.MessageDigest;
/**
 * MD5不可逆加密
 * @author Master.Xia
 *
 */
public class MD5Util {

	/**
	 * 对字符串进行MD5加密，得到长度为32的小写密文
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	public static void main(String[] args) {
		System.out.println(md5("123456"));
		System.out.println(md5("asdfalkjdsflj"));
	}
}
