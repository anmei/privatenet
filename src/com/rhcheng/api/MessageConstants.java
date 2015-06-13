package com.rhcheng.api;

/*******************************************************************************
 * 错误代码
 * 
 * @version 2013-07-01
 ******************************************************************************/
public interface MessageConstants {
	// 返回的结果标识
	public static final String RESULT_CODE = "rsCode";
	// 错误的参数请求
	public static final String ILLEGAL_PAR = "ILLEGAL_PAR";
	// 错误的参数签名
	public static final String ILLEGAL_SIGN = "ILLEGAL_SIGN";
	// 成功
	public static final String SUCCESS = "SUCCESS";
	// 成功
	public static final String FAIL = "FAIL";
	// 接口网络异常
	public static final String NETEXCEPTION = "NET_EXCEPTION";
	// 会员不存在
	public static final String MEMEBRNOTFOUND = "MEM_NOT_EXITS";
	// 密码不正确
	public static final String PWDNOTMATCH = "PWD_NOT_MATCH";
	//不存在
	public static final String NOTEXITS = "NOT_EXITS";
	//已过期
	public static final String EXPIRED = "EXPIRED";
}
