package com.rhcheng.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("messageProcess")
public class MessageProcess {
	@Autowired
	private JedisTemplate jt;
	
	public void addConnection(){
		if(jt.getJedis().sismember("userOnLinelist", "")){
			
		}else{
			
		}
	}
	
	
	
}
