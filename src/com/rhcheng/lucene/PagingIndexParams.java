package com.rhcheng.lucene;

import org.apache.lucene.search.Query;

import org.apache.lucene.search.Sort;

import com.rhcheng.common.MyConstant;



/**
 * 分页的参数类
 * 
 * @author 黄文韬
 * @since 1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class PagingIndexParams {
	private Integer nextPage;// 需要显示内容的页码
	private Integer pageSize; // 每页的条数
	private Query query; // lucene查询对象
	private Sort sort; // 排序对象
	private Class clazz; //封装的bean

	//设置下一页的页码
	public Integer getNextPage() {
		if (nextPage == null || "".equals(nextPage)) {
			return  1;
		}else {
			return nextPage;
		}
	}
	
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getPageSize() {
		if (pageSize == null || "".equals(pageSize)) {
			int configSize = MyConstant.PAGESIZE;
			return configSize;
		}else {
		   return pageSize;
		}
	}

	//设置每页条数
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}
	
	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

}
