package com.rhcheng.lucene.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rhcheng.news.dao.DgDao;
import com.rhcheng.news.entity.NewsDetails;

@Service("luceneService")
public class LuceneService implements ILuceneService{
	
	@Resource
	private DgDao dgDao;
	
	public List<NewsDetails> FindAllNewsDetail(String tableName){
		return dgDao.FindAllNewsDetail(tableName);
	}
}
