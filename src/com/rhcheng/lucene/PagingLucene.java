package com.rhcheng.lucene;


import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.stereotype.Component;

import com.rhcheng.util.date.DateUtils;
import com.rhcheng.util.string.StringUtil;



/**
 * lucene的分页
 * 
 * @author 黄文韬
 * @since 1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
@Component
public class PagingLucene {
	private static final Logger LOGGER = Logger.getLogger(PagingLucene.class);
	//初始化的查询大小
	private int initSize = 100;
	
	/**
	 * 分页方法
	 * @param params 分页参数封装类
	 * @param highLightFields 需要高亮显示的字段
	 * @return
	 */
	public PagingIndexResults paging(PagingIndexParams params,String[] highLightFields){
		
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		// 显示条数
		int querySize = (params.getNextPage() * params.getPageSize()
				/ initSize + 1)
				* initSize;

		// 设置查询、查询显示的条数、排序对象
		TopDocs topDocs = null;
		try {
			if (params.getSort() != null) {
					topDocs = searcher.search(params.getQuery(), querySize,
							params.getSort());
			} else {
				topDocs = searcher.search(params.getQuery(), querySize);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 总共记录条数
		int totalNum = topDocs.totalHits;
		// 总页数
		int pageNum = totalNum % params.getPageSize() == 0 ? totalNum
				/ params.getPageSize() : totalNum / params.getPageSize() + 1;

		// 如果当前页超出了页码范围
		int page = params.getNextPage();
		if (page > pageNum) {
			params.setNextPage(pageNum);
		}

		// 得到记录集
		ScoreDoc[] docs = topDocs.scoreDocs;
		
		//起始位置和终止位置
		int startSize = (page - 1)*params.getPageSize();
		int endSize = startSize + params.getPageSize() > totalNum ? 
				totalNum : startSize + params.getPageSize();
		
		
		//得到本页的结果集
		List beanList = new ArrayList();
		Class beanClass = params.getClazz();
		try {
			for (int i = startSize; i < endSize; i++) {
				Document document = searcher.doc(docs[i].doc);
				try {
					//实例化分页bean对象
					Object object = beanClass.newInstance();
					//得到字段集
					Field[] fields = beanClass.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						//高亮显示
						if (StringUtil.isInArray(field.getName(), highLightFields)) {
							//定义高亮显示的样式
							SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'><b>", "</b></font>");
							String fieldNameStr = field.getName();
							String documentStr = document.get(fieldNameStr);
							//按分数高低
							QueryScorer scorer = new QueryScorer(params.getQuery());  
							Fragmenter fragmenter = new SimpleFragmenter(100);  
					        Highlighter highlight = new Highlighter(formatter,scorer);  
					        highlight.setTextFragmenter(fragmenter);  
			                try {
			                	String hightHtmlStr = highlight.getBestFragment(IndexUtils.getAnalyzer(), fieldNameStr, documentStr);
			                	this.typeMapper(field, object, StringUtil.isBlank(hightHtmlStr)?documentStr:hightHtmlStr);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							}catch (InvalidTokenOffsetsException e) {
								e.printStackTrace();
							}
						}else { //如果不进行高亮显示
							this.typeMapper(field, object, document.get(field.getName()));
							//field.set(object, document.get(field.getName()));
						}
						field.setAccessible(false);
					}
					beanList.add(object);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PagingIndexResults results = new PagingIndexResults();
		results.setCurrentPage(params.getNextPage());//当前页
		results.setPageSize(params.getPageSize());//每页大小
		results.setTotalPages(pageNum);//总页数
		results.setTotalSize(totalNum);//总条数
		results.setList(beanList); //结果集
		return results;
	}
	
	/**
	 * 查询符合条件的记录条数
	 * @param query 查询对象
	 * @return 记录条数
	 */
	public Integer searchTermNum(Query query){
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		int num = 0;
		try {
			TopDocs topDocs = searcher.search(query, 1);
			num = topDocs.totalHits;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return num;
	}
	
	/**
	 * 查询指定条数的记录
	 * @param query 查询对象
	 * @param termSize 显示的条数
	 * @param sort 排序对象 （可为空）
	 * @param clazz 需要封装的javaBean
	 * @param highLightFields 需要高亮显示的字段数组
	 * @return javabean集合
	 */
	public List search(Query query,int termSize,Sort sort,Class clazz,String[] highLightFields){
		IndexSearcher searcher = IndexUtils.getIndexSearcher();
		// 设置查询、查询显示的条数、排序对象
		TopDocs topDocs = null;
		try {
			if (sort != null) {
				topDocs = searcher.search(query, termSize, sort);
			} else {
				topDocs = searcher.search(query, termSize);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 得到记录集
		ScoreDoc[] docs = topDocs.scoreDocs;
		
		//得到本页的结果集
		List beanList = new ArrayList();
		Class beanClass = clazz;
		try {
			termSize = topDocs.totalHits > termSize ? termSize : topDocs.totalHits;
			for (int i = 0; i < termSize; i++) {
				Document document = searcher.doc(docs[i].doc);
				try {
					//实例化分页bean对象
					Object object = beanClass.newInstance();
					//得到字段集
					Field[] fields = beanClass.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						//高亮显示
						if (StringUtil.isInArray(field.getName(), highLightFields)) {
							//定义高亮显示的样式
							SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color='red'><b>", "</b></font>");
							String fieldNameStr = field.getName();
							String documentStr = document.get(fieldNameStr);
							//按分数高低
							QueryScorer scorer = new QueryScorer(query);  
							SimpleFragmenter fragmenter = new SimpleFragmenter(100);  
					        Highlighter highlight=new Highlighter(formatter,scorer);  
					        highlight.setTextFragmenter(fragmenter);  
			                try {
			                	String hightHtmlStr = highlight.getBestFragment(IndexUtils.getAnalyzer(), fieldNameStr, documentStr);
			                	this.typeMapper(field, object, StringUtil.isBlank(hightHtmlStr)?documentStr:hightHtmlStr);
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							}
			                catch (InvalidTokenOffsetsException e) {
								e.printStackTrace();
							}
						}else { //如果不进行高亮显示
							this.typeMapper(field, object, document.get(field.getName()));
						}
						field.setAccessible(false);
					}
					//将对象加入到list中
					beanList.add(object);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return beanList;
	}
	
	/**
	 * 类型的映射
	 * 
	 * @param field
	 * @param obj
	 * @param rs
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	private void typeMapper(Field field, Object obj, String rs) {
		String typeName = field.getType().getName(); // 得到字段类型
		// 设置值的
		// （所属对象，值）, ResultSet的getString/getInt...里面的字段名是不分大小写的
		try {
			if (typeName.equals("java.lang.String")) {
				field.set(obj, rs);
			} else if (typeName.equals("int")
					|| typeName.equals("java.lang.Integer")) {
				field.set(obj, StringUtil.isBlank(rs) ? 0 : Integer.parseInt(rs));
			} else if (typeName.equals("long")
					|| typeName.equals("java.lang.Long")) {
				field.set(obj,  StringUtil.isBlank(rs) ? 0 : Long.parseLong(rs));
			} else if (typeName.equals("float")
					|| typeName.equals("java.lang.Float")) {
				field.set(obj,  StringUtil.isBlank(rs) ? 0 : Float.parseFloat(rs));
			} else if (typeName.equals("double")
					|| typeName.equals("java.lang.Double")) {
				field.set(obj,  StringUtil.isBlank(rs) ? 0 : Double.parseDouble(rs));
			} else if (typeName.equals("boolean")
					|| typeName.equals("java.lang.Boolean")) {
				field.set(obj,  StringUtil.isBlank(rs) ? 0 : Boolean.parseBoolean(rs));
			} else if (typeName.equals("java.util.Date")) {
				field.set(obj, StringUtil.isNotBlank(rs) ? DateUtils.strToDate(rs, "yyyy-MM-dd") : null);
			} else {
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("============>pagingLucene:typeMapper [fieldTypeName:"+typeName+"] " +
					"[fieldName:"+field.getName()+"]  [error:"+e.getMessage()+"]");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LOGGER.error("============>pagingLucene:typeMapper [fieldTypeName:"+typeName+"] " +
					"[fieldName:"+field.getName()+"]  [error:"+e.getMessage()+"]");
			e.printStackTrace();
		}
	}
}
