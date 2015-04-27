package com.rhcheng.user.dao;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository("testDao")
public class TestDao{
	
	/**
	 * 测试ehcache缓存
	 * @author RhCheng
	 * @date 2015-4-27
	 * @param para
	 * @return
	 */
	@Cacheable(value="tempCache",key="'find_'+#para")
	public String find(String para){
		System.out.println("get from db");
		return "a";
	}
	
}
