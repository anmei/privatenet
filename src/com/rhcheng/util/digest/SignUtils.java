package com.rhcheng.util.digest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import com.rhcheng.common.SysConstants;


/*******************************************************************************
 * 请求参数签名工具类
 * @version 2013-07-25
 ******************************************************************************/
public class SignUtils {

	/**
	 * 对request.getRequestParams()请求参数的转换
	 * 将key=String[] values类型转换成key=1,2,2类型
	 * @param params 请求的参数
	 * @return 验证结果
	 */
	public static Map<String, String> conversion(Map map) {
		if (null == map || map.size() <= 0) {
			return null;
		}
		Map<String, String> params = new HashMap<String, String>(map.size());
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) map.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}
	
	public static Map<String,String> string2map(String params){
		Map<String,String> res = new HashMap<String,String>();
		String[] tem = params.split("&");
		for(int i=0;i<tem.length;i++){
			if(StringUtils.isNotBlank(tem[i])){
				String[] tem2 = tem[i].split("=");
				res.put(tem2[0], tem2[1]);
			}
		}
		return res;
	}
	
	/**
	 * 将map类型请求参数组装成一个类似url请求的字符串参数
	 * 
	 * @param params 需要签名的请求参数
	 * @return 签名结果
	 */
	public static String map2string(Map<String,String >params){
		// 没有包含签名;或者签名值为空
		if (null == params || params.size() <= 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		List<String> kl = new ArrayList<String>(params.keySet());
		// 排序
		Collections.sort(kl);
		for (String key : kl) {
			String value = params.get(key);
			if (!StringUtils.equals(key, SysConstants.SIGNKEY)
					&& StringUtils.isNotBlank(value)) {
				sb.append(key).append("=").append(value).append("&");
			}
		}
		return sb.substring(0, sb.length()-1);
	}
	
	
	/**
	 * 签名字符串(接口系统提供给外的签名)
	 * 
	 * @param text
	 *            需要签名的字符串
	 * @return 签名结果
	 */
	public static String sign(String text) {
		return signParam(text, SysConstants.SIGNKEY);
	}
	
	/**
	 * 签名字符串
	 * 
	 * @param text
	 *            需要签名的字符串
	 * @return 签名结果
	 */
	public static String signParam(String text,String signKey) {
		if (StringUtils.isBlank(text)) {
			return null;
		}
		text = text + signKey;
		try {
			return DigestUtils.md5Hex(text.getBytes(SysConstants.CHARSET));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	/**
	 * 验证签名
	 * 
	 * @param params
	 *            请求的参数
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params) {
		String linkStr = map2string(params);
		if(StringUtils.isBlank(linkStr)){
			return false;
		}
		String sign = params.get(SysConstants.SIGNKEY);
		//System.out.println(sign(linkStr));
		return StringUtils.equals(sign(linkStr), sign);
	}
	
	/**
	 * 验证签名(万通公司专业)
	 * 
	 * @param params
	 *            请求的参数
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params,String wtKey) {
		String linkStr = map2string(params);
		if(StringUtils.isBlank(linkStr)){
			return false;
		}
		String sign = params.get(SysConstants.SIGNKEY);
		return StringUtils.equals(signParam(linkStr,wtKey), sign);
	}
	
	
	
	public static void main(String[] args) {
		String pwd = "654321";
		System.out.println(EncryptUitls.MD5Digest(pwd));
	}
	
}
