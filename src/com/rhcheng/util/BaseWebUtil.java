package com.rhcheng.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

public class BaseWebUtil extends WebUtils{

	public static String WEB_ROOT_PATH;

	public static String getRequestIP(HttpServletRequest request) {
		if (null != request) {
			String ip = request.getHeader("x-forwarded-for");
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
			return ip;
		}
		return null;
	}
	

	
	/**
	 * 移除session属性
	 * @author RhCheng
	 * @date 2014-4-15
	 * @param request
	 * @param name
	 */
	public static void removeSessionAttribute(HttpServletRequest request, String name){
		Assert.notNull(request, "Request must not be null");
		HttpSession session = request.getSession(false);
		if(session != null){
			session.removeAttribute(name);
		}
	}
	
	public static <T> T getBean(String beanName){
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("/configure/applicationContext.xml") ;
		return (T) beanFactory.getBean(beanName);
	}
	
	public static ServletContext getServletContext(){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();   
		return webApplicationContext.getServletContext();
	} 
	

	
}