package com.rhcheng.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/*******************************************************************************
 * 分页组件
 * @author RhCheng
 * @version 2013-07-01
 ******************************************************************************/
public class Pagination<T> {

	// 总记录数
	private int totalRows = 0;
	// 总页数
	private int totalPage = 1;
	// 页大小,即每一页显示的记录条数
	private int pageSize = 10;
	// 请求页，即当前要显示的页码
	private int toPage = 1;
	// 查询结果集
	private List<T> objLists;
	
	public Pagination(){}
	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 0) {
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}

	public int getToPage() {
		return toPage;
	}

	public void setToPage(int toPage) {
		if (toPage < 0) {
			toPage = 1;
		}
		this.toPage = toPage;
	}

	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public List<T> getObjLists() {
		return objLists;
	}

	public void setObjLists(List<T> objLists) {
		this.objLists = objLists;
	}
	
	
	
	////////////////////////////////////////////////////////////
	
	/**
	 * 该构造函数可以得到该类的所有属性值
	 * @param pageSize
	 * @param toPage
	 * @param totalRows
	 */
	public Pagination(int pageSize, int toPage, int totalRows) {
		super();
		this.totalRows = totalRows;
		if (pageSize > 0 ) {
			this.pageSize = pageSize;
		}else{
			this.pageSize = 10;//默认
		}
		//生成总页数，注意顺序
		totalPage = getTotalPageMethod();
	
		if (toPage > 0 && toPage <= totalPage) {
			this.toPage = toPage;
		}else if(toPage==0){
			toPage=1;
		}
		else{
			this.toPage = this.totalPage;
		} 
		
	}
	

	/*
	 * 获取总页数方法
	 */
	private int getTotalPageMethod() {
		if (totalRows > 0) {
			int totalpage = totalRows / pageSize;
			if (totalRows % pageSize != 0) {
				totalpage++;
			}
			return totalpage;
		}
		return 0;
	}
	
	
	// 根据sql返回查询总记录行数语句
	public static String countSql(String querySql) {
		return new StringBuffer("SELECT COUNT(*) as count FROM (")
				.append(querySql).append(" ) rhc").toString();
	}
	
	
	
	//根据toPage与pagesize获取该页的起始记录
	public int getFromRowNum() {
		return (this.getToPage() - 1) * this.pageSize;
	}

	//根据toPage获取该页的结束记录
	public int getEndRowNum() {
		return this.getToPage() * this.pageSize;
	}

	
	/********************************************************
	 * oracle 获取分页查询sql语句		方法一
	 * Oracle 根据sql返回分页查询的语句，此处第一个？为getEndRowNum()的返回值，第二个？为getFromRowNum()的返回值
	 *******************************************************/
	
	//oracle
	public static String pageSql_oracle(String querySql) {
		StringBuffer pageSql = new StringBuffer(
				"SELECT *  FROM ( SELECT wrap.*, ROWNUM as brow FROM ( ");
		pageSql.append(querySql).append(
				" ) wrap WHERE ROWNUM <= ? ) WHERE brow > ?  ");
		return pageSql.toString();
	}
	

	//mysql
	public static String pageSql_mysql(String querySql) {
		String pageSql = " select * from( select wrap1.*,(@rownum := @rownum + 1) rownum from ( "
				+ querySql + ") wrap1,(select (@rownum := 0)) wrap2 ) wrap3 where  wrap3.rownum <=? and  wrap3.rownum >? ";
		return pageSql;
	}
	
	
	
	
	/******************************************************
	 * oracle 获取分页查询sql语句		方法二
	 * 需要调用两个函数获取分页查询语句
	 * 
	 * ****************************************************/
	
	//oracle
	public static String pageSql2_oracle(String querySql) {
		StringBuffer pageSql = new StringBuffer(
				"SELECT *  FROM ( SELECT wrap.*, ROWNUM as brow FROM ( ");
		pageSql.append(querySql).append(
				" ) wrap WHERE ROWNUM <= {para1} ) WHERE brow > {para2} ");
		return pageSql.toString();
	}
	
	//mysql
	public static String pageSql2_mysql(String querySql) {
		String pageSql = " select * from( select wrap1.*,(@rownum := @rownum + 1) rownum from ( "
				+ querySql + ") wrap1,(select (@rownum := 0)) wrap2 ) wrap3 where  wrap3.rownum <={para1} and  wrap3.rownum >{para2} ";
		return pageSql;
	}
	

	public String generatePageSql(String querySql) {
		querySql = StringUtils.replace(querySql, "{para1}", toPage * pageSize
				+ "");
		querySql = StringUtils.replace(querySql, "{para2}", (toPage - 1)
				* pageSize + "");
		return querySql;
	}
	
	//==========================================================
	
	

	
	
	
}
