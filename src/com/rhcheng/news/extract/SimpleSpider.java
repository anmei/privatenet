package com.rhcheng.news.extract;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


/**
 * 网页爬取抽象类
 * @author RhCheng
 * @param <T>
 * @date   2014-8-4
 */
public abstract class SimpleSpider<T> {
	/** 获取域名*/
//	protected abstract String getDomainName(String keyword);
	
	/** 域名*/
	public String url;
	public SimpleSpider(){}
	public SimpleSpider(String url) {
		this.url = url;
	}


	/** 获取该域名的document待处理*/
	public Document getDocument() throws IOException{
		Document doc = Jsoup.connect(this.getUrl())
				.data("jquery", "java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(60000)
				.get();
		
		System.out.println("连接成功……");
//		System.out.println(doc.toString());
		return doc;
	}
	
	
	public abstract List<T> findNewsList(String url) throws IOException;

	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
}
