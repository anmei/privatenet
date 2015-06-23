package com.rhcheng.netty.test.personalpro.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer oid;
	private String name;
	private float price;
	private Date date;
	
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", name=" + name + ", price=" + price
				+ ", date=" + date + "]";
	}
	
	
}
