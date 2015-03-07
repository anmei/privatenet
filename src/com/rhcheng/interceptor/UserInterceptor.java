package com.rhcheng.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rhcheng.user.entity.User;


/**
 * 用户拦截器
 * 凡是需要对用户进行拦截，以地址以 /user/** 开头即可
 * @since 1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
@Component("userInterceptor")
public class UserInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String requestType = request.getHeader("X-Requested-With");
		// 对ajax的拦截
		if ("XMLHttpRequest".equals(requestType) || request.getRequestURI().contains("/login/")) {
			User user = (User) request.getSession().getAttribute("user");
        	if(user == null){
        		request.getRequestDispatcher("/test/noLogin.json").forward(request, response);
        		return false;
        	}else {
				return true;
			}
        // 对普通请求的拦截
        }else {
        	User user = (User) request.getSession().getAttribute("user");
        	if(user == null){
        		request.getRequestDispatcher("/test/noLoginCommon.action").forward(request, response);
        		return false;
        	}else {
				return true;
			}
		}
		
	}

}
