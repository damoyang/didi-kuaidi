package com.ddhy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaiduMapService {
	public static String ak="77b25ae37a0e5d7be217a49dec46f773";
	RestTemplate restTemplate;
	public BaiduMapService(){
		restTemplate=new RestTemplate();
	}
	
}
