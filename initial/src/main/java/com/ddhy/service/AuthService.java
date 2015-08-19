package com.ddhy.service;

import java.util.Map;

import org.apache.activemq.broker.MutableBrokerFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthService {
	static private String url="http://apis.haoservice.com/idcard/VerifyIdcard?key=%s&cardNo=%s&realName=%s";
	static private String ak="0d4ff625405c4d8e866136f78613e000";
	public AuthService(){
		
	}
	public boolean isValid(String idCard,String name){
		RestTemplate restTemplate=new RestTemplate();
		String trueurl=String.format(url, ak,idCard,name);
		String re=restTemplate.getForObject(trueurl,String.class);
		ObjectMapper mapper=new ObjectMapper();
		try{
			Map<String, Object> reMap=mapper.readValue(re, Map.class);
			Map<String, Object> sucMap=(Map<String, Object>)reMap.get("result");
			if(sucMap==null) return false;
			else {
				boolean suc=(boolean)sucMap.get("isok");
				return suc;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

}
