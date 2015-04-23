package com.rhcheng.baseJqgrid;

/**
 * 查询条件处理类
 * 
 * 提供查询索引和格式转换
 * @author RhCheng
 * @date   2014-3-25
 * @since  jdk1.6
 */
public interface JqGridSearchFormater {
	// 查询条件字段前缀，如a.userid中的a
	public String prefix(String field);
	// 查询条件中字段名，如a.userid中的userid
	public String dbFieldName(String field);
	// 查询条件中的数值，如a.userid=23中的23
	public Object format(String field, String data);

}