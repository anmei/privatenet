package com.rhcheng.util.digest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.rhcheng.common.SysConstants;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 加密编码工具类
 * 
 * @author zengxiangtao
 * @since 1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class  EncryptUitls{

	private static Logger log = Logger.getLogger(EncryptUitls.class);
	protected EncryptUitls() {
		
	}

	/***************************************************************************
	 * MD5<br>
	 * 
	 * @param data
	 *            原文<br>
	 **************************************************************************/
	public static String MD5Digest(String str) {
		if(StringUtils.isBlank(str)){
			return null;
		}
		log.debug("----------->MD5Digest------------>Data:" + str);
		try {
			return DigestUtils.md5Hex(str.getBytes(SysConstants.CHARSET));
		} catch (Exception e) {
			log.error("--------------->MD5DigestException------------>msg:"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/***************************************************************************
	 * BASE64 编码<br>
	 * 
	 * @param data
	 **************************************************************************/
	public static String base64Encode(String data) {
		log.info("-------------------->base64Encode------------>Data:" + data);
		if (StringUtils.isBlank(data)) {
			return null;
		}
		try {
			String rs = (new BASE64Encoder()).encodeBuffer(data.getBytes());
			log.info("---------------------->base64Encode------------>Result:"
					+ rs);
			return rs;
		} catch (Exception e) {
			log.error("-------------------->base64Encode------------>Excetption:"
					+ e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	/***************************************************************************
	 * BASE64 解码<br>
	 * 
	 * @param data
	 **************************************************************************/
	public static String base64Decode(String key) {
		log.info("-------------------->base64Decode------------>Data:" + key);
		try {
			String rs = new String((new BASE64Decoder()).decodeBuffer(key));
			log.info("-------------------->base64Encode------------>Result:"
					+ rs);
			return rs;
		} catch (Exception e) {
			log.error("-------------------->base64Decode------------>Excetption:"
					+ e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 将字符串加密
	 * 
	 * @param str
	 *            待加密字符串
	 * @return 加密后的字符串，错误时返回null
	 */
	public String encode(String str) {
		String temp = EncryptUitls.base64Encode(str);
		if (temp != null) {
			int a = temp.length() % SysConstants.SALT.length();
			int b = temp.length() / SysConstants.SALT.length();
			String encoded = toOppositeCase(new StringBuilder(temp).insert(b,
					SysConstants.SALT.charAt(a)).toString());
			return encoded != null ? encoded.trim() : null;
		} else
			return null;
	}

	/**
	 * 还原加密的字符串
	 * 
	 * @param encodedStr
	 *            加密后的字符串
	 * @return 原字符串，密文有误时返回null
	 */
	public String decode(String encodedStr) {
		if (encodedStr == null || encodedStr.length() == 0)
			return "";
		String temp1 = toOppositeCase(encodedStr);
		int tempLength = temp1.length() - 1;
		int b = tempLength / SysConstants.SALT.length();
		String temp2 = temp1.substring(0, b)
				+ temp1.substring(b + 1, temp1.length());
		String temp3 = EncryptUitls.base64Decode(temp2);
		return temp3 != null ? temp3.trim() : temp3;
	}

	/**
	 * 将字符串中大写字母变为小写，小写变为大写
	 * 
	 * @param str
	 *            待转换字符串
	 * @return 转换后字符串
	 */
	private String toOppositeCase(String str) {
		if (str == null)
			return null;
		StringBuilder resultBuilder = new StringBuilder();
		char temp;
		for (int i = 0; i < str.length(); i++) {
			temp = str.charAt(i);
			if (Character.isUpperCase(temp))
				temp = Character.toLowerCase(temp);
			else if (Character.isLowerCase(temp))
				temp = Character.toUpperCase(temp);
			resultBuilder.append(temp);
		}
		return resultBuilder.toString();
	}
	
	
	/** 
    * 汉字转Unicode 
    * @param s 
    * @return 
    */  
   public static String gbEncoding(final String s){  
       String str = "";  
       for (int i = 0; i < s.length(); i++) {  
       int ch = (int) s.charAt(i);  
       str += "\\u" + Integer.toHexString(ch);  
       }  
       return str;  
   }  
   /** 
    * Unicode转汉字 
    * @param str 
    * @return 
    */  
   public static String encodingtoStr(String str){  
       Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");  
       Matcher matcher = pattern.matcher(str);  
       char ch;  
       while (matcher.find()) {  
       ch = (char) Integer.parseInt(matcher.group(2), 16);  
       str = str.replace(matcher.group(1), ch + "");  
       }  
       return str;  
   }  
	
	
	public static void main(String[] args) {
		/*System.out.println(EncryptUitls.base64Decode("<div style\="margin\:0 auto;border-left\:5px solid \#742a77;width\:500px;min-height\:450px;color\:\#777;font-family\:'Helvetica Neue',Helvetica,Arial,sans-serif;font-size\:13px;"><div style\="padding\:15px;"><img style\="width\:170px;height\:70px" src\="http\://192.183.3.207/ipost/resources/images/common/logo.png" alt\="\u7231\u90AE\u7F51"/></div><div style\="padding\:5px 25px;line-height\:25px;"><p>\u5C0A\u656C\u7684${username}\uFF1A</p><p style\="text-indent\:2em;line-height\:30px;">\u611F\u8C22\u60A8\u6CE8\u518C\u7231\u90AE\u7F51\uFF0C\u70B9\u51FB<a style\="color\:\#742a77" href\="${active_url}">\u8FD9\u91CC</a>\u5B8C\u6210\u6CE8\u518C\u3002\u5982\u679C\u4EE5\u4E0A\u94FE\u63A5\u65E0\u6548\uFF0C\u8BF7\u60A8\u5C06\u4EE5\u4E0B\u7F51\u5740\u590D\u5236\u5230\u6D4F\u89C8\u5668\u5730\u5740\u680F\u6253\u5F00\uFF1A</p><p>${active_url}</p><p>\u5982\u679C\u60A8\u6CA1\u6709\u6CE8\u518C\u8FC7\u7231\u90AE\u7F51\uFF0C\u53EF\u4EE5\u5FFD\u7565\u6B64\u90AE\u4EF6\u3002</p><p>\u7231\u90AE\u7F51\u56E2\u961F</p></div><div style\="color\:\#aaa;margin\:37px 25px 0 25px;padding-bottom\:5px;font-size\:12px;border-top\:1px solid \#aaa"><p>\u672C\u90AE\u4EF6\u7531\u7CFB\u7EDF\u81EA\u52A8\u53D1\u51FA\uFF0C\u8BF7\u52FF\u56DE\u590D\u3002</p><p>&reg;2014, \u7248\u6743\u6240\u6709 \u5E7F\u4E1C\u7701\u90AE\u653F\u516C\u53F8\u7231\u90AE\u7F51</p></div></div>");
		*/
		System.out.println(EncryptUitls.base64Encode("你好"));
		//07818b4815bbddbe7f1b3fbc5b1dc31f
	}
}
