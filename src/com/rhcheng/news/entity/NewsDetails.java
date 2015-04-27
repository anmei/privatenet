package com.rhcheng.news.entity;

import java.util.Date;

/**
 * 
 * @author RhCheng
 * @date   2014-11-27
 */
public class NewsDetails {
	private String url;
	private String content;
	private String absidf;
	private Date date;
	
	//------------------
	private String tableName; // table name for news detail store
	private String title;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAbsidf() {
		return absidf;
	}
	public void setAbsidf(String absidf) {
		this.absidf = absidf;
	}
	
	
}
