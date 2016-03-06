package com.rhcheng.taobaoassi.model;

// 商品 主图片信息
public class ImgModel {
	private String imgNamemd5;// 图片名称在本地的md5值
	private String color;// 图片对应的颜色值
	private String originurl;//图片的源地址，非淘宝图片空间的地址
	
	public String getImgNamemd5() {
		return imgNamemd5;
	}
	public void setImgNamemd5(String imgNamemd5) {
		this.imgNamemd5 = imgNamemd5;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOriginurl() {
		return originurl;
	}
	public void setOriginurl(String originurl) {
		this.originurl = originurl;
	}
	
	
}
