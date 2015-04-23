package com.rhcheng.news.webmagic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.util.string.NewsUtils;

/**
 * newslist process and update
 * single Thread invoke {@code doSpider}
 * @author RhCheng
 * @date   2014-9-25
 */
@Service("listProcess")
@Deprecated
public class NewsListProcessor {
	
	public static List<NewsAbstract> res = new ArrayList<NewsAbstract>();
	
	/**
	 * get all the legal news list
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param mapattr
	 * @throws IOException
	 */
	public  List<NewsAbstract> getAll(Map<String,String> mapattr){
		List<NewsAbstract> resli = new ArrayList<NewsAbstract>();
		for(Map.Entry<String, String> entry:mapattr.entrySet()){
			try {
				NewsUtils.getInstanceByName(entry.getValue().split("\\|")[0]).doSpide(entry.getValue().split("\\|")[1],entry.getKey());
				resli.addAll(res);
			} catch (InstantiationException e) {
				System.out.println("get instance error:"+entry.getValue().split("\\|")[0]);
			} catch (IllegalAccessException e) {
				System.out.println("get instance error:"+entry.getValue().split("\\|")[0]);
			} catch (ClassNotFoundException e) {
				System.out.println("get instance error:"+entry.getValue().split("\\|")[0]);
			}
		}
		
		
		
		return resli;
		
	}
	
	
	//--------------------------------------------------------------utils
	
	public static void main(String[] args){
		List<NewsAbstract> li = new NewsListProcessor().getAll(NewsUrls.URL_AND_PROCESSOR_DG);
		for(int i=0;i<li.size();i++){
			System.out.println(i+": "+li.get(i).getTitle());
		}
		
//		new DgNewsListProcessor().getSpide("gb2312","http://news.sun0769.com/dg/headnews/","http://news.sun0769.com/dg/sh/").run();
		
//		System.out.println(NewsListProcess.res.size());
//		for(int i=0;i<NewsListProcess.res.size();i++){
//			System.out.println(i+": "+NewsListProcess.res.get(i).getTitle());
//		}
//		DgPipeLing.res.clear();
		
	}
	
}
