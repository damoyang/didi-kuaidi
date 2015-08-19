package com.ddhy.web;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddhy.domain.YybResult;
import com.ddhy.domain.YybBussOrder;
import com.ddhy.domain.YybBussTraderecord;
import com.ddhy.repository.*;
import com.ddhy.service.ActiveSenderService;
import com.ddhy.service.GrabService;


@RestController
public class BussinessController {
	@Autowired
	ActiveSenderService jmsService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	GrabService grabService;
	/**
	 * TODO baidu search
	 * TODO activeMQ
	 * TODO 
	 */
	/**
	 * 标志下单状态为已下单
	 * 
	 */
	@RequestMapping("/busi/orderconfirm")
	YybResult orderConfirm(YybBussOrder yybBussOrder){
		YybResult result=new YybResult();
		YybBussOrder trueOrder=orderRepository.findByOrderNo(yybBussOrder.getYybOrderno());
		if(trueOrder==null){
			result.setErrMsg("订单号不正确");
			result.setStatus(1);
			return result;
		}else if(!trueOrder.getYybOrderstatus().equals("未确认")){
			result.setErrMsg("订单状态异常");
			result.setStatus(1);
			return result;
		}
		trueOrder.setYybOrderstatus("已下单");
		orderRepository.save(trueOrder);
		//TODO baiduMap
		
		jmsService.pushToDriver("22", trueOrder);
		return result;
	}
	/**
	 * 
	 */
	
	/**
	 * 用户发出送货交易请求，
	 * 在数据库中要存储交易请求
	 * 并且向该城市队列发送消息
	 * 下单
	 */
	@RequestMapping("/busi/orderrequest")
	YybResult orderRequest(YybBussOrder order){
		String type="";
		YybResult result=new YybResult();
		//生成订单
		order.init();
		order=orderRepository.save(order);
		//生成账单
		YybBussTraderecord yybBussTraderecord=new YybBussTraderecord(order.getYybOrderno(),type);
		yybBussTraderecord.init();
		yybBussTraderecord=tradeRepository.save(yybBussTraderecord);
		Map<String, Object> reMap=new HashMap<>();
		reMap.put("order", order);
		reMap.put("trade", yybBussTraderecord);
		result.setData(reMap);
		return result;
	}
	/**
	 * 推送
	 * current all 
	 * 
	 * 
	 */
	@RequestMapping("/busi/orderlist")
	YybResult orderlist(Integer yybId){
		YybResult result=new YybResult();
		//TODO more choise
		List<YybBussOrder> lists=orderRepository.findByOrderStatus("已下单");
		//TODO more 
		result.setData(lists);
		return result;
	}
	/**
	 * 历史订单
	 * current all 
	 * 
	 * 
	 */
	@RequestMapping("/busi/historyorderlist")
	YybResult orderlistHis(Integer yybId){
		YybResult result=new YybResult();
		//TODO more choise
		List<YybBussOrder> lists=orderRepository.findByDriverId(yybId);
		//TODO more 
		result.setData(lists);
		return result;
	}
	/**
	 * 我的订单
	 * current all 
	 * 
	 * 
	 */
	@RequestMapping("/busi/customorderlist")
	YybResult orderlistCus(Integer yybId){
		YybResult result=new YybResult();
		//TODO more choise
		List<YybBussOrder> lists=orderRepository.findByUserId(yybId);
		//TODO more 
		result.setData(lists);
		return result;
	}
	/**
	 * 推送抢单
	 * 
	 * 
	 */
	@RequestMapping("/busi/orderinfo")
	YybResult orderByGrab(YybBussOrder yybBussOrder){
		YybResult result=new YybResult();
		YybBussOrder trueOrder=orderRepository.findByOrderNo(yybBussOrder.getYybOrderno());
		if(yybBussOrder.getYybOrderno()==null||trueOrder==null){
			result.setErrMsg("订单号不对");
			result.setStatus(2);
			return result;
		}else if(trueOrder.getYybOrderstatus().equals("已抢单")){
			result.setData(trueOrder);
			return result;
		}else{
			result.setErrMsg("未被抢单");
			result.setStatus(1);
			return result;
		}
	}
	/**
	 * 抢单
	 * @param order
	 * @return
	 */
	@RequestMapping("/busi/orderresponse")
	YybResult orderResponse(YybBussOrder order){
		YybResult result=new YybResult();		
		if(order.getYybOrderno()==null||order.getYybDriverid()==0){
			result.setErrMsg("数据不完整");
			result.setStatus(2);
			return result;
		}
		if(grabService.grabOrder(order.getYybOrderno())){
			YybBussOrder trueOrder=orderRepository.findByOrderNo(order.getYybOrderno());
			if(trueOrder.getYybDriverid()!=0){//some one has grab it
				result.setErrMsg("订单已经被抢");
				result.setStatus(1);
				return result;
			}
			trueOrder.setYybDriverid(order.getYybDriverid());
			trueOrder.setYybCarlicense(order.getYybCarlicense());
			trueOrder.setYybDrivername(order.getYybDrivername());
		    trueOrder.setYybOrderstatus("已抢单");
			orderRepository.saveAndFlush(trueOrder);
			grabService.clearOrder(order.getYybOrderno());
			result.setData(trueOrder);
			//TODO
			return result;
		}else{
			result.setErrMsg("订单已经被抢");
			result.setStatus(1);
			return result;
		}
	}
	/**
	 * 支付成功
	 * 
	 */
	@RequestMapping("/busi/paysuc")
	YybResult paySuccess(String orderNo){
		YybResult result=new YybResult();
		YybBussOrder yybOrder=orderRepository.findByOrderNo(orderNo);
		//update state
		yybOrder.setYybOrderstatus("已支付");
		yybOrder.setYybOrdertime(new Timestamp(System.currentTimeMillis()));
		orderRepository.save(yybOrder);
		//send to each car
		return result;
	}
	
	
}
