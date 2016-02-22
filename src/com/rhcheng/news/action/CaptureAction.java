package com.rhcheng.news.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rhcheng.news.webmagic.CrawlModel;
import com.rhcheng.news.webmagic.crawl.SpiderCrawl;
import com.rhcheng.news.webmagic.pageprocessor.TaobaoItemPageProcessor;

@Controller
@RequestMapping("/capture/*")
public class CaptureAction {
	@Resource
	private SpiderCrawl spiderCrawl;
	// 全局变量存储抓取结果
	public static List<String> res = new ArrayList<String>();
    
	@RequestMapping(value="captureTaoBaoItem.json")
	public ModelAndView captureTaoBaoItem(){
	    CrawlModel cm = new CrawlModel();
	    cm.setCharset("UTF-8");
	    cm.setUrl("http://www.baidu.com");
	    cm.setMethod("GET");
	    cm.setPageProcessor(TaobaoItemPageProcessor.class);
	    Map<String,CrawlModel> url2cm = new HashMap<String,CrawlModel>();
	    url2cm.put(cm.getUrl(), cm);
	    
	    spiderCrawl.craw(url2cm, null, 1);
	    ModelAndView mo =  new ModelAndView();
	    mo.addObject("res", this.res);
	    return mo;
	}
	
}
