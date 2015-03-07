package com.rhcheng.news.webmagic.manage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.rhcheng.common.Pagination;
import com.rhcheng.news.entity.EntranceProfile;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.formbean.SearchForm;
import com.rhcheng.news.imgproc.DefaultImageProcess;
import com.rhcheng.news.service.IDgService;
import com.rhcheng.news.webmagic.ConcreateSpide;
import com.rhcheng.news.webmagic.SpiderEntrance;
import com.rhcheng.news.webmagic.ThreeTypeReturn;
import com.rhcheng.util.string.NewsUtils;

@Component("updateNews")
public class UpdateNews{
	@Resource
	private IDgService dgservice;
	
	
	/*public void testMysql(){
		dgservice.dotest();
	}*/
	
	
	/**
	 * update every 5 hours
	 * @author RhCheng
	 * @date 2014-12-4
	 */
	public void doUpdate(){
		this.updateNewslistAndDetail(1,4,10,10,
				null,
				null);
	}
	
	/**
	 * reset table sequence every day 23：:45
	 * @author RhCheng
	 * @date 2014-12-10
	 */
	public void resetSequence(){
		List<EntranceProfile> prolist = dgservice.findProlist(new SearchForm()).getObjLists();
		List<EntranceProfile> profilelist = new ArrayList<EntranceProfile>();
		int f = 1;
		for(EntranceProfile ef:prolist){
			f=1;
			for(EntranceProfile pl:profilelist){
				if(pl.getAbstable().equalsIgnoreCase(ef.getAbstable())){
					f=0;break;
				}
			}
			if(f==1){
				profilelist.add(ef);
				dgservice.resetSequence(ef.getAbstable()+"id");
			}
		}
	}
	
	//-------------------------------------------------------------------------------------
	
	/**
	 * 
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param listThreadNum thread num used when get news list 
	 * @param detailThreadNum thread num used when get news detail
	 * @param persistInterval the news amount every time connect database and add to database  
	 * @param profilePagesize get profilelist's size every time
	 * @param notIncludUrl urls which will not crawl
	 * @param justIncludeUrl just only crawl these urls
	 */
	public void updateNewslistAndDetail(int listThreadNum,int detailThreadNum,int persistInterval,int profilePagesize,
			String[] notIncludUrl,String[] justIncludUrl){
		Long st = System.currentTimeMillis();
		
		//=====================update===================
		SearchForm sf = new SearchForm();
		sf.setPageSize(profilePagesize);
		sf.setToPage(1);
		Pagination<EntranceProfile> propagination = dgservice.findProlist(sf);
		List<EntranceProfile> prolist = propagination.getObjLists();
		/**获取并持久化新数据*/
		for(int j=1;j<=propagination.getTotalPage();j++){
			if(j>1){
				sf.setToPage(j);
				prolist = dgservice.findProlist(sf).getObjLists();
			}
			prolist = filteUrlOfPro(notIncludUrl, justIncludUrl, prolist);
			crawlAndPersistent(prolist, listThreadNum, detailThreadNum, persistInterval);
		}
		//=========delete and remove duplicate=========
		this.deleteOldAndRemoveDup(notIncludUrl, justIncludUrl);
		
		System.out.println("all news update finished! waste time: "+NewsUtils.formatFromMillSecond(System.currentTimeMillis()-st));
		
	}
	
	
	public void deleteOldAndRemoveDup(String[] notIncludUrl,String[] justIncludUrl){
		List<EntranceProfile> prolist = dgservice.findProlist(new SearchForm()).getObjLists();
		List<String> tableNames = filteUrl(notIncludUrl, justIncludUrl, prolist);
		for(int i = 0;i<tableNames.size();i++){
			List<NewsAbstract> imguuids = dgservice.deleteOldNews(tableNames.get(i));
			// 删除本地图片
			for(NewsAbstract nabs:imguuids){
				System.out.println("old news------------->"+nabs.getImgUuid());
				DefaultImageProcess.deleteLocalImg(nabs.getImgUuid());
			}
		}
		System.out.println("delete old content and images completed.");
		//-----
		this.removeDupData(prolist);
	}
	
	
	
	/**
	 * filte table name which will be delete
	 * @author RhCheng
	 * @date 2014-12-8
	 * @param notIncludUrl
	 * @param justIncludUrl
	 * @param prolist
	 * @return
	 */
	public List<String> filteUrl(String[] notIncludUrl,String[] justIncludUrl,List<EntranceProfile> prolist){
		List<String> tableNames = new ArrayList<String>();
		if(justIncludUrl != null){
			for(EntranceProfile ef:prolist){
				for(int i=0;i<justIncludUrl.length;i++){
					if(ef.getUrl().equalsIgnoreCase(justIncludUrl[i]) && !tableNames.contains(ef.getAbstable())){
						tableNames.add(ef.getAbstable());
						tableNames.add(ef.getDetailtable());
					}
				}			
			}
		}else if(notIncludUrl != null){
			int flg = 1;
			for(EntranceProfile ef:prolist){
				flg = 1;
				for(int i=0;i<notIncludUrl.length;i++){
					if(notIncludUrl[i].equalsIgnoreCase(ef.getUrl())){
						flg = 0;
						break;
					}
				}
				if(flg == 1 && !tableNames.contains(ef.getAbstable())){
					tableNames.add(ef.getAbstable());
					tableNames.add(ef.getDetailtable());
				}
			}
		}else {
			for(EntranceProfile ef:prolist){
				if(!tableNames.contains(ef.getAbstable())){
					tableNames.add(ef.getAbstable());
					tableNames.add(ef.getDetailtable());
				}
			}
		}
		return tableNames;
	}
	
	/**
	 * filte {@code EntranceProfile} which will be crawled
	 * @author RhCheng
	 * @date 2014-12-8
	 * @param notIncludUrl
	 * @param justIncludUrl
	 * @param prolist
	 * @return
	 */
	public List<EntranceProfile> filteUrlOfPro(String[] notIncludUrl,String[] justIncludUrl,List<EntranceProfile> prolist){
		List<EntranceProfile> profilelist = new ArrayList<EntranceProfile>();
		if(justIncludUrl != null){
			for(EntranceProfile ef:prolist){
				for(int i=0;i<justIncludUrl.length;i++){
					if(ef.getUrl().equalsIgnoreCase(justIncludUrl[i])){
						profilelist.add(ef);
					}
				}			
			}
		}else if(notIncludUrl != null){
			int flg = 1;
			for(EntranceProfile ef:prolist){
				flg = 1;
				for(int i=0;i<notIncludUrl.length;i++){
					if(notIncludUrl[i].equalsIgnoreCase(ef.getUrl())){
						flg = 0;
						break;
					}
				}
				if(flg == 1){
					profilelist.add(ef);
				}
			}
		}else {
			profilelist=prolist;
		}
		return profilelist;
	}
	
	/**
	 * remove duplicate data
	 * @author RhCheng
	 * @date 2014-12-8
	 * @param notIncludUrl
	 * @param justIncludUrl
	 * @param prolist
	 * @return
	 */
	public void removeDupData(List<EntranceProfile> prolist){
		List<EntranceProfile> profilelist = new ArrayList<EntranceProfile>();
		int f = 1;
		for(EntranceProfile ef:prolist){
			f=1;
			for(EntranceProfile pl:profilelist){
				if(pl.getAbstable().equalsIgnoreCase(ef.getAbstable())){
					f=0;break;
				}
			}
			if(f==1){
				profilelist.add(ef);
				List<NewsAbstract> res = dgservice.deleteDuplicate(ef.getAbstable(), ef.getDetailtable());
				for(NewsAbstract nabs:res){
					DefaultImageProcess.deleteLocalImg(nabs.getImgUuid());
				}
			}
		}
		System.out.println("delete duplicate content and images completed.");
	}
	
	
	public void crawlAndPersistent(List<EntranceProfile> prolist,int listThreadNum,int detailThreadNum,int persistInterval){
		/**core method**/
		ThreeTypeReturn<Map<String, List<NewsAbstract>>, Map<String, NewsAbstract>, 
			Map<String, List<String>>,List<NewsAbstract>> newsresult = this.updateNewslist(listThreadNum, prolist);
		
		/**根据新闻概要信息对所有的新闻列表{@code AllNewsList}进行适当去重*/
		/*....*/
		
//		for(EntranceProfile pro:prolist){
//			System.out.println(pro.getUrl());
//		}
//		for(Entry<String, NewsAbstract> ent:SpiderEntrance.AllNewsByUrl.entrySet()){
//			System.out.println(ent.getKey());
//		}
//		System.out.println("============="+SpiderEntrance.AllNewsByUrl.size());
//		for(NewsAbstract na:SpiderEntrance.AllNewsList){
//			System.out.println(na.getUrl());
//		}
//		System.out.println(SpiderEntrance.AllNewsList.size());
		
		
		
		this.updateNewsDetail(detailThreadNum, persistInterval, newsresult);
		/***************/
	}
	
	
	/**
	 * update news list
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param thnum thread number
	 * @param prolist the newslist entrance
	 * @return
	 */
	public ThreeTypeReturn<Map<String, List<NewsAbstract>>, Map<String, NewsAbstract>, 
			Map<String, List<String>>,List<NewsAbstract>> updateNewslist(int thnum,List<EntranceProfile> prolist){
		/**多线程处理新闻列表*/
		ConcreateSpide concreatespider = new ConcreateSpide();
		ThreeTypeReturn<Map<String, List<NewsAbstract>>, Map<String, NewsAbstract>, 
			Map<String, List<String>>,List<NewsAbstract>> newslistres = concreatespider.getAllNewsList(prolist, thnum);
		
		System.out.println("news list update finished,size is: "+newslistres.AllNewsList.size());
		return newslistres;
		
	}
	
	/**
	 * update news content
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param thnum thread num
	 * @param interval data persistent interval
	 * @param newslistres result of {@code UpdateNews#updateNewslist}
	 */
	public void updateNewsDetail(int thnum,int interval,ThreeTypeReturn<Map<String, List<NewsAbstract>>, 
			Map<String, NewsAbstract>, Map<String, List<String>>,List<NewsAbstract>> newslistres){
		/**多线程处理新闻正文*/
		int k = interval;
		int g = 0;
		List<? extends EntranceProfile> temlist = new ArrayList();
		ConcreateSpide concreatespider = new ConcreateSpide();
		
		for(int i=0;i<newslistres.AllNewsList.size();i=(i+k)>newslistres.AllNewsList.size()?newslistres.AllNewsList.size():(i+k)){
			temlist = newslistres.AllNewsList.subList(i, (i+k)>newslistres.AllNewsList.size()?newslistres.AllNewsList.size():(i+k));
			concreatespider.getNewsContent(newslistres.AllNewsByUrl,temlist,thnum);
			
			/**持久化新闻内容,向数据库写入新闻正文以及新闻概要*/
			NewsDetails newsdetail;
			for(int j=0;j<SpiderEntrance.AllNewsDetailList.size();j++){
				newsdetail = SpiderEntrance.AllNewsDetailList.get(j);
				dgservice.newsPersist(SpiderEntrance.AllNewsByUrl.get(newsdetail.getUrl()),newsdetail);
				g++;
			}
			
		}
		concreatespider.clearStatic();
		System.out.println("news detail update finished, size is: "+g);
	}

	
		
}
