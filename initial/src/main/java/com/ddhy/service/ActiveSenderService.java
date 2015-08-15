package com.ddhy.service;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.ddhy.AppRun;
import com.ddhy.domain.YybBussOrder;

@Service
public class ActiveSenderService {
	public ActiveSenderService(){
	}
	/**
	 * orderconfirm
	 * each id is target
	 */
	public void pushToDriver(String target,YybBussOrder order){
		JmsTemplate jmsTemplate=AppRun.getContext().getBean(JmsTemplate.class);

		jmsTemplate.convertAndSend(target, order);
	}
	

}
