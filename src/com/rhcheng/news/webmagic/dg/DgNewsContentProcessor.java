package com.rhcheng.news.webmagic.dg;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import us.codecraft.webmagic.Page;

import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.util.string.NewsUtils;

/**
 * get news content without relative image path,imgPath and publish date
 * @author RhCheng
 * @date   2014-9-26
 */
public class DgNewsContentProcessor extends BaseSpider{
	
	/**
	 * return content with absolute img src value
	 */
	@Override
	public void detailProcess(Page page) throws RuntimeException{
		NewsAbstract newsabs = super.thv.getNewsabs();
		NewsDetails newsdet = super.thv.getNewsdet();
		
		// 分页处理 第一页时获取其他页面的url
		if(thv.getF() == 1){
			List<String> requesturl = getRequestUrl(page.getHtml().getDocument(),page.getRequest().getUrl());
			super.addTargetUrls(requesturl, page);
		}
		
		
		/** 获取新闻正文*/
		String temcontent = "";
		Document doc = page.getHtml().getDocument();
		temcontent = doc.select(".TRS_Editor").get(0).toString().trim();
		/*模拟获取分页信息,将内容存在静态变量{@code content}中*/
		thv.setContent(thv.getContent()+temcontent);
		
		
		// if(page.getTargetRequests().size() == 0){// 抓取到的每一个url都是一个不同的page对象，故不能通过此方法判断分页是否为最后一页
		// 根据{@Scheduler}判断是否还有分页，当没有分页时继续处理,此种方法也有缺陷，因为在处理倒数第二个时，{@Scheduler}的size已经为0了，具体见{@Spider#Run}循环的写法
		if(super.isLast()){
			newsdet.setTableName("dgnewsdetail");
			newsdet.setUrl(newsabs.getUrl());
			// 获取日期
			if(newsabs.getDate() == null){
				String oridate = super.getOriginalDateOfNewsDetail(doc.toString());
				newsabs.setOriginalDate(oridate);
				newsabs.setDate(super.getPublishDate(oridate));
			}
			newsdet.setDate(newsabs.getDate());
			
			// 正文内容与图片的处理
			String content = thv.getContent();
			content = this.imgRelativeToAbsolute(page.getRequest().getUrl(), content);
			newsabs.setImgPath(super.getImgPath(newsdet.getUrl(), content));// 设置网络原图片访问路径
			newsabs.setImgUuid(NewsUtils.getImgUuid(newsabs));
			newsdet.setContent(content);
			/**可以由专有的image服务器处理**/
			content = super.contentAndImgProc(newsdet,newsabs);
			/**********************/
			newsdet.setContent(content);
			
//			NewsUtils.checkAndPutNewsDetail(SpiderEntrance.AllNewsDetailList,SpiderEntrance.AllNewsByUrl,SpiderEntrance.AllNewsList,
//					newsdet);
			
			super.checkAndPutNewsDetail(newsdet);
			
		}
		
	}
	
	
	
	
	
	//--------------------------------------------------------------------------------content process utils
	
	@Override
	public String imgRelativeToAbsolute(String url,String content){
		List<String> imglist = NewsUtils.getImgTag(content);
		String src;
		for(String img:imglist){
			src = NewsUtils.getImgSrc(img);
			if(src!=null && src.indexOf("http://") == -1){
				content = content.replace(NewsUtils.getSrcValue(img), NewsUtils.getImgAbsoluteSrcValue(url, img));
			}
		}
		return content;
	}
	

	
	
	/**
	 * get different page url
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param doc
	 * @param currenturl
	 * @return
	 */
	public static List<String> getRequestUrl(Document doc,String currenturl){
		List<String> urls = new ArrayList<String>();
		Elements eles = doc.select(".clsTRSNavigatorDIV a");
		for(Element e:eles){
			if(NewsUtils.ifNumbers(e.ownText().trim()) && !e.ownText().trim().equals("1")){
				urls.add(currenturl.substring(0, currenturl.lastIndexOf('.'))
						+"_"+(Integer.valueOf(e.ownText().trim())-1)
						+currenturl.substring(currenturl.lastIndexOf('.')));
			}
		}
		return urls;
	}
	
	

}
