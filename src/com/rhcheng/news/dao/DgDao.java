package com.rhcheng.news.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.rhcheng.base.BaseDAO;
import com.rhcheng.common.MyConstant;
import com.rhcheng.common.PageFormBean;
import com.rhcheng.common.Pagination;
import com.rhcheng.news.entity.EntranceProfile;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.formbean.SearchForm;
import com.rhcheng.util.date.DateUtils;
import com.rhcheng.util.string.NewsUtils;

@Repository("dgDao")
public class DgDao extends BaseDAO{
	
	//---------------------------------------------------------------------------------------------------news abstract
	public Long getSequence(String sequenceName){
		if(MyConstant.CURRENT_SEARCH == MyConstant.MYSQL_SEARCH){
			return super.getSequenceIdOfMysql(sequenceName);
		}else if(MyConstant.CURRENT_SEARCH == MyConstant.ORCLA_SEARCH){
			return super.getSequenceId(sequenceName);
		}else{
			return null;
		}
	}
	public int resetSequence(String sequenceName){
		if(MyConstant.CURRENT_SEARCH == MyConstant.MYSQL_SEARCH){
			String sql = " update sequence set "+sequenceName+" = 0 ";
			return super.saveORUpdate(sql, new Object[]{});
		}else if(MyConstant.CURRENT_SEARCH == MyConstant.ORCLA_SEARCH){
			String sql1 = " drop sequence "+sequenceName;
			String sql2 = " create sequence "+sequenceName+" minvalue 1 maxvalue 99999999999999 start with 1 increment by 1 nocache order ";
			super.saveORUpdate(sql1, new Object[]{});
			super.saveORUpdate(sql2, new Object[]{});
			return 1;
		}else {
			return 0;
		}
	}
	
	public String getNewsAbsid(String sequenceName){
		return DateUtils.formatDate(new Date(), "yyyyMMdd")+this.getSequence(sequenceName);
	}
	
	/**
	 * batch update the news abstract list
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param args
	 * @return
	 */
	public int batchUpdateNewsAbs(List<Object[]> args){
		String sql = " insert into dgnewsabs(id,title,url,auth,originalDate,date,imgPath) values(?,?,?,?,?,?,?) ";
		return super.batchUpdate(sql, args);
		
	} 
	

	/**
	 * update the news abstract list one by one
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param args
	 * @return
	 */
	public int updateNewsAbs(NewsAbstract args,String tableName,String sequenceName){
		String sql = " insert into "+tableName+" (id,title,url,auth,originalDate,date,imgPath) values(?,?,?,?,?,?,?) ";
		return super.saveORUpdate(sql, this.getSequence(sequenceName),args.getTitle(),args.getUrl(),args.getAuth(),
				args.getOriginalDate(),args.getDate(),args.getImgPath());
		
	} 
	
	public String addNewsAbs(NewsAbstract args,String tableName){
		String absid = this.getNewsAbsid(args.getSequenceName());
		System.out.println("---------标题----------->："+args.getTitle());
		String sql = " insert into "+tableName+" (absid,title,url,auth,originalDate,date,imgPath,imgUuid) values(?,?,?,?,?,?,?,?) ";
		super.saveORUpdate(sql,absid,args.getTitle(),args.getUrl(),args.getAuth(),args.getOriginalDate(),args.getDate(),
				args.getImgPath(),args.getImgUuid());
		return absid;
		
	} 
	
	
	public int deleteNewsAbs(Long begin,Long end,String tableName){
		String sql="";
		if(end == null){
			sql = " delete from "+tableName+" where id>=? ";
			return super.saveORUpdate(sql, begin);
		}else{
			sql = " delete from "+tableName+" where id>=? and id<=? ";
			return super.saveORUpdate(sql, begin,end);
		}
		
	}
	
	public Pagination<NewsAbstract> findNewsAbs(PageFormBean pf,String absTableName){
		if(MyConstant.CURRENT_SEARCH == MyConstant.MYSQL_SEARCH){
			String sql = " SELECT *,count(DISTINCT title) AS other FROM "+absTableName+" GROUP BY title order by date desc ";
			return super.queryForPage(MyConstant.CURRENT_SEARCH, pf.getPageSize(), pf.getToPage(), sql, NewsAbstract.class, null);
		}else if(MyConstant.CURRENT_SEARCH == MyConstant.ORCLA_SEARCH){
			String sql = " select * from "
						+" (select absid,title,auth,originalDate,date,imgPath,url,Row_number() OVER (PARTITION BY title ORDER BY date desc) other from "+absTableName+" ) "
						+" where other = 1 ";
			return super.queryForPage(MyConstant.CURRENT_SEARCH, pf.getPageSize(), pf.getToPage(), sql, NewsAbstract.class, null);
		}else{
			return null;
		}
	}
	
	public NewsAbstract getNewsabsByUrl(String url,String tableName){
		String sql = " select * from "+tableName+" where url = ? ";
		return super.queryForListBean(sql, new Object[]{url}, NewsAbstract.class).get(0);
	}
	
	public NewsAbstract getNewsabsByabsid(String absid,String tableName){
		String sql = " select * from "+tableName+" where absid = ? ";
		return super.queryForListBean(sql, new Object[]{absid}, NewsAbstract.class).get(0);
	}
	
	
	// 对表中数据去重
	public List<NewsAbstract> deleteDuplicate(String absTableName,String detTableName){
		List<NewsAbstract> res = new ArrayList<NewsAbstract>();
		
		
		if(MyConstant.CURRENT_SEARCH == MyConstant.MYSQL_SEARCH){
			String sql3 = " select imguuid from "+absTableName+" where absid not in (select t.absid from (SELECT absid,count(distinct title) AS FIELD_1 FROM "+absTableName+" GROUP BY title) t)";
			res = super.queryForListBean(sql3, null, NewsAbstract.class);
			
			/*String sql1 = " delete from "+absTableName+" where absid not in (select t.absid from (SELECT absid,count(distinct title) AS FIELD_1 FROM "+absTableName+" GROUP BY title) t)";
			String sql2 = " delete from "+detTableName+" where absidf not in (select t.absid from (SELECT absid,count(distinct title) AS FIELD_1 FROM "+absTableName+" GROUP BY title) t)";
			super.saveORUpdate(sql2, null);
			super.saveORUpdate(sql1, null);*/
		}else if(MyConstant.CURRENT_SEARCH == MyConstant.ORCLA_SEARCH){
			String sql3 = " select imguuid from "+absTableName+" where absid not in "
						+" (select t.absid from (select absid,Row_number() OVER (PARTITION BY title ORDER BY date desc) other from "+absTableName+") t where t.other = 1) ";
			res = super.queryForListBean(sql3, null, NewsAbstract.class);
			
			
			/*String sql1 = " delete from "+absTableName+" where absid not in "
						+" (select t.absid from (select absid,Row_number() OVER (PARTITION BY title ORDER BY date desc) other from "+absTableName+") t where t.other = 1) ";
			
			String sql2 = " delete from "+detTableName+" where absidf not in "
					+" (select t.absid from (select absid,Row_number() OVER (PARTITION BY title ORDER BY date desc) other from "+absTableName+") t where t.other = 1) ";
			super.saveORUpdate(sql2, null);
			super.saveORUpdate(sql1, null);*/
		}
		
		
		/*删除duplicate内容**/
		String sql1 = " delete from "+absTableName+" where imguuid in ( ";
		String sql2 = " delete from "+detTableName+" where absidf in (select absid from "+absTableName+" where imguuid in( ";
		for(int k=0;k<res.size();k++){
			if(StringUtils.isNotBlank(res.get(k).getImgUuid()) && k<(res.size()-1)){
				sql1 += "'"+res.get(k).getImgUuid()+"' ,";
				sql2 += "'"+res.get(k).getImgUuid()+"' ,";
			}else if(StringUtils.isNotBlank(res.get(k).getImgUuid())){
				sql1 += "'"+res.get(k).getImgUuid()+"' )";
				sql2 += "'"+res.get(k).getImgUuid()+"' ))";
			}
		}
		if(res.size()>0){
			super.saveORUpdate(sql2, new Object[]{});
			super.saveORUpdate(sql1, new Object[]{});
		}
		
		return res;

	}
	
	
	
	
	//----------------------------------------------------------------------------------------------------news detail
	public int getNewsDetail(String url,String tableName){
		String sql = " select count(1) from "+tableName+" where url=? ";
		return super.queryCount(sql, new Object[]{url});
	}
	
	public int deleteDetail(String url){
		String sql = " delete from dgnewsdetail where url = ? ";
		return super.saveORUpdate(sql, url);
	}
	
	public int deleteDetailNotInAbs(String abstableName,String detailTableName){
		String sql = " delete from "+detailTableName+" where url not in (select a.url from "+abstableName+" a) ";
		return super.saveORUpdate(sql,new Object[]{});
	}

	public int addDetail(String absidf,String url,String content,Date date,String tableName){
		String sql = " insert into "+tableName+" (absidf,url,content,date) values (?,?,?,?)";
		return super.saveORUpdate(sql, absidf,url,content,date);
	}
	
	public NewsDetails getNewsDetailByUrl(String url,String tableName){
		String sql = " select * from "+tableName+" where url=? ";
		return super.queryForBean(sql, new Object[]{url}, NewsDetails.class);
	}
	
	public NewsDetails getNewsDetailByAbsid(String absid,String tableName){
		String sql = " select * from "+tableName+" where absidf=? ";
		return super.queryForBean(sql, new Object[]{absid}, NewsDetails.class);
	}
	
	public List<NewsDetails> FindAllNewsDetail(String tableName){
//		String sql = " select * from "+tableName;
		String sql = " SELECT b.absidf,a.title,a.url,b.content,b.date FROM dgnewsabs a,dgnewsdetail b where a.absid=b.absidf ";
		return super.queryForListBean(sql, null, NewsDetails.class);
	}
	
	
	
	
	
	
	//----------------------------------------------------------------------------------------------profile
	public Pagination<EntranceProfile> findProlist(SearchForm sf) {
		String sql = " select url,charset as contentCharset,pageprocessor as contentProcessClassName,remark,abstable,detailtable from profile where 1=1 ";
		List<Object> para = new ArrayList<Object>();
		if(sf != null){
			if(StringUtils.isNotBlank(sf.getUrl())){
				sql += " and url = ? ";
				para.add(sf.getUrl());
			}
			if(StringUtils.isNotBlank(sf.getRemark())){
				sql += " and remark = ? ";
				para.add(sf.getRemark());
			}
		}
		try{
			return super.queryForPage(MyConstant.CURRENT_SEARCH, sf.getPageSize(), sf.getToPage(),
					sql, EntranceProfile.class, para.toArray());
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<NewsAbstract> deleteOldNews(String tableName) {
		String sql = "";
		String sql2 = "";
		// 查询新闻对应的本地图片名
		List<NewsAbstract> imguuidList = new ArrayList<NewsAbstract>();
		
		if(MyConstant.CURRENT_SEARCH == MyConstant.MYSQL_SEARCH){
			sql = " delete from "+tableName+" where STRCMP(date_format(date,'%Y-%m-%d %H:%i'),?) = -1 ";
			sql2 = " select imguuid from "+tableName+" where STRCMP(date_format(date,'%Y-%m-%d %H:%i'),?) = -1 ";
			if(tableName.indexOf("detail")==-1){
				imguuidList = super.queryForListBean(sql2, new Object[]{NewsUtils.toDeleteByDate()}, NewsAbstract.class);
			}
			
			super.saveORUpdate(sql, NewsUtils.toDeleteByDate());
		}else if(MyConstant.CURRENT_SEARCH == MyConstant.ORCLA_SEARCH){
			sql = " delete from "+tableName+" where to_char(date,'yyyy-MM-dd HH24:MI') <? ";
			sql2 = " select imguuid from "+tableName+" where to_char(date,'yyyy-MM-dd HH24:MI') <? ";
			if(tableName.indexOf("detail")==-1){
				imguuidList = super.queryForListBean(sql2, new Object[]{NewsUtils.toDeleteByDate()}, NewsAbstract.class);
			}
			
			super.saveORUpdate(sql, NewsUtils.toDeleteByDate());
		}
	
		return imguuidList;
		
	}
	
	
	/*public void dotest(){
		String sql = " insert into financenewsdetail (url) values(?) ";
		super.saveORUpdate(sql, "fds你好吗？");
	}*/
	
}
