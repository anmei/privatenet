package com.rhcheng.common;
/**
 * 系统常用常量
 * @author RhCheng
 * @date   2014-5-21
 * @since  1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class SysConstants {
	//项目根路径
	public static final String BASE = "base";// contextpath
	//图片根路径
	public static final String IMGBASE = "imgbase";
	//系统全路径
	public static final String FULLBASE = "fullbase";// fullcontextpath
	//系统编码
	public static final String CHARSET = "UTF-8";
	//系统配置文件的路径
	public static final String SYSCONFIG_PATH = "/properties/sysProperties.properties";
	//邮箱登录地址配置文件的路径
	public static final String EMAIL_LOGIN_URL_CONFIG_PATH = "/emailLoginUrl.properties";
	//邮件内容配置文件的路径
	public static final String EMAIL_CONTENT_CONFIG_PATH = "/emailContent.properties";
	//
	public static final String NEWS_PROPERTIES = "/properties/newsSources.properties";
	//随机字母
	public static final String LETTERS = "abcdefghijkmnopqrstuvwxyABCDEFGHIJKLMNPQRSTVUWXY23456789";
	//加密后缀
	public static final String SIGNKEY = "@i#p$o%*&s!~t";
	//加密用字符串
	public static final String SALT = "14fadNw5KIVh2SBP";
	//oracle 数据库
	public static final int ORCLA_SEARCH = 1;
	//mysql  数据库
	public static final int MYSQL_SEARCH = 2;
	/** 上传文件最大为30M*/   
	public static final Long fileMaxSize = 30000000L;   
    /** 允许上传的扩展名*/  
	public static final String [] extensionPermit = {"txt", "xls", "zip","jpg"};  
	/** 上传目录名，相对于context.getRealPath("/")*/  
    public static final String uploadPath = "uploadFiles/test/";  
    /** 上传临时文件存储目录,相对于context.getRealPath("/")*/  
    public static final String tempPath = "tempFiles/";  
    /** 统一的编码格式*/  
    public static final String encode = "UTF-8"; 
    
	//-------------------
	
	
	
	
}
