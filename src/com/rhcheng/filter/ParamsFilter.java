/**
 * 
 */
package com.rhcheng.filter;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


import org.slf4j.Logger;
//import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 请求参数过滤器
 * @author RhCheng
 * @date   2015年6月12日
 */
public class ParamsFilter implements Filter{
//	private static Logger logger = Logger.getLogger(ParamsFilter.class);
	private static Logger slflogger = LoggerFactory.getLogger(ParamsFilter.class);
	
	/**
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 * @author ChdYan
	 * @date 2015-5-13
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @author ChdYan
	 * @date 2015-5-13
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest srequest = (HttpServletRequest) request;
		String url = srequest.getRequestURL().toString();
		if(!url.endsWith(".jsp") && !url.endsWith(".css") && !url.endsWith(".js") && !url.endsWith(".jpg") && !url.endsWith(".png")) {
			StringBuilder builder = new StringBuilder("\n==================请求=================\n");
			builder.append("request_url=").append(url).append("\n");
			builder.append("query_params=").append(srequest.getQueryString()).append("\n");
			builder.append("---------------post参数--------------\n");
			Map<String,String[]> args = srequest.getParameterMap();
			for(Entry<String, String[]> entry : args.entrySet()) {
				builder.append(entry.getKey()).append("=");
				for(int j=0;j<entry.getValue().length;j++){
					builder.append(j<entry.getValue().length-1?entry.getValue()[j]+",":entry.getValue()[j]);
				}
				builder.append("\n");
			}
//			logger.info(builder.toString());
			slflogger.info(builder.toString());
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 * @author ChdYan
	 * @date 2015-5-13
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
