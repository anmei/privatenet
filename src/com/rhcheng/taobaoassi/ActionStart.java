package com.rhcheng.taobaoassi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rhcheng.news.webmagic.CrawlModel;
import com.rhcheng.news.webmagic.crawl.SpiderCrawl;
import com.rhcheng.taobaoassi.model.ProductInfo;

@Controller
@RequestMapping("/action/*")
public class ActionStart {
	public static List<ProductInfo> nvzhuang;
	public static List<ProductInfo> nvxie;
	public static List<ProductInfo> tongzhuang;
	public static List<ProductInfo> tongxie;
	public static List<ProductInfo> jiaju;
	public static List<ProductInfo> qita;
	
	@Resource
	private SpiderCrawl spiderCrawl;
	
	
	@RequestMapping(value="doAction.json")
	public ModelAndView doAction(){
		// 从数据库获取每种分类商品的信息
		
		// 开始抓取商品
		Map<String,CrawlModel> url2cm = new HashMap<String,CrawlModel>();
		url2cm.put("http://www.beibei.com/martshow/180797.html?sort=hot&cate=0&page=1",
				MainStart.getBeibeiTemaiCrawlModel("http://www.beibei.com/martshow/180797.html?sort=hot&cate=0&page=1"));
		url2cm.put("http://www.beibei.com/martshow/180797.html?sort=hot&cate=0&page=2",
				MainStart.getBeibeiTemaiCrawlModel("http://www.beibei.com/martshow/180797.html?sort=hot&cate=0&page=2"));
		url2cm.put("http://www.beibei.com/martshow/180797.html?sort=hot&cate=0&page=3",
				MainStart.getBeibeiTemaiCrawlModel("http://www.beibei.com/martshow/180797.html?sort=hot&cate=0&page=3"));
		spiderCrawl.craw(url2cm, new CountDownLatch(3), 3);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("res", "success");
		return mv;
	}
}
