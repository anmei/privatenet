package com.rhcheng.jms.service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("messageProducer")
public class MessageProducer implements IMessageProducer{
//	@Autowired
//	private JmsTemplate jmsTemplate;
//	
//	@Override
//	public void sendMessage(Destination destination, final String message) {
//		jmsTemplate.send(destination, new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createTextMessage(message);
//			}
//		});
//	}

}
