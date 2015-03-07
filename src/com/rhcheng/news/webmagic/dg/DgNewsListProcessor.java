package com.rhcheng.news.webmagic.dg;

import java.util.Date;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.Page;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.util.string.NewsUtils;

/**
 * just get news list
 * @author RhCheng
 * @date   2014-9-25
 */
public class DgNewsListProcessor extends BaseSpider{
	
	/**
	 * process data and put data in {@code res}
	 * every url access will invoke this method and PipeLine, no matter how many thread
	 */
	@Override
	public void detailProcess(Page page) throws RuntimeException{
		Elements ele = page.getHtml().getDocument().select(".mListB ul li");
		if(ele == null){
			ele = page.getHtml().getDocument().select(".c2 ul li");
		}
		
		if(ele != null){
			String date;
			Date pubdate;
			for(Element element:ele){ 
				if(element.children().size()>0){
					date = element.select("span").get(0).ownText();// 原始显示日期
					pubdate = super.getPublishDate(super.getOriginalDateOfNewsAbs(date));
					NewsAbstract news=new NewsAbstract();  
					String title=NewsUtils.getTitle(element.select("a").get(0)); // 新闻标题
					String path=element.select("a").get(0).attr("abs:href"); //新闻所在绝对路径
					news.setTitle(title); 
					news.setUrl(path);  
					news.setOriginalDate(date);
					news.setDate(pubdate);
					news.setAuth("东莞阳光网");
					news.setTableName("dgnewsabs");
					news.setSequenceName("dgnewsabsid");
					
					news.setContentProcessClassName("com.rhcheng.news.webmagic.dg.DgNewsContentProcessor");
					news.setContentCharset("gb2312");
					
					System.out.println("----------标题----------->"+news.getTitle());
					
//					NewsUtils.checkAndPutNewsAbs(SpiderEntrance.AllNewsListByPageProcess,SpiderEntrance.AllNewsByUrl,
//							SpiderEntrance.AllUrlsListByPageProcess,SpiderEntrance.AllNewsList,
//							news,"com.rhcheng.news.webmagic.dg.DgNewsContentProcessor"); 
					
					super.checkAndPutNewsAbs(news, "com.rhcheng.news.webmagic.dg.DgNewsContentProcessor");
						
				}
			}
			
			/* 模拟获取分页等新的url继续spider*/
//			if(SpiderEntrance.flag == 1){
//				page.addTargetRequest("http://news.sun0769.com/dg/headnews/default_1.shtml");
//			}
//			SpiderEntrance.flag--;
			
			
		}
		
	}

	@Override
	public String imgRelativeToAbsolute(String url, String content) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
