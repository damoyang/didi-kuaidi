package com.ddhy.web;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddhy.domain.Result;
import com.ddhy.domain.YybBussOrder;
import com.ddhy.domain.YybBussTraderecord;
import com.ddhy.repository.*;
import com.ddhy.service.GrabService;


@RestController
public class BussinessController {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	GrabService grabService;
	/**
	 * 用户发出送货交易请求，
	 * 在数据库中要存储交易请求
	 * 并且向该城市队列发送消息
	 */
	@RequestMapping("/busi/orderrequest")
	Result orderRequest(YybBussOrder order,String type){
		Result result=new Result();
		order.init();
		orderRepository.save(order);
		//生成账单
		YybBussTraderecord yybBussTraderecord=new YybBussTraderecord(order.getYybOrderno(),type);
		yybBussTraderecord.init();
		tradeRepository.save(yybBussTraderecord);
		return result;
	}
	/**
	 * 抢单
	 * @param order
	 * @return
	 */
	@RequestMapping("/busi/orderresponse")
	Result orderResponse(YybBussOrder order){
		Result result=new Result();		
		if(order.getYybOrderno()==null||order.getYybDriverid()==0){
			result.setErrMsg("data is incomplete");
			return result;
		}
		if(grabService.grabOrder(order.getYybOrderno())){
			YybBussOrder trueOrder=orderRepository.findByOrderNo(order.getYybOrderno());
			if(trueOrder.getYybDriverid()!=0){//some one has grab it
				result.setSuccess(false);
				return result;
			}
			trueOrder.setYybDriverid(order.getYybDriverid());
			trueOrder.setYybCarlicense(order.getYybCarlicense());
			trueOrder.setYybDrivername(order.getYybDrivername());
		    trueOrder.setYybOrderstatus("已抢单");
			orderRepository.saveAndFlush(trueOrder);
			grabService.clearOrder(order.getYybOrderno());
			
			//TODO 
			return result;
		}else{
			result.setSuccess(false);
			return result;
		}
	}
	/**
	 * 支付成功
	 * 
	 */
	@RequestMapping("/busi/paysuc")
	Result paySuccess(String orderNo){
		Result result=new Result();
		YybBussOrder yybOrder=orderRepository.findByOrderNo(orderNo);
		//update state
		yybOrder.setYybOrderstatus("已支付");
		yybOrder.setYybOrdertime(new Timestamp(System.currentTimeMillis()));
		orderRepository.save(yybOrder);
		//send to each car
		
		
		return result;
	}
	
	
}
