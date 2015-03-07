package com.rhcheng.user.formbean;

import com.rhcheng.common.PageFormBean;
/**
 * 用户模块接受页面参数类
 * @author Administrator
 *
 */
public class UserFormBean extends PageFormBean{
	private String name;
	private String sex;
	private Integer age;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
