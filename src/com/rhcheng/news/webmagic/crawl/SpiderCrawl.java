package com.rhcheng.news.webmagic.crawl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.news.webmagic.MyPipleLine;
import com.rhcheng.news.webmagic.OverWriteSpider;
import com.rhcheng.news.webmagic.pageprocessor.TaobaoItemPageProcessor;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;
/**
 * {@ us.codecraft.webmagic.Request}中存储的信息：Method、url、parameters
 * "nameValuePair",在Request中存于extras中，POST提交时用于存储key-value键值对
 * @see org.apache.http.NameValuePair
 * 
 * {@ us.codecraft.webmagic.Site}中存储的信息：user-agent、cookie等请求头信息，charset
 * 
 * @author anmei
 *
 */
public class SpiderCrawl {
	
	public void doCrawl(CountDownLatch countDownLatch) {
		// 设置请求头,编码等
		Site site = Site.me()
				.setSleepTime(0)
				.setUserAgent("Mozilla")
				//.addCookie("data", "jquery")
				.setTimeOut(10000)
				.setCharset("GB2312")
				.addHeader("Cookie", "uc1=cookie14=UoWyiPoJbJt/8g==&existShop=false&cookie16=Vq8l+KCLySLZMFWHxqs8fwqnEw==&cookie21=UIHiLt3xSarfJWFC &tag=0&cookie15=U+GCWk/75gdr5Q==&pas=0; v=0; cookie2=1c69e51a6b05095777992627b11ea377; _tb_token_=XUTJTPkLNGjucz3; existShop=MTQ1NjA0NTU5NA==; sg=o9d; cookie1=AQCY4gnNfVfOJben3oPzwFoyh4zwDml2YZQz4sgqyUs=; unb=2401205659; skt=add0381ace30ac7d; _l_g_=Ug==; _nk_=anmeigogogo; cookie17=UUwQl69T+VQk6A==")
				.addHeader("Content-Type", "application/x-www-form-urlencoded");
		
		Request request = new Request();
		// 请求方法
		request.setMethod("GET");
		// 请求url
		request.setUrl("http://www.baidu.com");
		// 请求form参数设置
		NameValuePair parameter1 = new BasicNameValuePair("sex", "男");
		List<NameValuePair> tmp = new ArrayList<NameValuePair>();
		tmp.add(parameter1);
		Map<String,Object> extras = new HashMap<String,Object>();
		extras.put("nameValuePair", tmp.toArray());
		request.setExtras(extras);
		
		BaseSpider pageProcessor = new TaobaoItemPageProcessor();
		pageProcessor.setSite(site);
		OverWriteSpider ows = OverWriteSpider.create(pageProcessor);
		ows.addRequest(request);
		ows.setEmptySleepTime(5000);// 此处有点疑问，感觉signalNewUrl();会唤醒waitNewUrl();但是结果却没有，让然一直等待EmptySleepTime时间再结束
		ows.setCdl(countDownLatch).setSpawnUrl(false)
		.addPipeline(new MyPipleLine())
		.setScheduler(new QueueScheduler().setDuplicateRemover(new HashSetDuplicateRemover()))
		.run();
		
		
	}
	
	
	
	
	
	
}
