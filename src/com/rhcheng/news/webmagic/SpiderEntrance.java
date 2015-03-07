package com.rhcheng.news.webmagic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import us.codecraft.webmagic.selector.thread.CountableThreadPool;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.EntranceProfile;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.util.string.NewsUtils;
/**
 * crawl in threadpool with multiple threads and multiple different PageProcessor
 * @author RhCheng
 * @date   2014-11-27
 */
public class SpiderEntrance {
//	public static int flag = 1;
	private int threadNum = 1;
	private List<? extends EntranceProfile> profielist = null;
	
	/**parameter shared in different thread*/
	public static List<NewsAbstract> AllNewsList = new ArrayList<NewsAbstract>();
	public static Map<String,List<NewsAbstract>> AllNewsListByPageProcess = new HashMap<String,List<NewsAbstract>>();
	public static Map<String,List<String>> AllUrlsListByPageProcess = new HashMap<String,List<String>>();
	public static Map<String,NewsAbstract> AllNewsByUrl = new HashMap<String,NewsAbstract>();
	public static List<NewsDetails> AllNewsDetailList = new ArrayList<NewsDetails>();
	
	
	public SpiderEntrance(){}
	public SpiderEntrance(Integer tnum,List<? extends EntranceProfile> list){
		this.threadNum = (tnum==null?1:tnum);
		this.profielist = list;
	}
	
	
	/**
	 * do spider with multiple threads and different {@PageProcessor} instance
	 * @author RhCheng
	 * @date 2014-11-27
	 */
	public void entrance() {
		if(profielist != null && profielist.size()>0){
			CountableThreadPool threadPool = new CountableThreadPool(threadNum);
			final CountDownLatch countDownLatch = new CountDownLatch(profielist.size());
			
			for(final EntranceProfile pro : profielist){
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							// every url will new a {@code Spider} instance, a {@code PageProcessor} instance 
							// and produce a new thread pool
							BaseSpider bs = NewsUtils.getInstanceByName(pro.getContentProcessClassName());
							bs.doSpide(pro.getContentCharset(),countDownLatch,pro.getUrl());
							
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						
					}
				});
					
			}
			
			try {
				countDownLatch.await();
			}catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				threadPool.shutdown();
				System.out.println("main thread completed! thread name is: "+Thread.currentThread().getName());
			}
			
		}
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public List<? extends EntranceProfile> getProfielist() {
		return profielist;
	}

	public void setProfielist(List<? extends EntranceProfile> profielist) {
		this.profielist = profielist;
	}
	
	
	
	
}
