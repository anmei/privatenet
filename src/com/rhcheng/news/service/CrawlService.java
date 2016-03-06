package com.rhcheng.news.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rhcheng.news.dao.CrawlDao;
import com.rhcheng.taobaoassi.model.ProductInfo;

@Service
public class CrawlService implements ICrawlService{
	@Resource
	private CrawlDao crawlDao;
	
	@Override
	public List<ProductInfo> findProductInfo(ProductInfo pi) {
		// TODO Auto-generated method stub
		return crawlDao.findProductInfo(pi);
	}

	@Override
	public int addProductInfo(ProductInfo pi) {
		// TODO Auto-generated method stub
		return crawlDao.addProductInfo(pi);
	}

}
