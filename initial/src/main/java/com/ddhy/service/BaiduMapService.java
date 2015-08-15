package com.ddhy.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ddhy.domain.baidumap.BaiduResult;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler.Builder;
import com.fasterxml.jackson.databind.util.JSONPObject;

@Service
public class BaiduMapService {
	public static String ak="77b25ae37a0e5d7be217a49dec46f773";
	public static String geotable_id="116591";
	public static int coord_type=1;
	public static String url_geoTableList="http://api.map.baidu.com/geodata/v3/geotable/list";
	public static String url_addPOI="http://api.map.baidu.com/geodata/v3/poi/create";
	public static String url_updatePOI="http://api.map.baidu.com/geodata/v3/poi/update";
	
	static RestTemplate restTemplate;
	static {
		restTemplate=new RestTemplate();
	}
	public BaiduMapService(){
	}
	public void getTest(){
		UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(url_geoTableList).queryParam("ak", ak);
		URI uri=builder.build().encode().toUri();
		String baiduResult=restTemplate.getForObject(uri, String.class);
		System.out.println("ok");
	}
	public void updatePOI(String id,double lat,double lng){
//		UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(url_updatePOI)
//				.queryParam("ak", ak)
//				.queryParam("geotable_id", geotable_id)
//				.queryParam("latitude", lat)
//				.queryParam("longitude", lng)
//				.queryParam("coord_type", coord_type)
//				.queryParam("id", id);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
		map.add("ak", ak);
		map.add("geotable_id", geotable_id);
		map.add("latitude", lat);
		map.add("longitude", lng);
		map.add("coord_type", coord_type);
		map.add("id", id);
//		String text=builder.build().encode().toUriString();
	    String result = restTemplate.postForObject(url_updatePOI,map,String.class);
	}
	public String addPOI(double lat,double lng){
//		UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(url_addPOI)
//				.queryParam("ak", ak)
//				.queryParam("geotable_id", geotable_id)
//				.queryParam("latitude", lat)
//				.queryParam("longitude", lng)
//				.queryParam("coord_type", coord_type);
		MultiValueMap<String, Object> map=new LinkedMultiValueMap<>();
		map.add("ak", ak);
		map.add("geotable_id", geotable_id);
		map.add("latitude", lat);
		map.add("longitude", lng);
		map.add("coord_type", coord_type);
		String baiduResult=restTemplate.postForObject(url_addPOI,map,String.class);
		ObjectMapper objectMapper=new ObjectMapper();
		try {
			Map<String, Object> re=objectMapper.readValue(baiduResult, Map.class);
			return String.valueOf(re.get("id"));
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}
	
}

