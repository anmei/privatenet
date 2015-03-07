package com.rhcheng.news.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import com.rhcheng.common.MyConstant;
import com.rhcheng.common.PageFormBean;
import com.rhcheng.common.Pagination;
import com.rhcheng.news.ESun0769;
import com.rhcheng.news.TouTiao;
import com.rhcheng.news.dao.DgDao;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.entity.EntranceProfile;
import com.rhcheng.news.extract.TextExtract;
import com.rhcheng.news.formbean.SearchForm;
import com.rhcheng.util.BaseWebUtil;
import com.rhcheng.util.string.NewsUtils;

@Service("dgService")
public class DGService implements IDgService{
	@Resource
	private DgDao dgDao;
	
	/*public void dotest(){
		dgDao.dotest();
	}*/
	
	
	//--------------------------------------------------------------------update news
	

	@Override
	@Deprecated
	public void updateNewsOfDG(List<NewsAbstract> args,
			String sequenceName,String absTableName,String detailTableName) {
		Document doc =new Document("");
		String url="",temurl="",content="";
		//category
		dgDao.resetSequence(sequenceName);
		dgDao.deleteNewsAbs(1L, (long)(args.size()/2),absTableName);
		for(int i=0;i<args.size()/2;i++){
			
			url = args.get(i).getUrl();
			temurl = url.replace("?", "c");
			temurl = temurl.replace("&", "r");
			
			// get detail
			if(dgDao.getNewsDetail(temurl,detailTableName)<=0){
				try {
					doc = TextExtract.getDocument(url);
					args.get(i).setDate(ESun0769.getDateOfDg(doc));
					content = ESun0769.imgRelativeToAbsolute(url, doc);
					args.get(i).setImgPath(ESun0769.getImgList(url, doc));
				} catch (IOException e) {
					System.out.println("404 error---"+url);
				}
			
				dgDao.addDetail("",temurl, content,args.get(i).getDate(),detailTableName);
			}
			args.get(i).setUrl(temurl);
			dgDao.updateNewsAbs(args.get(i),absTableName,sequenceName);
		}
		
		
		dgDao.deleteNewsAbs((long)(args.size()/2)+1, null,absTableName);
		for(int i=args.size()/2;i<args.size();i++){
			url = args.get(i).getUrl();
			temurl = url.replace("?", "c");
			temurl = temurl.replace("&", "r");
			
			// get detail
			if(dgDao.getNewsDetail(temurl,detailTableName)<=0){
				try {
					doc = TextExtract.getDocument(url);
					args.get(i).setDate(ESun0769.getDateOfDg(doc));
					content = ESun0769.imgRelativeToAbsolute(url, doc);
					args.get(i).setImgPath(ESun0769.getImgList(url, doc));
				} catch (IOException e) {
					System.out.println("404 error---"+url);
				}
			
				dgDao.addDetail("",temurl, content,args.get(i).getDate(),detailTableName);
			}
			args.get(i).setUrl(temurl);
			dgDao.updateNewsAbs(args.get(i),absTableName,sequenceName);
		}
		
		dgDao.deleteDetailNotInAbs(absTableName,detailTableName);
		
		
		
	}


	@Override
	@Deprecated
	public void updateNewsOfYLtoutiao(List<NewsAbstract> args,
			String sequenceName, String absTableName, String detailTableName){
		Document doc = new Document("");
		String url="",temurl="",content="";
		//category
		dgDao.resetSequence(sequenceName);
		dgDao.deleteNewsAbs(1L, (long)(args.size()/2),absTableName);
		for(int i=0;i<args.size()/2;i++){
			
			url = args.get(i).getUrl();
			temurl = url.replace("?", "c");
			temurl = temurl.replace("&", "r");
			
			// get detail
			if(dgDao.getNewsDetail(temurl,detailTableName)<=0){
				try {
					doc = TextExtract.getDocument(url);
				} catch (IOException e) {
					System.out.println("404 error---"+url);
				}
				content = TextExtract.parse(doc.toString(), url);
				//content img process
				content = TouTiao.makeImgAbsolute(content,Arrays.asList(args.get(i).getImgPath().split("\\|")));
				
				dgDao.addDetail("",temurl, content,args.get(i).getDate(),detailTableName);
			}
			
			args.get(i).setUrl(temurl);
			dgDao.updateNewsAbs(args.get(i),absTableName,sequenceName);
		}
		
		
		dgDao.deleteNewsAbs((long)(args.size()/2)+1, null,absTableName);
		for(int i=args.size()/2;i<args.size();i++){
			url = args.get(i).getUrl();
			temurl = url.replace("?", "c");
			temurl = temurl.replace("&", "r");
			
			// get detail
			if(dgDao.getNewsDetail(temurl,detailTableName)<=0){
				try {
					doc = TextExtract.getDocument(url);
				} catch (IOException e) {
					System.out.println("404 error---"+url);
				}
				content = TextExtract.parse(doc.toString(), url);
				//content img process
				content = TouTiao.makeImgAbsolute(content,Arrays.asList(args.get(i).getImgPath().split("\\|")));
				
				dgDao.addDetail("",temurl, content,args.get(i).getDate(),detailTableName);
			}
			
			args.get(i).setUrl(temurl);
			dgDao.updateNewsAbs(args.get(i),absTableName,sequenceName);
		}
		
		dgDao.deleteDetailNotInAbs(absTableName,detailTableName);
		
		
		
	}
	
	


	//-------------------------------------------------------------------common

	@Override
	public Pagination<NewsAbstract> findNewsAbs(PageFormBean pf,String absTableName) {
		Pagination<NewsAbstract> newslist = dgDao.findNewsAbs(pf,absTableName);
		for(NewsAbstract news:newslist.getObjLists()){
			if(StringUtils.isNotBlank(news.getImgUuid())){
				news.setImglist(NewsUtils.string2List(BaseWebUtil.getServletContext().getContextPath()+MyConstant.LOCAL_LISTVIEW_ACCESS+news.getImgUuid()+"_0.png"));
			}
		}
		return newslist;
	}

	@Override
	public NewsDetails getNewsDetailByUrl(String url,String tableName) {
		return dgDao.getNewsDetailByUrl(url,tableName);
	}

	@Override
	public NewsAbstract getNewsabsByUrl(String url,String tableName) {
		return dgDao.getNewsabsByUrl(url,tableName);
	}

	@Override
	public Pagination<EntranceProfile> findProlist(SearchForm sf) {
		return dgDao.findProlist(sf);
	}


	@Override
	public void newsPersist(NewsAbstract newsabs, NewsDetails newsdet) {
//			String temurl="";
//			temurl = newsabs.getUrl().replace("?", "c");
//			temurl = temurl.replace("&", "r");
//			newsabs.setUrl(temurl);
			String absidf = dgDao.addNewsAbs(newsabs, newsabs.getTableName());
			dgDao.addDetail(absidf,newsabs.getUrl(), newsdet.getContent(),newsabs.getDate(),newsdet.getTableName());
	}


	@Override
	public List<NewsAbstract> deleteOldNews(String tableName) {
		return dgDao.deleteOldNews(tableName);
	}


	@Override
	public List<NewsAbstract> deleteDuplicate(String absTableName, String detTableName) {
		return dgDao.deleteDuplicate(absTableName, detTableName);
	}


	@Override
	public int resetSequence(String sequenceName) {
		return dgDao.resetSequence(sequenceName);
	}


	@Override
	public NewsDetails getNewsDetailByAbsid(String absid, String tableName) {
		return dgDao.getNewsDetailByAbsid(absid, tableName);
	}


	@Override
	public NewsAbstract getNewsabsByabsid(String absid, String tableName) {
		return dgDao.getNewsabsByabsid(absid, tableName);
	}
	
	
	
}
