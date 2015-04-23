package com.rhcheng.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rhcheng.common.SysConstants;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.extract.SimpleSpider;
import com.rhcheng.news.extract.TextExtract;
import com.rhcheng.news.service.IDgService;
import com.rhcheng.util.LoadProperties;
import com.rhcheng.util.date.DateUtils;
import com.rhcheng.util.string.NewsUtils;

/**
 * dongguan sun
 * @author RhCheng
 * @date   2014-9-18
 */
public class ESun0769 extends SimpleSpider<NewsAbstract>{
	public ESun0769(){}
	public ESun0769(String url) {
		super(url);
	}
	
	//------------------------------------------------------------------------get news list
	
	/**
	 * dg_hot
	 */
	@Override
	public List<NewsAbstract> findNewsList(String url) throws IOException {
		super.setUrl(url);
		Document doc = super.getDocument();
		Elements e1 = doc.select("#part1 .left .l2 .l2_1");
		Elements e2 = doc.select("#part1 .left .l2 .l2_2");
		e1.addAll(e2);
		
		List<NewsAbstract> newslist = new ArrayList<NewsAbstract>();
		for(Element element:e1){ 
			NewsAbstract news=new NewsAbstract();  
			
			String title=NewsUtils.getTitle(element.select(".head a").get(0)); // 新闻标题
			String path=element.select(".head a").get(0).attr("abs:href"); //新闻所在绝对路径
			news.setTitle(title); 
			news.setUrl(path);  
			news.setOriginalDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
			news.setAuth("阳光网");
			newslist.add(news);  

		}
		
		return newslist;
	}
	
	
	/**
	 * dg_head\dg_sh\dg_town_ss\dg_town_ms\dg_town_ys
	 * @author RhCheng
	 * @date 2014-9-17
	 * @return
	 * @throws IOException
	 */
	public List<NewsAbstract> findNewsListDghead(String url) throws IOException {
		super.setUrl(url);
		Document doc = super.getDocument();
		Elements e1 = doc.select(".c2 ul li");
		
		List<NewsAbstract> newslist = new ArrayList<NewsAbstract>();
		for(Element element:e1){ 
			NewsAbstract news=new NewsAbstract();  
			
			String title=NewsUtils.getTitle(element.select("a").get(0)); // 新闻标题
			String path=element.select("a").get(0).attr("abs:href"); //新闻所在绝对路径
			String date = element.select("span").get(0).ownText();// 发布日期
			news.setTitle(title); 
			news.setUrl(path);  
			news.setOriginalDate(date);
			news.setAuth("阳光网");
			newslist.add(news); 

		}
		
		return newslist;
	}
	

	/**
	 * get all the news list from dgsun
	 * @author RhCheng
	 * @date 2014-9-17
	 * @param args
	 * @throws IOException
	 */
	public List<NewsAbstract> getAll() throws IOException{
		
		String url1 = LoadProperties.getPropertieByKeyFromCache("dg_hot", SysConstants.NEWS_PROPERTIES);
		String url2 = LoadProperties.getPropertieByKeyFromCache("dg_head", SysConstants.NEWS_PROPERTIES);
		String url6 = LoadProperties.getPropertieByKeyFromCache("dg_sh", SysConstants.NEWS_PROPERTIES);
		String url3 = LoadProperties.getPropertieByKeyFromCache("dg_town_ss", SysConstants.NEWS_PROPERTIES);
		String url4 = LoadProperties.getPropertieByKeyFromCache("dg_town_ms", SysConstants.NEWS_PROPERTIES);
		String url5 = LoadProperties.getPropertieByKeyFromCache("dg_town_ys", SysConstants.NEWS_PROPERTIES);
		//-----------
		List<NewsAbstract> newslist = new ArrayList<NewsAbstract>();
		newslist.addAll(this.findNewsList(url1));
		newslist.addAll(this.findNewsListDghead(url2));
		newslist.addAll(this.findNewsListDghead(url3));
		newslist.addAll(this.findNewsListDghead(url4));
		newslist.addAll(this.findNewsListDghead(url5));
		newslist.addAll(this.findNewsListDghead(url6));
			
		return newslist;
	}
	
	//--------------------------------------------------------------------------------date process
	
	/** get publish date from content */
	public static Date getDateOfDg(Document doc){
		return NewsUtils.parseDate(NewsUtils.getDate(doc.toString()));
	}
	
	//--------------------------------------------------------------------------------img process
	
	public static String getPrefixOfImg(String url){
		return url.substring(0, url.lastIndexOf("/")+1);
	}
	
	/** get the absolute src value*/
	public static String getImgAbsoluteSrc(String url,String imgTag){
		return getPrefixOfImg(url)+TextExtract.getImgName(imgTag);
	}

	/** convert content relative img src to absolute, and return good content*/
	public static String imgRelativeToAbsolute(String url,Document doc) throws IOException{
		String content = TextExtract.getContent(url,doc);
		List<String> imglist = TextExtract.getImgTag(content);
		for(String img:imglist){
			if(TextExtract.getImgSrc(img).indexOf("http://") == -1){
				content = content.replace(TextExtract.getSrcValue(img), getImgAbsoluteSrc(url, img));
			}
		}
		return content;
	}
	
	/** get the img list of content*/
	public static String getImgList(String url,Document doc) throws IOException{
		String allimg = "";
		String content = TextExtract.getContent(url,doc);
		List<String> imglist = TextExtract.getImgTag(content);
		for(int i=0;i<3 && i<imglist.size();i++){
			if(TextExtract.getImgSrc(imglist.get(i)).indexOf("http://") == -1){
				allimg += getImgAbsoluteSrc(url, imglist.get(i)) + "|";
			}else{
				allimg += TextExtract.getSrcValue(imglist.get(i)) + "|";
			}
		}
		return allimg;
	}
	
	
	//-----------------------------------------------------------------------main
	public static void main(String[] args) throws IOException{
		
//		String url = "http://news.sun0769.com/dg/headnews/201409/t20140917_4434458.shtml";
//		imgRelativeToAbsolute(url, TextExtract.getDocument(url));
//		for(NewsAbstract l:li){
//			System.out.println(l.getUrl()+"**"+l.getTitle()+"**"+l.getOriginalDate());
//		}
		
		
		/**更新*/
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("/configure/applicationContext.xml") ;  
		IDgService dgService = (IDgService) beanFactory.getBean("dgService");
		ESun0769 ex = new ESun0769();
		List<NewsAbstract> li = ex.getAll();
		dgService.updateNewsOfDG(li,"dgnewsabs_id","dgnewsabs","dgnewsdetail");
		System.out.println("dg ok");
		
//		Pagination<NewsAbstract> res = dgService.findNewsAbs(new PageFormBean());
//		for(NewsAbstract nabs:res.getObjLists()){
//			System.out.println(nabs.getTitle()+"+++"+dgService.getNewsDetailByUrl(nabs.getUrl()).getContent());
//		}
		
	}

}
