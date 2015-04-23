package com.rhcheng.news.webmagic;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.rhcheng.news.dao.DgDao;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.util.string.NewsUtils;
/**
 * news content process and update
 * @author RhCheng
 * @date   2014-9-26
 */
@Service("contentProcess")
@Deprecated
public class NewsContentProcessor {
	
	public static String content = "";
	
	@Resource
	private DgDao dgDao;
	
	
	
	public void newsPersis(NewsAbstract newsabs,NewsDetails newsdet){
		if(newsabs.getDate()!=null && NewsUtils.ifok(newsabs.getDate())){
			this.processAndSave(newsabs,newsdet);
		}	
		
	}
	
	
	
	
	/**
	 * mainly get imgpath, publish date and content
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param allnewslist news list
	 * @param absTableName table name which store news abstract 
	 * @param detailTableName table name which store news details
	 */
	@Deprecated
	public void contentProcessAndPersis(List<NewsAbstract> allnewslist,String absTableName,String detailTableName){
		for(NewsAbstract li:allnewslist){
			if(li.getDate()!=null && NewsUtils.ifok(li.getDate())){
				BaseSpider bsp;
				try {
					bsp = NewsUtils.getInstanceByName(li.getContentProcessClassName());
					bsp.doSpide(li.getContentCharset(), li.getUrl());
					//content process
					this.processAndSave(bsp, li, absTableName, detailTableName);
					
				} catch (InstantiationException e) {
					System.out.println("get instance error:"+li.getContentProcessClassName());
				} catch (IllegalAccessException e) {
					System.out.println("get instance error:"+li.getContentProcessClassName());
				} catch (ClassNotFoundException e) {
					System.out.println("get instance error:"+li.getContentProcessClassName());
				}
				
				
			}else if(li.getDate()==null){// get content and judge date
				BaseSpider bsp;
				try {
					bsp = NewsUtils.getInstanceByName(li.getContentProcessClassName());
					bsp.doSpide(li.getContentCharset(), li.getUrl());
					//content process
					if(!bsp.ifnewest(bsp.getPublishDate(bsp.getOriginalDateOfNewsDetail(content)))){
						continue;
					}else{
						li.setOriginalDate(bsp.getOriginalDateOfNewsDetail(content));
						li.setDate(bsp.getPublishDate(li.getOriginalDate()));
						this.processAndSave(bsp, li, absTableName, detailTableName);
					}
					
				} catch (InstantiationException e) {
					System.out.println("get instance error:"+li.getContentProcessClassName());
				} catch (IllegalAccessException e) {
					System.out.println("get instance error:"+li.getContentProcessClassName());
				} catch (ClassNotFoundException e) {
					System.out.println("get instance error:"+li.getContentProcessClassName());
				}
				
				
				
			}	
		}
		System.out.println("update ok.");
		
	}
	
	@Deprecated
	private void processAndSave(BaseSpider bs,NewsAbstract newslist,String absTableName,String detailTableName){
		if(StringUtils.isNotBlank(content)){
			String temurl="";
			newslist.setImgPath(bs.getImgPath(newslist.getUrl(), content));
			temurl = newslist.getUrl().replace("?", "c");
			temurl = temurl.replace("&", "r");
			newslist.setUrl(temurl);
			dgDao.addNewsAbs(newslist, absTableName);
			dgDao.addDetail("",newslist.getUrl(), content,newslist.getDate(),detailTableName);
		}
	}
	
	private void processAndSave(NewsAbstract newsabs,NewsDetails newsdet){
		if(StringUtils.isNotBlank(newsdet.getContent())){
			String temurl="";
			temurl = newsabs.getUrl().replace("?", "c");
			temurl = temurl.replace("&", "r");
			newsabs.setUrl(temurl);
			dgDao.addNewsAbs(newsabs, newsabs.getTableName());
			dgDao.addDetail("",newsabs.getUrl(), newsdet.getContent(),newsabs.getDate(),newsdet.getTableName());
		}
	}
	
	

}
