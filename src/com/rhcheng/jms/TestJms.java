package com.rhcheng.jms;

import javax.jms.Destination;

import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.beans.factory.annotation.Qualifier;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

import com.rhcheng.jms.service.IMessageProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/configure/applicationContext.xml"})
public class TestJms {
	@Autowired
	private IMessageProducer messageProducer;
	@Autowired
	@Qualifier("queueDestination")  
	private Destination queueDestination;
	
	
	@Test
	public void testSendMes(){
		for (int i=0; i<2; i++) {  
			messageProducer.sendMessage(queueDestination, "你好，生产者！这是消息：" + (i+1));  
        }  
		
	}
}
