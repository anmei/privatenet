package com.rhcheng.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 重复提交token检查
 * @author RhCheng
 * @date   2014-5-17
 * @since  1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckToken {
	
	
	
	/**
	 * token 注解,检查token
	 * @author flatychen
	 * @date 2014-4-14
	 * @return
	 */
	boolean check() default true;

}
