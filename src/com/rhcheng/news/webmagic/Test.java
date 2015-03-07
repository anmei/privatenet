package com.rhcheng.news.webmagic;

import com.rhcheng.news.webmagic.manage.UpdateNews;
import com.rhcheng.util.BaseWebUtil;

public class Test {

	
	public static void main(String[] args){
		UpdateNews updateNews = (UpdateNews) BaseWebUtil.getBean("updateNews");
		
//		updateNews.resetSequence();
		
		updateNews.updateNewslistAndDetail(1,4,10,10,
				null,
				null);
		
		
		
	}
		
		
		
		
		
		
		
		
		
//		ConcreateSpide concreatespider = new ConcreateSpide();
//		Test test = new Test();
//		SearchForm sf = new SearchForm();
////		sf.setUrl("http://news.sun0769.com/");
//		List<EntranceProfile> prolist = test.dgservice.findProlist(sf);
		//------------
		
		
		// 多线程处理新闻列表
//		ThreeTypeReturn<Map<String, List<NewsAbstract>>, Map<String, NewsAbstract>, 
//			Map<String, List<String>>,List<NewsAbstract>> newslistres = concreatespider.getAllNewsList(prolist, 2);
		
		
		/**根据新闻标题进行新闻去重*/
//		....

		
//		System.out.println(newslistres.AllNewsList.size());
//		for(NewsAbstract news:newslistres.AllNewsList){
//			System.out.println(news.getUrl());
//		}
//		
//		
//		// 多线程处理新闻正文
//		int k = 10; // interval
//		List<? extends EntranceProfile> temlist = new ArrayList();
//		
//		for(int i=0;i<newslistres.AllNewsList.size();i=(i+k)>newslistres.AllNewsList.size()?newslistres.AllNewsList.size():(i+k)){
//			
//			temlist = newslistres.AllNewsList.subList(i, (i+k)>newslistres.AllNewsList.size()?newslistres.AllNewsList.size():(i+k));
//			System.out.println(temlist.size());
//			concreatespider.getNewsContent(newslistres.AllNewsByUrl,temlist,4);
//			
//			/**持久化新闻内容,向数据库写入新闻正文以及新闻概要*/
//			NewsDetails newsdetail;
//			for(int j=0;j<SpiderEntrance.AllNewsDetailList.size();j++){
//				newsdetail = SpiderEntrance.AllNewsDetailList.get(j);
//				test.dgservice.newsPersist(SpiderEntrance.AllNewsByUrl.get(newsdetail.getUrl()),newsdetail);
//				
//			}
//			
//		}
//		concreatespider.clearStatic();
		
		

//		cconcreatespi.getNewsContent(newslistres.AllNewsByUrl,newslistres.AllNewsList,2);
//		/**持久化新闻内容,向数据库写入新闻正文以及新闻概要*/
//		System.out.println(SpiderEntrance.AllNewsDetailList.size());
//		for(int j=0;j<SpiderEntrance.AllNewsDetailList.size();j++){
//			newsdetail = SpiderEntrance.AllNewsDetailList.get(j);
//			System.out.println(newsdetail.getUrl());
//			test.dgservice.newsPersis(SpiderEntrance.AllNewsByUrl.get(newsdetail.getUrl()), 
//					newsdetail);
//			
//		}
		
		
//		for(Entry<String,List<NewsAbstract>> na:newslistres.allNewsList.entrySet()){
//			for(NewsAbstract n:na.getValue()){
//				System.out.println(n.getTitle()+" "+na.getKey());
//			}
//		}
		

	
}
