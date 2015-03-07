package com.rhcheng.common;
/**
 * 分页查询基类
 * @author Administrator
 *
 */
public class PageFormBean {
	// 页大小,即每一页显示的记录条数
	private int pageSize = 10;
	// 请求页，即当前要显示的页码
	private int toPage = 1;
	// 总页数
	private int totalPage = 1;
	
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getToPage() {
		return toPage;
	}
	public void setToPage(int toPage) {
		this.toPage = toPage;
	}
	
	
}
