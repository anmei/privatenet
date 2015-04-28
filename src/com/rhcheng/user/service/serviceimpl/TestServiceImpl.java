package com.rhcheng.user.service.serviceimpl;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.rhcheng.user.dao.TestDao;
import com.rhcheng.user.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Resource
	private TestDao testDao;
	
	@Override
	@Cacheable(value="tempCache",key="'find_'+#para")
	public String find(String para) {
		return testDao.find(para);
	}

}
