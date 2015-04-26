package com.rhcheng.lucene;

import java.util.List;

/**
 * 分页结果类
 * 
 * @author 黄文韬
 * @since 1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class PagingIndexResults {
	private int currentPage; //当前页码
	private int totalSize;   //总共条数
	private int totalPages;  //总共页数
	private Integer pageSize; // 每页的条数
	private List list ;       //结果集
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
}
