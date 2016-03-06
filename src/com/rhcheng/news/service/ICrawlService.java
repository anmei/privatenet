package com.rhcheng.news.service;

import java.util.List;

import com.rhcheng.taobaoassi.model.ProductInfo;

public interface ICrawlService {
	public List<ProductInfo> findProductInfo(ProductInfo pi);
	public int addProductInfo(ProductInfo pi);
}
