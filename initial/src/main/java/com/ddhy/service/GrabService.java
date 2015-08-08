package com.ddhy.service;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

/**
 * 抢单功能的实现
 * 可以依赖互斥访问的hash完成
 * 也可以依赖一个消息队列来实现
 * @author houweichao
 *
 */
@Service
public class GrabService {
	private static ConcurrentHashMap<String, Boolean> concurrentHashMap=new ConcurrentHashMap<>();
	
	public boolean grabOrder(String orderno){
		Boolean res=concurrentHashMap.putIfAbsent(orderno, true);
		if(res==null){//之前没有值，抢单成功
			return true;
		}else{//之前有值,已经被抢
			return false;
		}
	}

	public void clearOrder(String yybOrderno) {
		concurrentHashMap.remove(yybOrderno);
	}
	

}
