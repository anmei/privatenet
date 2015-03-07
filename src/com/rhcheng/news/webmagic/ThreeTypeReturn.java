package com.rhcheng.news.webmagic;
/**
 * 泛型元组用于一个函数返回多个类型数值
 * @author RhCheng
 * @date   2014-12-1
 */
public class ThreeTypeReturn<A,B,C,D> {
	public final A AllNewsListByPageProcess;
	public final B AllNewsByUrl;
	public final C AllUrlsListByPageProcess;
	public final D AllNewsList;
	
	public ThreeTypeReturn(A allNewsListByPageProcess, B allNewsByUrl,C allUrlsListByPageProcess,D allNewsList){
		this.AllNewsListByPageProcess = allNewsListByPageProcess;
		this.AllNewsByUrl = allNewsByUrl;
		this.AllUrlsListByPageProcess = allUrlsListByPageProcess;
		this.AllNewsList = allNewsList;
	}
	
}
