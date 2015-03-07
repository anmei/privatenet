package com.rhcheng.news.service;

import java.util.List;

import com.rhcheng.common.PageFormBean;
import com.rhcheng.common.Pagination;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.entity.EntranceProfile;
import com.rhcheng.news.formbean.SearchForm;

public interface IDgService {
	/*void dotest();*/
	
	//--------------------------------------------------------------customize
	/**
	 * batch update the dgnews abstract list and 
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param args
	 * @return
	 */
	@Deprecated
	public void updateNewsOfDG(List<NewsAbstract> args,
			String sequenceName,String absTableName,String detailTableName);
	@Deprecated
	public void updateNewsOfYLtoutiao(List<NewsAbstract> args,
			String sequenceName,String absTableName,String detailTableName);
	

	
	//--------------------------------------------------------------common
	
	public Pagination<NewsAbstract> findNewsAbs(PageFormBean pf,String absTableName);
	public NewsDetails getNewsDetailByUrl(String url,String tableName);
	public NewsDetails getNewsDetailByAbsid(String absid,String tableName);
	public NewsAbstract getNewsabsByUrl(String url,String tableName);
	public NewsAbstract getNewsabsByabsid(String absid,String tableName);
	public Pagination<EntranceProfile> findProlist(SearchForm sf); 
	public void newsPersist(NewsAbstract newsabs,NewsDetails newsdet);
	public List<NewsAbstract> deleteOldNews(String tableName);
	public List<NewsAbstract> deleteDuplicate(String absTableName,String detTableName);
	public int resetSequence(String sequenceName);
	
	
	
}
