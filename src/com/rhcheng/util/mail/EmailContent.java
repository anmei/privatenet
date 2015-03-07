package com.rhcheng.util.mail;


import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.rhcheng.util.LoadProperties;

/**
 * 通过配置文件获取邮件内容
 * @author RhCheng
 * @date   2014-5-23
 * @since  1.0
 */
public class EmailContent {

	/**
	 * 根据名称和参数获取配置文件中的邮件内容。
	 * 
	 * @param name
	 *            邮件内容在properties文件中的key
	 * @param param
	 *            邮件内容中的参数${paramname}
	 * @return 
	 *         邮件内容，如果param不为空，将使用param中的值替换配置文件中的${paramname}参数，如map中的["text1","1"
	 *         ]将替换"${text1}text2"为"1text2"
	 */
	public static String getEmailContent(String name,String filename,Map<String, Object> param) {
		String content = LoadProperties.getPropertieByKey(name, filename);
		if (null == content)
			return "";
		if (param != null) {
			for (String key : param.keySet()) {
				content = content.replaceAll(getMatcher(key), param.get(key)
						.toString());
			}
		}
		return content;
	}

	/**
	 * 获取匹配参数的字符串
	 * 
	 * @param name
	 *            参数名
	 * @return 匹配${name}的字符串
	 */
	private static String getMatcher(String name) {
		return "\\$\\{*\\}".replace("*", name);
	}
	
	/**
	 * 根据用户id与注册时间生成邮件激活链接
	 * @author RhCheng
	 * @date 2014-5-23
	 * @param userid
	 * @param registTime
	 * @return
	 */
	public static String getActiveUrl(Long userid,Date registTime,HttpServletRequest request){
		
		return null;
		      
	}
	
	
}
