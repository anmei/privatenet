package com.rhcheng.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.rhcheng.common.MyConstant;
import com.rhcheng.common.SysConstants;
/**
 * 上下文监听器
 * @author RhCheng
 * @date   2014-5-21
 * @since  1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class BasePathListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("------->context had destroyed<-------");
		
	}

	/**
	 * 获取上下文根路径
	 * 参数放于context中
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("------>context inited<-------");
		String str = sce.getServletContext().getContextPath();
		// 上下文根路径
		sce.getServletContext().setAttribute(SysConstants.BASE, str);
		// 本地图片存储路径
		sce.getServletContext().setAttribute(MyConstant.UPLOAD_PATH_ORIGINAL,sce.getServletContext().getRealPath("/")+"newsImages_original/");
		sce.getServletContext().setAttribute(MyConstant.UPLOAD_PATH_COMPRESSED,sce.getServletContext().getRealPath("/")+"newsImages_compressed/");
		sce.getServletContext().setAttribute(MyConstant.UPLOAD_PATH_LISTVIEW,sce.getServletContext().getRealPath("/")+"newsImages_listview/");
		
		
	}

}
