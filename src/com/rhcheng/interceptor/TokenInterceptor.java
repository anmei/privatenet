package com.rhcheng.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;


/**
 * 重复提交拦截器
 * @author RhCheng
 * @date   2014-5-17
 * @since  1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			// 添加
			AddToken addToken = handlerMethod.getMethodAnnotation(AddToken.class);
			if(addToken != null) {
				WebUtils.setSessionAttribute(request, "token", UUID.randomUUID()
						.toString());
			}
			// 检查
			CheckToken checkToken = handlerMethod.getMethodAnnotation(CheckToken.class);
			// 需要检查
			if(checkToken != null) {
				// 是重复提交
				if(isRepeatSubmit(request)){
					System.out.println("重复提交");
					return false;
				}else{
					return true;
				}
				
			}
		}
		return true;
	}

	/**
	 * 检查是否重复提交，true->是，false-> 否
	 * @author RhCheng
	 * @date 2014-5-17
	 * @param request
	 * @return
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String serverToken = null;
		synchronized (session) {
			serverToken = (String) session
					.getAttribute("token");
			session.setAttribute("token", UUID.randomUUID()
					.toString());
		}
		String clientToken = request.getParameter("token");
		// 检查客户端是否含有token属性
		if (!StringUtils.isBlank(clientToken)) {
			// token相等或服务器token已销毁时，不存在重复提交
			if (StringUtils.equals(serverToken, clientToken)
					|| serverToken == null) {
				return false;
			}
			return true;
		}else{
			return true;
		}
		
	}

}
