package com.rhcheng.news.webmagic;

import java.util.concurrent.CountDownLatch;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author RhCheng
 * @date   2014-12-2
 */
public class OverWriteSpider extends Spider{
	protected CountDownLatch cdl;//	sync main thread and sub thread 
	
	public OverWriteSpider(PageProcessor pageProcessor) {
		super(pageProcessor);
	}
	
	public CountDownLatch getCdl() {
		return cdl;
	}
	public OverWriteSpider setCdl(CountDownLatch cdl) {
		this.cdl = cdl;
		return this;
	}


	@Override
	public void close(){
		super.close();
		this.cdl.countDown();
		System.out.println("sub thread complete! thread name is: "+Thread.currentThread().getName());
	}
	
	public static OverWriteSpider create(PageProcessor pageProcessor) {
        return new OverWriteSpider(pageProcessor);
    } 
	
	@Override
	public void extractAndAddRequests(Page page, boolean spawnUrl){
		super.extractAndAddRequests(page, spawnUrl);
	}
	
}
