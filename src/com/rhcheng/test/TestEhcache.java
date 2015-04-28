package com.rhcheng.test;

//import java.net.URL;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rhcheng.user.dao.TestDao;
import com.rhcheng.user.service.TestService;

public class TestEhcache {
	
	public static void main(String[] args) throws InterruptedException{
		Cache cache;
		CacheManager manager;
		BeanFactory bf = new ClassPathXmlApplicationContext("/configure/applicationContext.xml");
//		TestDao ehcachedao = (TestDao)bf.getBean("testDao");
		TestService ts = (TestService)bf.getBean("testService");
//		manager = (CacheManager)bf.getBean("cacheManager");
//		cache = manager.getCache("tempCache");  
		
//		URL url = TestEhcache.class.getResource("ehcache.xml");  
//	    CacheManager manager = new CacheManager(url);
		
		
//	    cache.put("find_haha", "a");
//	    cache.evict("find_haha");
//	    cache.clear();
		System.out.println(ts.find("haha"));
		
		System.out.println(ts.find("haha"));

//		System.out.println(cache.get("find_haha"));
		
//		System.out.println(ehcachedao.find("haha"));
//		Thread.sleep(5000);
//		System.out.println(ehcachedao.find("haha1"));
//	    System.out.println(cache.get("find_haha"));
	    
//	    Element element = new Element("a","fsd");
//	    cache.put(element);
//		System.out.println(cache.get("a"));
//		Thread.sleep(5000);
//		System.out.println(cache.get("a"));
		
		System.out.println("ok");

	}
}

