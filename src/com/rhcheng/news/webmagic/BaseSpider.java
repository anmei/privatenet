package com.rhcheng.news.webmagic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;

import com.rhcheng.common.MyConstant;
import com.rhcheng.news.entity.NewsAbstract;
import com.rhcheng.news.entity.NewsDetails;
import com.rhcheng.news.imgproc.DefaultImageProcess;
import com.rhcheng.util.BaseWebUtil;
import com.rhcheng.util.file.FileInfo;
import com.rhcheng.util.string.NewsUtils;

/**
 * base spider, maybe call it a base pageProcessor is more exactly</br>
 * just a spider will start here also  </br>
 * 
 * normally every time start a spider only have one spider thread and some pageprocessor,pipeLine threads</br>
 * always, pageProcessor and piplLine thread is one to one correspondence</br>
 * @author RhCheng
 * @date   2014-9-25
 */
public abstract class BaseSpider implements PageProcessor{
	private String[] urls;// start spide urls
	protected String content = ""; // store the news content
	public BaseSpider(){}
	public ThreadLocalVariable thv = null;
	private QueueScheduler queueScheduler = new QueueScheduler();
	private OverWriteSpider oversp;
	private Spider sp;
	
	/** spider profile*/
	private Site site = Site.me()
			.setSleepTime(0)
			.setUserAgent("Mozilla")
			.addCookie("data", "jquery")
			.setTimeOut(10000);
	
	
	/**
	 * spide and result will be in {@code res} or {@code content}
	 * @see SpiderEntrance#AllNewsList
	 * @see NewsContentProcessor#content
	 * @author RhCheng
	 * @date 2014-9-25
	 * @param url start spide url
	 * @param charset chaset when spide {@code url}
	 */
	public void doSpide(String charset,String... url) {
		NewsContentProcessor.content="";
		
		if(StringUtils.isNotBlank(charset)){this.getSite().setCharset(charset);}
		sp = Spider.create(this);
		sp.addUrl(url)
		.addPipeline(new MyPipleLine())
		.setScheduler(this.queueScheduler.setDuplicateRemover(new HashSetDuplicateRemover()))
		.run();

	}
	
	public void doSpide(String charset,CountDownLatch countDownLatch,String... url) {
		NewsContentProcessor.content="";
		
		if(StringUtils.isNotBlank(charset)){this.getSite().setCharset(charset);}
		oversp = OverWriteSpider.create(this);
		oversp.setEmptySleepTime(5000);// 此处有点疑问，感觉signalNewUrl();会唤醒waitNewUrl();但是结果却没有，让然一直等待EmptySleepTime时间再结束
		oversp.setCdl(countDownLatch).addUrl(url).setSpawnUrl(false)
		.addPipeline(new MyPipleLine())
		.setScheduler(this.queueScheduler.setDuplicateRemover(new HashSetDuplicateRemover()))
		.run();

	}

	
	/**
	 * initial a spider and return it, you can customize the spider and then run it
	 * @author RhCheng
	 * @date 2014-9-25
	 * @param url start spide url
	 * @param charset chaset when spide {@code url}
	 * @return {@code Spider} object
	 */
	public Spider getSpide(String charset) {
		NewsContentProcessor.content="";
		Spider sp;
		if(StringUtils.isNotBlank(charset)){this.getSite().setCharset(charset);}
		sp = Spider.create(this);
		sp.addPipeline(new MyPipleLine());
		return sp;

	}
	
	
	
	@Override
	public void process(Page page) {
		if(this.thv == null){
			this.thv = new ThreadLocalVariable(page.getRequest().getUrl());
		}
		try{
			detailProcess(page);
		}catch(RuntimeException e){
			e.printStackTrace();
		}finally{
			
		}
		

	}
	
	public abstract void detailProcess(Page page) throws RuntimeException;
	
	
	
	
	//-------------------------------------------utils may be used in different PageProcessor implementation
	
	/**
	 * get date of news abstract from page content
	 * note:date format must match 'yyyy-MM-dd HH:mm'
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param content page content
	 * @return a string
	 */
	public String getOriginalDateOfNewsAbs(String content){
		return NewsUtils.getDate(content);
	};
	
	/**
	 * get date of news detail from page content
	 * 
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param content page content
	 * @return a string if null return curent date
	 */
	public String getOriginalDateOfNewsDetail(String content){
		return NewsUtils.getDateInNewsDetail(content);
	};
	
	
	/**
	 * get the {@code Date} parsed from {@code BaseSpider#getOriginalDate(String)}
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param date
	 * @return {@code Date}
	 */
	public Date getPublishDate(String date){
		return NewsUtils.parseDate(date);
	};
	/**
	 * judge if the page content is newest
	 * ie. whether interval between {@code date} and {@code new Date()} is less
	 * than {@code NewsUrls.INTERVAL}
	 * 
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param date content publish date 
	 * @return is newest return {@code true} or return {@code false}
	 */
	@Deprecated
	public boolean ifnewest(Date date){
		return true;
	};
	
	/**
	 * convert content relative img src to absolute, and return good content
	 * @author RhCheng
	 * @date 2014-12-3
	 * @param url request url of this page
	 * @param content news detail content
	 * @return good news detail
	 */
	public abstract String imgRelativeToAbsolute(String url,String content);
	
	
	
	/**
	 * 图片压缩剪切等处理
	 * @author RhCheng
	 * @date 2014-12-26
	 * @param content
	 * @return
	 */
	public String contentAndImgProc(NewsDetails newsdet,NewsAbstract newsabs){
		String temcontent = newsdet.getContent();
		temcontent = NewsUtils.replaceSomeChar(temcontent);
		if(this.checkNewsDetail(newsdet)){
			// 下载图片到本地并进行本地处理
			String[] imgpaths = newsabs.getImgPath().split("\\|");
			FileInfo fileinfo = new FileInfo();
			int k=0;
			for(int i=0;i<imgpaths.length && i<6;i++){
				if(StringUtils.isNotBlank(imgpaths[i])){
					fileinfo.setStoreFileName(newsabs.getImgUuid()+"_"+i);
					FileInfo localOriginalFile = DefaultImageProcess.downLoadImage(imgpaths[i],fileinfo);
					FileInfo localcompressedFile = DefaultImageProcess.compressImage(localOriginalFile);
					if(k==0){
						FileInfo localListviewFile = DefaultImageProcess.cutImagetoListview(localcompressedFile);
					}
					if(localcompressedFile != null){
						temcontent = temcontent.replaceAll(imgpaths[i], BaseWebUtil.getServletContext().getContextPath()+MyConstant.LOCAL_COMPRESSED_ACCESS+localcompressedFile.getStoreFileName());
					}
					k++;
				}
			}
		}
		return temcontent;
	}
	
	
	
	/**
	 * get all images absolute src value which can access through web 
	 * @author RhCheng
	 * @date 2014-9-26
	 * @param url current page request url
	 * @param content page content
	 * @return a string split by "|"
	 */
	public String getImgPath(String url,String content){
		String allimg = "";
		List<String> imgtaglist = NewsUtils.getImgTag(content);
		for(int i=0;i<imgtaglist.size() && i<6;i++){
			if(NewsUtils.getImgSrc(imgtaglist.get(i)).indexOf("http://") == -1){
				allimg += NewsUtils.getImgAbsoluteSrcValue(url, imgtaglist.get(i)) + "|";
			}else{
				allimg += NewsUtils.getSrcValue(imgtaglist.get(i)) + "|";
			}
		}
		return allimg;
	}
	
	
	/**
	 * filter and store the news list
	 * @author RhCheng
	 * @date 2014-11-28
	 * @param newsmap
	 * @param newsmapbyurl
	 * @param obj
	 * @param pageProcessor
	 */
	public void checkAndPutNewsAbs(NewsAbstract obj,String pageProcessor){
		if(this.checkNewsAbstact(obj)){
			if(SpiderEntrance.AllNewsListByPageProcess.containsKey(pageProcessor)){
				SpiderEntrance.AllNewsListByPageProcess.get(pageProcessor).add(obj);
			}else{
				List<NewsAbstract> newList = new ArrayList<NewsAbstract>();
				newList.add(obj);
				SpiderEntrance.AllNewsListByPageProcess.put(pageProcessor, newList);
			}
			
			if(SpiderEntrance.AllUrlsListByPageProcess.containsKey(pageProcessor)){
				SpiderEntrance.AllUrlsListByPageProcess.get(pageProcessor).add(obj.getUrl());
			}else{
				List<String> newurl = new ArrayList<String>();
				newurl.add(obj.getUrl());
				SpiderEntrance.AllUrlsListByPageProcess.put(pageProcessor, newurl);
			}
			
			SpiderEntrance.AllNewsByUrl.put(obj.getUrl(), obj);
			SpiderEntrance.AllNewsList.add(obj);
			
		}
	}
	
	
	
	/**
	 * filter and store the news detail
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param allNewsDetailList
	 * @param newsdet
	 * @param pubdate
	 */
	public void checkAndPutNewsDetail(NewsDetails newsdet){
		if(this.checkNewsDetail(newsdet)){
			SpiderEntrance.AllNewsDetailList.add(newsdet);
			
		}
		
	}

	
	/**
	 * check and filter news abstract
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param url
	 * @return
	 */
	private boolean checkNewsAbstact(NewsAbstract obj){
		if(obj.getUrl().indexOf("、") != -1 ||
				(obj.getDate() != null && !NewsUtils.ifok(obj.getDate())) ){
			return false;
		}else{
			
			synchronized (this.getClass()) {
				for(Entry<String, NewsAbstract> ent:SpiderEntrance.AllNewsByUrl.entrySet()){
					if(ent.getValue().getUrl().equalsIgnoreCase(obj.getUrl()) || 
							ent.getValue().getTitle().equalsIgnoreCase(obj.getTitle())){
						return false;
					}
				}
				return true;
			}
			
		}
	}
	
	/**
	 * check and filter news detail
	 * @author RhCheng
	 * @date 2014-12-2
	 * @param newsabs
	 * @param newsdet
	 * @return
	 */
	private boolean checkNewsDetail(NewsDetails newsdet){
		if(newsdet.getDate()!=null && !NewsUtils.ifok(newsdet.getDate()) || 
				StringUtils.isBlank(newsdet.getContent())){
			return false;
		}else{
			return true;
		}
	}
	
	
	//--------------------------------------------------------------------judge if the last page
	// Spider Run 方法循环有些问题，故通过getLeftRequestsCount方法无法精确获取剩余的url，此处自己写了一些方法，
	// 注意：只适用于本应用
	protected int totalSize = 1; 
	
	public void addTargetUrls(List<String> requesturl,Page page){
		if(thv.getF() == 1){
			if(requesturl.size()>0){
				page.addTargetRequests(requesturl);
				this.getOversp().extractAndAddRequests(page, true);
			}
			thv.fAddOne();
			this.totalSize = this.totalSize + requesturl.size();
		}
	}
	
	public Boolean isLast(){
		this.totalSize--;
		return (this.totalSize==0);
		
	}
	
	

	public String[] getUrls() {
		return urls;
	}


	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	@Override
	public Site getSite() {
		return site;
	}


	public void setSite(Site site) {
		this.site = site;
	}

	public ThreadLocalVariable getThv() {
		return thv;
	}

	public void setThv(ThreadLocalVariable thv) {
		this.thv = thv;
	}

	public QueueScheduler getQueueScheduler() {
		return queueScheduler;
	}

	public void setQueueScheduler(QueueScheduler queueScheduler) {
		this.queueScheduler = queueScheduler;
	}

	public OverWriteSpider getOversp() {
		return oversp;
	}

	public void setOversp(OverWriteSpider oversp) {
		this.oversp = oversp;
	}

	public Spider getSp() {
		return sp;
	}

	public void setSp(Spider sp) {
		this.sp = sp;
	}
	
	
}
