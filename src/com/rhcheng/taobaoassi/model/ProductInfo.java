package com.rhcheng.taobaoassi.model;

import java.util.Date;
// 本地数据库中的商品信息
public class ProductInfo {
	private Integer category;//　商品类别　１女装　２女鞋　３童装　４童鞋　５家居　６其他
	private String url;// 商品详情url
	private Date gmtcreate; // 商品添加时间
	private Integer num;// 商品数量
	private String name;// 宝贝名称
	private String originname;// 宝贝原始名称
	
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getGmtcreate() {
		return gmtcreate;
	}
	public void setGmtcreate(Date gmtcreate) {
		this.gmtcreate = gmtcreate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginname() {
		return originname;
	}
	public void setOriginname(String originName) {
		this.originname = originName;
	}
	
	
}
