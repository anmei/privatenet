package com.rhcheng.test;

//import java.net.URL;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;
//import net.sf.ehcache.Element;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rhcheng.user.dao.TestDao;

public class TestEhcache {
	private static Cache cache;
	private static TestDao ehcachedao;
	private static CacheManager manager;
	private static BeanFactory bf;
	static{
		bf = new ClassPathXmlApplicationContext("/configure/applicationContext.xml");
		ehcachedao = (TestDao)bf.getBean("testDao");
		manager = (CacheManager)bf.getBean("cacheManager");
		cache = manager.getCache("privatenet");  
	}
	
	public static void main(String[] args) throws InterruptedException{
		
//		URL url = TestEhcache.class.getResource("ehcache.xml");  
//	    CacheManager manager = new CacheManager(url);
		
		
//	    cache.put("find_haha", "a");
//	    cache.evict("find_haha");
//	    cache.clear();
		System.out.println(ehcachedao.getPar("haha"));
		Thread.sleep(5000);
		
		System.out.println(ehcachedao.getPar("haha"));
//		Thread.sleep(5000);
		
//		System.out.println(ehcachedao.find("haha"));
//		Thread.sleep(5000);
//		System.out.println(ehcachedao.find("haha1"));
//	    System.out.println(cache.get("find_haha"));
	    
//	    Element element = new Element("a","fsd");
//	    cache.put(element);
//		System.out.println(cache.get("a"));
//		Thread.sleep(5000);
//		System.out.println(cache.get("a"));
	}
}

