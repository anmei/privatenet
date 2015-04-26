package com.rhcheng.lucene.service;

import java.util.List;

import com.rhcheng.news.entity.NewsDetails;

public interface ILuceneService {
	public List<NewsDetails> FindAllNewsDetail(String tableName);
}
