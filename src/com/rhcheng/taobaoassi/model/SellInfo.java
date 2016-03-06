package com.rhcheng.taobaoassi.model;
// 销售属性
public class SellInfo {
	private String color;// 颜色
	private String size;// 尺码
	private Integer price;// 价格 单位:分
	private Integer number;// 数量
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
}
