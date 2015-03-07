package com.rhcheng.news.webmagic.dg;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.Page;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.news.webmagic.SpiderEntrance;
import com.rhcheng.util.string.NewsUtils;
/**
 * get news list
 * @author RhCheng
 * @date   2014-9-26
 */
public class DgNewsListProcessorHot extends BaseSpider{
	
	@Override
	public void detailProcess(Page page) throws RuntimeException{
		Document doc = page.getHtml().getDocument();
		Elements e1 = doc.select(".hTitleA ul li span a");
		Elements e2 = doc.select(".hTitleA ul li div a");
		e1.addAll(e2);
		
		for(Element element:e1){ 
			NewsAbstract news=new NewsAbstract();  
			String title=NewsUtils.getTitle(element); // 新闻标题
			String path=element.attr("abs:href"); //新闻所在绝对路径
			news.setTitle(title); 
			news.setUrl(path);  
			news.setAuth("东莞阳光网");
			news.setTableName("dgnewsabs");
			news.setSequenceName("dgnewsabsid");
			news.setContentProcessClassName("com.rhcheng.news.webmagic.dg.DgNewsContentProcessor");
			news.setContentCharset("gb2312");
			
//			NewsUtils.checkAndPutNewsAbs(SpiderEntrance.AllNewsListByPageProcess,SpiderEntrance.AllNewsByUrl,
//					SpiderEntrance.AllUrlsListByPageProcess,SpiderEntrance.AllNewsList,
//					news,"com.rhcheng.news.webmagic.dg.DgNewsContentProcessor"); 
			super.checkAndPutNewsAbs(news, "com.rhcheng.news.webmagic.dg.DgNewsContentProcessor");
			
			
		}

		
		
	}

	@Override
	public String imgRelativeToAbsolute(String url, String content) {
		// TODO Auto-generated method stub
		return null;
	}


}
