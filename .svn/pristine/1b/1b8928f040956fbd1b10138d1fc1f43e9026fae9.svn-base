package com.rhcheng.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.rhcheng.common.SysConstants;

/*******************************************************************************
 * 系统全局过滤器
 * 
 * <pre>
 * 系统权限;全局变量等过滤
 * </pre>
 * 
 * @author zengxiangtao
 * @version 2013-07-01
 ******************************************************************************/
public class BasePathFilter implements Filter {

	/**
	 * Filter初始化
	 * 
	 * @author zengxiangtao
	 * 
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

	/**
	 * 过滤逻辑设计
	 * 
	 * @author zengxiangtao
	 * 参数放于每次请求的request中
	 */
	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		//HttpServletResponse response = (HttpServletResponse) sresponse;
		/*------项目路径----------*/
		String base = request.getContextPath();
		request.setAttribute(SysConstants.FULLBASE, request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+base);
		/*------执行链----------*/
		chain.doFilter(srequest, sresponse);
	}

	@Override
	public void destroy() {

	}
}