package com.rhcheng.redis;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("/configure/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedis {
	
	@Autowired
	private JedisTemplate jt;
	
	@Before
	public void setUp(){
//		pool = new JedisPool(new JedisPoolConfig(), "192.183.3.202");  
//		jedis = pool.getResource(); 
	}
	
	@Test
	public void test1(){
		// CRUD
//		jt.set("userid", "1");
//		jt.set("blogid", "1");
//		jt.set("commentid", "1");
//		System.out.println(jt.get("userid"));
//		System.out.println(jt.get("blogid"));
//		System.out.println(jt.get("commentid"));
		
		System.out.println(jt.llen("user"));
		
	}
	
}
