package com.rhcheng.baseJqgrid;

import java.util.List;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.rhcheng.common.Pagination;


/**
 * jqgrid查询 base action
 * @author RhCheng
 * @date   2014-3-25
 * @since  jdk1.6
 */
public abstract  class QGridListAction {
	
	// 总记录条数
	private Integer total;
	// 查询的页码数，即第几页
	private Integer page;
	// 每页显示的数据条数
	private Integer rows = 30;
	// 接收前台传来的查询参数
	private String filters;
	
	private Integer records;
	
	private String sidx;
	
	private String sord;
	// 返回查询得到的结果集
	private List resultList;
	
	private Boolean _search = false;

	// 存储前台的查询条件
	private JqGridSearch searchQuery;
	//异常信息
	protected String errorInf;

	
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
	
	

	public final static String LIST_PAGE = "listPage";
	// 返回ajax查询的结果
	public final static String LIST_AJAX = "ajax";
	// 返回ajax查询错误
	public final static String LIST_QUERYERROR = "queryError";
	
	public String listPage() {
		return LIST_PAGE;
	}
	
	
	// action默认需要实现的方法，即分页查询
	public abstract ListResult list();
	
	
	public String getErrorInf() {
		return errorInf;
	}
	public void setErrorInf(String errorInf) {
		this.errorInf = errorInf;
	}
	
	@JSON(serialize = false)
	public JqGridSearch getSearchQuery() {
		if (searchQuery == null && filters != null) {
			try {
				searchQuery = JqGridSearch.convert(filters);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return searchQuery;
	}

	public void setSearchQuery(JqGridSearch searchQuery) {
		this.searchQuery = searchQuery;
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
	
	@ModelAttribute("page")
	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	public List getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public Integer getRows() {
		return rows;
	}
	
	@ModelAttribute("rows")
	public void setRows(Integer rows) {
		this.rows = rows;
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

	@JSON(serialize = false)
	public Boolean get_search() {
		return _search;
	}

	public void set_search(Boolean search) {
		_search = search;
	}

	@JSON(serialize = false)
	public String getFilters() {
		return filters;
	}
	
	@ModelAttribute("filters")
	public void setFilters(String filters) {
		this.filters = filters;
	}

}