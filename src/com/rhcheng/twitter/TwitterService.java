package com.rhcheng.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhcheng.redis.JedisTemplate;

@Service("twitterService")
public class TwitterService implements ITwitterService{
	@Autowired
	private JedisTemplate jt;

	
	
	
	
}
