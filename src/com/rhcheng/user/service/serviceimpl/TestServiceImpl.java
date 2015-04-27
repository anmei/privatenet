package com.rhcheng.user.service.serviceimpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rhcheng.user.dao.TestDao;
import com.rhcheng.user.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService{
	@Resource
	private TestDao testDao;
	
	@Override
	public String find(String para) {
		return testDao.getPar(para);
	}

}
