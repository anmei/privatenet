package com.rhcheng.lucene.test;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.queryParser.standard.nodes.NumericRangeQueryNode;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rhcheng.lucene.IndexUtils;
import com.rhcheng.lucene.service.ILuceneService;
import com.rhcheng.news.entity.NewsDetails;

@SuppressWarnings("unused")
public class TestMain {
	public static void main(String[] args) throws ParseException {
//		ILuceneService luceServ = ServiceFacade.getBean("luceneService", ILuceneService.class);
		
		/**创建索引*/
//		BeanFactory fac = new ClassPathXmlApplicationContext("/configure/applicationContext.xml");
//		ILuceneService luceServ = (ILuceneService)fac.getBean("luceneService");
//				
//		List<NewsDetails> newslist = luceServ.FindAllNewsDetail("dgnewsdetail");
//		String[] analyzedFiled = {"content"};
//		String[] excludField = new String[]{};
//		for(NewsDetails news:newslist){
//			IndexUtils.createIndex(news, excludField, analyzedFiled, "dgnews");
//		}
//		System.out.println("ok");
		
//		IndexUtils.deleteAll();
		Query query = null;
				
		// 搜索
		try {
			/**内置查询类*/
//			// 关键字搜索
//			query = new TermQuery(new Term("date","2015-03-21 10:49:00"));
//			query = new TermQuery(new Term("content","天气"));
			// 文本范围搜索
//			query = new TermRangeQuery("date", "2015-03-21 10:49:00", "2015-03-22 10:49:00", true, true);
			// 数字类型范围搜索
//			query = NumericRangeQuery.newIntRange("date", 20150321, 20150322, true,true);
//			query = new PrefixQuery(new Term("content","东莞"));
			//组合查询
			/*BooleanQuery bquery = new BooleanQuery();
			bquery.add(query, BooleanClause.Occur.MUST);*/
			//短语搜索(某个距离范围内的项对应的文档)
//			PhraseQuery
			//通配符搜索
//			WildCardQuery
			//模糊查询
//			query = new FuzzyQuery(new Term("date","2016-03-21 10:49:00"));
			
			////高级搜索功能////
			//对搜索结国排序
			//MultiPhraseQuery
			//DisjunctionMaxQuery
			
			/**解析用户输入语句*/
			QueryParser queryParser = new QueryParser(Version.LUCENE_36, "content", IndexUtils.getAnalyzer());
			query= queryParser.parse("天气");
			//MultiFieldQueryParser
			
			IndexSearcher searcher = IndexUtils.getIndexSearcher();
			TopDocs hits = searcher.search(query, 3);
//			TopDocs hits = searcher.search(query, 1, new Sort());//指定排序
			ScoreDoc[] scoreDocs = hits.scoreDocs;
			for(ScoreDoc sc:scoreDocs){
				Document dc = searcher.doc(sc.doc);
				System.out.println(dc.get("content")+" "+dc.get("date"));
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("ok");
		
		
	}
}
