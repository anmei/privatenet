package com.rhcheng.news.webmagic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * news profile
 * @author RhCheng
 * @date   2014-9-26
 */
@Deprecated
public class NewsUrls {
	private NewsUrls(){}
	
	
	
	//-------------------------------------------------------------------deprecated
	
	/**Dong Guan news list url and list spide profile*/
	public static Map<String,String> URL_AND_PROCESSOR_DG = new HashMap<String,String>();
	static{
		//DongGuan
		Map<String,String> temmap1 = new HashMap<String, String>();
		List<Map<String,String>> temli1 = new ArrayList<Map<String, String>>();
		
		
		URL_AND_PROCESSOR_DG.put("http://news.sun0769.com/", "com.rhcheng.news.webmagic.dg.DgNewsListProcessorHot|gb2312");
		URL_AND_PROCESSOR_DG.put("http://news.sun0769.com/dg/headnews/", "com.rhcheng.news.webmagic.dg.DgNewsListProcessor|gb2312");
		URL_AND_PROCESSOR_DG.put("http://news.sun0769.com/dg/sh/default.shtml", "com.rhcheng.news.webmagic.dg.DgNewsListProcessor|gb2312");
		URL_AND_PROCESSOR_DG.put("http://news.sun0769.com/town/ss/", "com.rhcheng.news.webmagic.dg.DgNewsListProcessor|gb2312");
		URL_AND_PROCESSOR_DG.put("http://news.sun0769.com/town/ms/", "com.rhcheng.news.webmagic.dg.DgNewsListProcessor|gb2312");
		URL_AND_PROCESSOR_DG.put("http://news.sun0769.com/town/ys/", "com.rhcheng.news.webmagic.dg.DgNewsListProcessor|gb2312");
		
	}
	

}
