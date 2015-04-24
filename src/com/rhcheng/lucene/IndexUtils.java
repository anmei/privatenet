package com.rhcheng.lucene;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import com.rhcheng.util.date.DateUtils;
import com.rhcheng.util.string.StringUtil;


/**
 * lucene的索引工具类
 * 
 * @author 黄文韬
 * @since 1.0
 * @Copyright 2013 东莞市邮政局All rights reserved.
 */
public class IndexUtils {
	private static final Logger LOGGER = Logger.getLogger(IndexUtils.class);
	// 索引的存放路径
	private static final String indexPath = "H:\\2T_E_S_T\\GIT\\privatenet\\WebRoot\\lucene\\index\\";
		
	// 庖丁解牛分词器（单例）
	private static Analyzer ANALYZER = null;
	static {
		if (ANALYZER == null) {
			ANALYZER = new PaodingAnalyzer();
		}
	}
	/**
	 * 得到庖丁解牛分词器
	 * 
	 * @return
	 */
	public static Analyzer getAnalyzer() {
		return ANALYZER;
	}

	/**
	 * 得到路径对象
	 * 
	 * @param path 相对路径
	 * @return
	 */
	public static Directory getDirectory(String path) {
		Directory directory = null;
		try {
			directory = FSDirectory.open(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return directory;
	}

	/**
	 * 得到读索引类
	 * @return
	 */
	public static IndexReader getIndexReader() {
		IndexReader reader = null;
		try {
			reader = IndexReader.open(getDirectory(indexPath));
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reader;
	}

	/**
	 * 得到些索引类
	 * @return
	 */
	public static IndexWriter getIndexWriter() {
		IndexWriter writer = null;
		try {
			writer = new IndexWriter(getDirectory(indexPath),
					new IndexWriterConfig(Version.LUCENE_36, ANALYZER));
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer;
	}

	/**
	 * 建立索引
	 * @param bean 需要建立索引的bean
	 * @param excludField 不建立索引的bean字段名称
	 * @param analyzedFiled 建立进行分词的bean字段名称
	 * @param flagStr 标志字符串  如资讯以ZX开头 ，就传入ZX
	 */
	public static void createIndex(Object bean,String[] excludField,String[] analyzedFiled,String flagStr) {
		// 得到输出索引类
		IndexWriter indexWriter = null;
		// 索引类
		try {
			indexWriter = getIndexWriter();
			Document doc = new Document();
			//新增标志
			doc.add(new Field("flagId",flagStr+StringUtil.getUUIDStr(),Store.YES,Index.NOT_ANALYZED));
			//对象的字段信息
			java.lang.reflect.Field[] fields = bean.getClass().getDeclaredFields();
			//建立索引
			for (java.lang.reflect.Field field : fields) {
				field.setAccessible(true);
				if(!StringUtil.isInArray(field.getName(),excludField) && excludField != null){
					addField(field,bean,doc,Store.YES,
							StringUtil.isInArray(field.getName(), analyzedFiled) ? Index.ANALYZED : Index.NOT_ANALYZED);
				} 
				field.setAccessible(false);
			}
			indexWriter.addDocument(doc);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭writer
				indexWriter.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 优化索引
	 */
	public static void mergeIndex() {
		IndexWriter indexWriter = null;
		// 强制优化索引
		try {
			indexWriter = getIndexWriter();
			indexWriter.forceMerge(1);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				indexWriter.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 更新索引
	 * @param bean 需要建立索引的bean
	 * @param excludField 不建立索引的bean字段名称
	 * @param notAnalyzedFiled 建立进行分词的bean字段名称
	 * @param term 查找索引库中的term
	 */
	public static void updateIndex(Object bean,String[] excludField,String[] analyzedFiled,Term term) {
		
		// 得到输出索引类
		IndexWriter indexWriter = null;
		// 索引类
		try {
			indexWriter = getIndexWriter();
			Document doc = new Document();
			//对象的字段信息
			java.lang.reflect.Field[] fields = bean.getClass().getDeclaredFields();
			//建立索引
			for (java.lang.reflect.Field field : fields) {
				field.setAccessible(true);
				if(!StringUtil.isInArray(field.getName(),excludField) && excludField != null){
					addField(field,bean,doc,Store.YES,
							StringUtil.isInArray(field.getName(), analyzedFiled) ? Index.ANALYZED : Index.NOT_ANALYZED);
			   } 
				field.setAccessible(false);
			}
			//更新索引
			indexWriter.updateDocument(term, doc, ANALYZER);
			indexWriter.forceMerge(1);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭writer
				indexWriter.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 删除全部索引文件
	 */
	public static void deleteAll() {
		IndexWriter writer = null;
		try {
			writer = getIndexWriter();
			writer.deleteAll();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据条件删除索引
	 * @param term 条件
	 */
	public static void delete(Term term) {
		IndexWriter writer = null;
		try {
			writer = getIndexWriter();
			writer.deleteDocuments(term);
			writer.forceMerge(1);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询
	 * @param fieldname 字段名
	 * @param queryText 查询内容
	 * @return
	 * @throws ParseException
	 */
	public static Query getQuery(String fieldname,String queryText) throws ParseException{
		queryText = queryText.trim();
		if (queryText.length() > 1) {
			QueryParser queryParser = new QueryParser(Version.LUCENE_36, fieldname, IndexUtils.getAnalyzer());
			return queryParser.parse(queryText);
		}else{
			return new WildcardQuery(new Term(fieldname,"*"+queryText+"*"));
		}
	}
	

	/**
	 * 得到索引搜索类
	 * @return
	 */
	public static IndexSearcher getIndexSearcher() {
		IndexSearcher searcher = null;
		try {
			searcher = new IndexSearcher(getIndexReader());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searcher;
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
	private static void addField(java.lang.reflect.Field field, Object bean,Document doc,Store store, Index index) {
		String typeName = field.getType().getName(); // 得到字段类型
		String fieldName = field.getName(); //字段名
		// 设置值的
		// （所属对象，值）, ResultSet的getString/getInt...里面的字段名是不分大小写的
		try {
			if (typeName.equals("float")
					|| typeName.equals("java.lang.Float")) {
				doc.add(new NumericField(fieldName,store,true).setFloatValue(field.get(bean)==null || "".equals(field.get(bean)) 
						? 0 : Float.parseFloat(field.get(bean).toString())));
			} else if (typeName.equals("double")
					|| typeName.equals("java.lang.Double")) {
				doc.add(new NumericField(fieldName,store,true).setDoubleValue(field.get(bean)==null || "".equals(field.get(bean)) 
						? 0 : Double.parseDouble(field.get(bean).toString())));
			}  else if (typeName.equals("java.util.Date")) {
				doc.add(new Field(field.getName(),field.get(bean) == null ? "":
					 DateUtils.formatDate((Date)field.get(bean),"yyyy-MM-dd HH:mm:ss")
						,store,index));
			} else {
				doc.add(new Field(fieldName,field.get(bean)==null || "".equals(field.get(bean)) ? "": field.get(bean).toString()
						,store,index));
			}
		} catch (IllegalArgumentException e) {
			LOGGER.error("============>IdexUtils:addField [fieldTypeName:"+typeName+"] " +
					"[fieldName:"+field.getName()+"]  [error:"+e.getMessage()+"]");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			LOGGER.error("============>IdexUtils:addField [fieldTypeName:"+typeName+"] " +
					"[fieldName:"+field.getName()+"]  [error:"+e.getMessage()+"]");
			e.printStackTrace();
		}
	}
}
