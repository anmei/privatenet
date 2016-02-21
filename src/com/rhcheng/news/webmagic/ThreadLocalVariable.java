package com.rhcheng.news.webmagic;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
/**
 * get news content pagination
 * @author RhCheng
 * @date   2014-12-8
 */
public class ThreadLocalVariable {
	// crawl number,default is 1,when should get all the page url
	// 第几次抓取，默认为第一次，只有第一次抓取时才会获取分页url，并添加到TargetRequests
	private static ThreadLocal<Integer> f = new ThreadLocal<Integer>();
	// variable which store the news content 
	private static ThreadLocal<String> content = new ThreadLocal<String>();
	private NewsAbstract newsabs;
	private NewsDetails newsdet;
	// news entrance url
	private String url; 
	
	public ThreadLocalVariable(String firstUrl) {
		f.set(1);
		content.set("");
		newsdet = new NewsDetails();
		newsabs = SpiderEntrance.AllNewsByUrl.get(firstUrl);
	}

	public Integer getF() {
		return f.get();
	}

	public void setF(Integer fvalue) {
		f.set(fvalue);
	}

	public String getContent() {
		return content.get();
	}

	public void setContent(String contentv) {
		content.set(contentv);
	}
	
	public void fAddOne(){
		f.set(f.get()+1);
	}

	public NewsAbstract getNewsabs() {
		return newsabs;
	}

	public void setNewsabs(NewsAbstract newsabs) {
		this.newsabs = newsabs;
	}

	public NewsDetails getNewsdet() {
		return newsdet;
	}

	public void setNewsdet(NewsDetails newsdet) {
		this.newsdet = newsdet;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
