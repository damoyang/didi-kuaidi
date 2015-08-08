package com.ddhy.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.ddhy.AppRun;

public class Sender {
	public void send(String target) {
		MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("test");
			}
		};
		JmsTemplate jmsTemplate = AppRun.getContext().getBean(JmsTemplate.class);
		jmsTemplate.send(target, messageCreator);
	}
}
