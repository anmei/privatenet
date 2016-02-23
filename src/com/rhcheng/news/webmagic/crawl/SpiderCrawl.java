package com.rhcheng.news.webmagic.crawl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import com.rhcheng.news.webmagic.BaseSpider;
import com.rhcheng.news.webmagic.CrawlModel;
import com.rhcheng.news.webmagic.MyPipleLine;
import com.rhcheng.news.webmagic.OverWriteSpider;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.component.HashSetDuplicateRemover;
import us.codecraft.webmagic.selector.thread.CountableThreadPool;
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
@Component
public class SpiderCrawl {
	
//        public void craw(final String charset,final String method,final Map<String,Object> urls2pageProcessor,
//            final Map<String,String> headers,final Map<String,String> parameters,final CountDownLatch countDownLatch,
//            final Integer threadNum){
	
		/**
		 * 抓取统一入口
		 * @param crawlModel
		 * @param countDownLatch
		 * @param threadNum
		 */
        public void craw(final Map<String,CrawlModel> crawlModels,final CountDownLatch countDownLatch,final Integer threadNum){
            
            CountableThreadPool threadPool = new CountableThreadPool(threadNum == null ? 1:threadNum);
            final CountDownLatch mycountDownLatch = (countDownLatch == null ? new CountDownLatch(crawlModels.size()):countDownLatch);
            
            for(final Map.Entry<String, CrawlModel> cm:crawlModels.entrySet()){
                threadPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                // every url will new a {@code Spider} instance, a {@code PageProcessor} instance 
                                // and produce a new thread pool
                                doCommonCrawl(cm.getValue().getCharset(), cm.getValue().getMethod(), cm.getKey(), 
                                    cm.getValue().getHeaders(), cm.getValue().getParameters(), 
                                    cm.getValue().getPageProcessor(), mycountDownLatch);
                            } catch (InstantiationException e) {
                                    e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                            } 
                                
                        }
                });
            }            
            
            try {
                mycountDownLatch.await();
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                threadPool.shutdown();
                System.out.println("--------------->main thread crawl completed! thread name is: "+Thread.currentThread().getName());
            }
	    
	}
    
    
    /**
     * 简单抓取，抓取的过程中不会在页面中寻找新的URL
     * @param charset
     * @param method
     * @param url
     * @param headers
     * @param parameters
     * @param pageProcessorClass
     * @param countDownLatch
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
	public void doCrawl(String charset,String method,String url,Map<String,String> headers,Map<String,String> parameters,
	    Class<? extends BaseSpider> pageProcessorClass,CountDownLatch countDownLatch) throws InstantiationException, IllegalAccessException {
		// 设置请求头,编码等
		Site site = Site.me()
				.setSleepTime(0)
				.setUserAgent("Mozilla")
				//.addCookie("data", "jquery")
				.setTimeOut(10000)
				.setCharset(charset);
		if(null != headers){
		    for(Map.Entry<String, String> entry:headers.entrySet()){
		        site.addHeader(entry.getKey(), entry.getValue());
		    }
		}
		
		Request request = new Request();
		// 请求方法
		request.setMethod(method.toUpperCase());
		// 请求url
		request.setUrl(url);
		// 请求form参数设置
		if(null != parameters){
		    List<NameValuePair> tmp = new ArrayList<NameValuePair>();
		    for(Map.Entry<String, String> entry:parameters.entrySet()){
		        NameValuePair parameter1 = new BasicNameValuePair(entry.getKey(), entry.getValue());
		        tmp.add(parameter1);
		    }
		    Map<String,Object> extras = new HashMap<String,Object>();
		    extras.put("nameValuePair", tmp.toArray());
	            request.setExtras(extras);
		}
		
		BaseSpider pageProcessor = pageProcessorClass.newInstance();
		pageProcessor.setSite(site);
		if(null != countDownLatch){
    		    OverWriteSpider ows = OverWriteSpider.create(pageProcessor);
    		    ows.addRequest(request);
                    ows.setEmptySleepTime(5000);// 此处有点疑问，感觉signalNewUrl();会唤醒waitNewUrl();但是结果却没有，让然一直等待EmptySleepTime时间再结束
                    ows.setCdl(countDownLatch).setSpawnUrl(false)
                    .addPipeline(new MyPipleLine())
                    .setScheduler(new QueueScheduler().setDuplicateRemover(new HashSetDuplicateRemover()))
                    .run();
		}else{
		    Spider ows = Spider.create(pageProcessor);
                    ows.addRequest(request);
                    ows.setEmptySleepTime(5000);// 此处有点疑问，感觉signalNewUrl();会唤醒waitNewUrl();但是结果却没有，让然一直等待EmptySleepTime时间再结束
                    ows.setSpawnUrl(false)
                    .addPipeline(new MyPipleLine())
                    .setScheduler(new QueueScheduler().setDuplicateRemover(new HashSetDuplicateRemover()))
                    .run();
		}
		System.out.println("--------------->sub thread finished. ");
		
	}
	
	
	/**
	 * 复杂抓取，抓取过程中可以在页面中寻找新的URL并抓取。递归抓取
	 * @param charset
	 * @param method
	 * @param url
	 * @param headers
	 * @param parameters
	 * @param pageProcessorClass
	 * @param countDownLatch
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void doCommonCrawl(String charset,String method,String url,Map<String,String> headers,Map<String,String> parameters,
            Class<? extends BaseSpider> pageProcessorClass,CountDownLatch countDownLatch) throws InstantiationException, IllegalAccessException{
	    // 设置请求头,编码等
        Site site = Site.me()
                .setSleepTime(0)
                .setUserAgent("Mozilla")
                //.addCookie("data", "jquery")
                .setTimeOut(10000)
                .setCharset(charset);
        if(null != headers){
            for(Map.Entry<String, String> entry:headers.entrySet()){
                site.addHeader(entry.getKey(), entry.getValue());
            }
        }
	    BaseSpider pageProcessor = pageProcessorClass.newInstance();
            pageProcessor.setSite(site);
            pageProcessor.doSpide(charset, method, url, null, parameters, null, countDownLatch);
            System.out.println("--------------->sub thread finished. ");
	}
	
	
	
}
