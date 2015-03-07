package com.rhcheng.common;


/**
 * 常量
 * @author RhCheng
 * @version 2013-11-01
 *
 */
public interface MyConstant {
	public static final String NO_USER ="nouser";//无此用户
	public static final String SUCCESS = "success";//成功
	public static final String ERROR = "error";//出错
	public static final String VALIDATECODE_ERROR = "validatecodeerror";//验证码出错
	public static final String PASSWORD_ERROR = "passerror";//验证码出错
	public static final String CATCHED_ERROR = "catchederror";//捕获的异常
	public static final int PAGESIZE = 2;//每页的记录数
	
	/*
	 * privatenet
	 */
	//oracle 数据库
	public static final int ORCLA_SEARCH = 1;
	//mysql  数据库
	public static final int MYSQL_SEARCH = 2;
	//当前使用的数据库
	public static final int CURRENT_SEARCH = MYSQL_SEARCH;
	/************************/
	// 原图片本地保存路径
	public static String UPLOAD_PATH_ORIGINAL = "upload_path_orininal";
	// 压缩后的图片本地保存路径,大小与原图相同，占用空间更小
	public static String UPLOAD_PATH_COMPRESSED = "upload_path_compressed";
	// 新闻列表中显示的图片本地保存路径
	public static String UPLOAD_PATH_LISTVIEW = "upload_path_listview";
	/************************/
	// 原图片本地访问路径,命名规则：absid[_n].png
	public static final String LOCAL_ORIGINAL_ACCESS = "/newsImages_original/";
	// 压缩后的图片本地访问路径,大小与原图相同，占用空间更小,命名规则：absid[_n].png
	public static final String LOCAL_COMPRESSED_ACCESS = "/newsImages_compressed/";
	// 新闻列表中显示的图片本地访问路径,命名规则：absid[_n].png
	public static final String LOCAL_LISTVIEW_ACCESS = "/newsImages_listview/";
	
	
	
	
//	// 原图片上传路径
//	public static final String UPLOAD_PATH_ORIGINAL = "D:\\myprogram\\tomcat7\\webapps\\privatenet\\newsImages_original\\";
//	// 压缩后的图片,大小与原图相同，占用空间更小
//	public static final String UPLOAD_PATH_COMPRESSED = "D:\\myprogram\\tomcat7\\webapps\\privatenet\\newsImages_compressed\\";
//	// 列表中显示的图片
//	public static final String UPLOAD_PATH_LISTVIEW = "D:\\myprogram\\tomcat7\\webapps\\privatenet\\newsImages_listview\\";
	
	
	
	
}
