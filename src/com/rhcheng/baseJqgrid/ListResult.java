package com.rhcheng.baseJqgrid;

import java.util.List;
import com.rhcheng.common.Pagination;

/**
 * 操作结果返回信息
 * @author RhCheng
 * @date   2014-7-18
 */
public class ListResult {
	// 总记录条数
	private Integer total;
	// 查询的页码数，即第几页
	private Integer page;
	// 每页显示的数据条数
	private Integer rows = 30;
	// 返回查询得到的结果集
	private List resultList;
	// 接收前台传来的查询参数
	private String filters;
	// 存储前台的查询条件
	private JqGridSearch searchQuery;
	// 异常信息
	protected String errorInf;
	
	
	private Integer records;
	private String sidx;
	private String sord;
	private Boolean _search = false;
	
	
	// 服务器返回页面前对分页对象的处理
	public void initPage(Pagination<? extends Object> pageObj) {
		// 总共的页数
		this.total = pageObj.getTotalPage();
		// 当前的页数
		this.page = pageObj.getToPage();
		//　结果集
		this.setResultList(pageObj.getObjLists());
		// 总共的记录条数
		this.records = pageObj.getTotalRows();
	}
	
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getRecords() {
		return records;
	}
	public void setRecords(Integer records) {
		this.records = records;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}
	public Boolean get_search() {
		return _search;
	}
	public void set_search(Boolean _search) {
		this._search = _search;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public JqGridSearch getSearchQuery() {
		return searchQuery;
	}
	public void setSearchQuery(JqGridSearch searchQuery) {
		this.searchQuery = searchQuery;
	}
	public String getErrorInf() {
		return errorInf;
	}
	public void setErrorInf(String errorInf) {
		this.errorInf = errorInf;
	}
	
	
	
	
}
