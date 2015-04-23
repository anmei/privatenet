package com.rhcheng.news.entity;

/**
 * profile about page to spider
 * @author RhCheng
 * @date   2014-11-27
 */
public class EntranceProfile {
	private String url;  					// 新闻链接
	private String contentCharset;			//charset for content process
	private String contentProcessClassName;	//the class name for content process
	private String remark;
	private String abstable; // 存储改新闻概要的表名
	private String detailtable;// 存储该新闻内容的表名
	
	
	public String getAbstable() {
		return abstable;
	}
	public void setAbstable(String abstable) {
		this.abstable = abstable;
	}
	public String getDetailtable() {
		return detailtable;
	}
	public void setDetailtable(String detailtable) {
		this.detailtable = detailtable;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContentCharset() {
		return contentCharset;
	}
	public void setContentCharset(String contentCharset) {
		this.contentCharset = contentCharset;
	}
	public String getContentProcessClassName() {
		return contentProcessClassName;
	}
	public void setContentProcessClassName(String contentProcessClassName) {
		this.contentProcessClassName = contentProcessClassName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
