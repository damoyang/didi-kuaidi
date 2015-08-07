package com.ddhy.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.ddhy.AppRun;

public class Sender {
	public void send() {
		MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("ping!");
			}
		};
		JmsTemplate jmsTemplate = AppRun.getContext().getBean(JmsTemplate.class);
		System.out.println("Sending a new message.");
		jmsTemplate.send("mailbox-destination", messageCreator);
	}
}
