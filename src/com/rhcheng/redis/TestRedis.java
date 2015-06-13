package com.rhcheng.redis;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedis {
	JedisPool pool;
	Jedis jedis;
	
	@Before
	public void setUp(){
		pool = new JedisPool(new JedisPoolConfig(), "192.183.3.202");  
		jedis = pool.getResource(); 
	}
	
	@Test
	public void test1(){
		// CRUD
		System.out.println(jedis.get("abc"));
		
		
	}
	
}
