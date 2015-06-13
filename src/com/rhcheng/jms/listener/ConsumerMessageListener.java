package com.rhcheng.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		TextMessage tm = (TextMessage)message;
		try {
			System.out.println("收到消息："+tm.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
