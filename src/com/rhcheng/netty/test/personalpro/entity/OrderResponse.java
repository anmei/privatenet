package com.rhcheng.netty.test.personalpro.entity;

import java.io.Serializable;

public class OrderResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer resCode;
	private String resString;
	
	public Integer getResCode() {
		return resCode;
	}
	public void setResCode(Integer resCode) {
		this.resCode = resCode;
	}
	public String getResString() {
		return resString;
	}
	public void setResString(String resString) {
		this.resString = resString;
	}
	@Override
	public String toString() {
		return "OrderResponse [resCode=" + resCode + ", resString=" + resString
				+ "]";
	}
	
	
	
	
}
