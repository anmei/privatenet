package com.rhcheng.news.webmagic;

import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Spider;

import com.rhcheng.news.entity.EntranceProfile;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.util.string.NewsUtils;

public class ConcreateSpide {
	
	/**
	 * get all the news list, you can also add targeturl in every {@code PageProcessor} and get all the 
	 * content in different pages
	 * do crawl with multiple thread,multiple {@code Spider} and multiple {@code PageProcessor},
	 * note: one {@code Spider} instance corresponds to one {@code PageProcessor} instance
	 *  
	 * @author RhCheng
	 * @date 2014-12-1
	 * @param prolist all the url list to be crawled
	 * @param threadnum you can set the thread number
	 * @return all the usable info,including
	 * 		{@link SpiderEntrance#AllNewsList}
	 * 		{@link SpiderEntrance#AllNewsListByUrl}
	 * 		{@link SpiderEntrance#allUrls}
	 * 		 		 
	 */
	public ThreeTypeReturn<Map<String, List<NewsAbstract>>, Map<String, NewsAbstract>, 
				Map<String, List<String>>,List<NewsAbstract>> getAllNewsList(
			List<? extends EntranceProfile> prolist,Integer threadnum){
		
		CommonCrawl(prolist,threadnum);
		return new ThreeTypeReturn<Map<String, List<NewsAbstract>>, 
				Map<String, NewsAbstract>, 
				Map<String, List<String>>,
				List<NewsAbstract>>(
						SpiderEntrance.AllNewsListByPageProcess,SpiderEntrance.AllNewsByUrl,
						SpiderEntrance.AllUrlsListByPageProcess,SpiderEntrance.AllNewsList);
		
	}
	
	
	/**
	 * crawl the news content, you can add new url in every {@code PageProcessor},i.e. the method
	 * can get all the content in different page, if you have processed in {@code PageProcessor} instance
	 * 
	 * @author RhCheng
	 * @date 2014-12-1
	 * @param allNewsByUrl
	 * @param allNewsList
	 * @param threadnum
	 */
	public void getNewsContent(Map<String,NewsAbstract> allNewsByUrl,
			List<? extends EntranceProfile> allNewsList,
			Integer threadnum){
		SpiderEntrance.AllNewsDetailList.clear();
		SpiderEntrance spiderentrance = new SpiderEntrance(threadnum,allNewsList);
		SpiderEntrance.AllNewsByUrl = allNewsByUrl;
		spiderentrance.entrance();
	}
	
	
	/**
	 * common crawl method,can get all the content in different pages
	 * @author RhCheng
	 * @date 2014-12-1
	 * @param prolist the url list to be crawled,including charset and {@code PageProcessor} class name
	 * @param threadnum if {@code threadnum} is {@code null},default value is: 1
	 */
	public void CommonCrawl(List<? extends EntranceProfile> prolist,Integer threadnum){
		clearStatic();
		SpiderEntrance spiderentrance = new SpiderEntrance(threadnum,prolist);
		spiderentrance.entrance();
	}
		
	
	/**
	 * common crawl method, all the pages processed by the same {@code PageProcessor}
	 * @author RhCheng
	 * @date 2014-12-1
	 * @param threadnum
	 * @param url
	 * @param pageProcessor
	 * @param charset
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void CommonCrawl(Integer threadnum,List<String> url,String pageProcessor,String charset) 
			throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		BaseSpider bs = NewsUtils.getInstanceByName(pageProcessor);
		Spider sp = bs.getSpide(null);
		sp.thread(threadnum)
		.addUrl(url.toArray(new String[0]))
		.getSite().setCharset(charset);
		sp.run();
	}
	
		
	public static void clearStatic(){
		SpiderEntrance.AllNewsList.clear();
		SpiderEntrance.AllNewsListByPageProcess.clear();
		SpiderEntrance.AllUrlsListByPageProcess.clear();
		SpiderEntrance.AllNewsByUrl.clear();
		SpiderEntrance.AllNewsDetailList.clear();
	}
		
		
}
