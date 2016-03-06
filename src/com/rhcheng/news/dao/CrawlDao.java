package com.rhcheng.news.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rhcheng.base.BaseDAO;
import com.rhcheng.taobaoassi.model.ProductInfo;
import com.rhcheng.util.date.DateUtils;

@Repository("crawlDao")
public class CrawlDao extends BaseDAO{
	public List<ProductInfo> findProductInfo(ProductInfo pi){
		List<Object>args = new ArrayList<Object>();
		String sql = "select * from product_info where 1=1 ";
		if(pi.getCategory() != null){
			args.add(pi.getCategory());
			sql += "and category = ?";
		}
		if(pi.getGmtcreate() != null){
			args.add(DateUtils.formatDate(pi.getGmtcreate(),"yyyy-MM-dd HH:mm"));
			sql += "and STRCMP(date_format(gmtcreate,'%Y-%m-%d %H:%i'),?) = 0";
		}
		if(pi.getName() != null){
			args.add(pi.getName());
			sql += "and name = ?";
		}	
		if(pi.getNum() != null){
			args.add(pi.getNum());
			sql += "and num = ?";
		}
		if(pi.getOriginname()!= null){
			args.add(pi.getOriginname());
			sql += "and originname = ?";
		}
		if(pi.getUrl() != null){
			args.add(pi.getUrl());
			sql += "and url = ?";
		}
		
		return super.queryForListBean(sql, args.toArray(), ProductInfo.class);
	}
	
	public int addProductInfo(ProductInfo pi){
		String sql = "insert into product_info(category,url,gmtcreate,num,name,originname) values(?,?,?,?,?,?)";
		return super.saveORUpdate(sql, pi.getCategory(),pi.getUrl(),pi.getGmtcreate(),pi.getNum(),pi.getName(),pi.getOriginname());
	}
	
	
}
