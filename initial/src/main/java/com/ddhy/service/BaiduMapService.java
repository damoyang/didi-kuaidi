package com.ddhy.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.activemq.broker.MutableBrokerFilter;
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
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String ak = "DC6acaecdf0fc9e0b15b582b3c23bfb0";
	public static String geotable_id = "115314";
	public static int coord_type = 1;
	// 添加检索自己的poi
	public static String url_geoTableList = "http://api.map.baidu.com/geodata/v3/geotable/list";
	public static String url_addPOI = "http://api.map.baidu.com/geodata/v3/poi/create";
	public static String url_updatePOI = "http://api.map.baidu.com/geodata/v3/poi/update";

	// 根据地址获取经纬度
	private static String url_getAdd = "http://api.map.baidu.com/geocoder/v2/";

	// 根据经纬度获取距离
	public static String url_distance = "http://api.map.baidu.com/direction/v1/routematrix";
	public static String output = "json";

	static RestTemplate restTemplate;
	static {
		restTemplate = new RestTemplate();
	}

	public BaiduMapService() {
	}

	public void getTest() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				url_geoTableList).queryParam("ak", ak);
		URI uri = builder.build().encode().toUri();
		String baiduResult = restTemplate.getForObject(uri, String.class);
		System.out.println("ok");
	}

	public void updatePOI(String id, double lat, double lng) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("ak", ak);
		map.add("geotable_id", geotable_id);
		map.add("latitude", lat);
		map.add("longitude", lng);
		map.add("coord_type", coord_type);
		map.add("id", id);
		String result = restTemplate.postForObject(url_updatePOI, map,
				String.class);
	}

	public String addPOI(double lat, double lng,String brand) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("ak", ak);
		map.add("geotable_id", geotable_id);
		map.add("latitude", lat);
		map.add("longitude", lng);
		map.add("coord_type", coord_type);
		map.add("title", brand);
		String baiduResult = restTemplate.postForObject(url_addPOI, map,
				String.class);
		try {
			Map<String, Object> re = objectMapper.readValue(baiduResult,
					Map.class);
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

	/**
	 * get method
	 * @param origin
	 *            40.23,123.4
	 * @param destination
	 *            12,23,123.4
	 * @return
	 */
	public Double calculateDistance(String origin, String destination) {
		// must use getMethod
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl(url_distance).queryParam("ak", ak)
				.queryParam("output", output).queryParam("origins", origin)
				.queryParam("destinations", destination);
		URI uri = builder.build().encode().toUri();

		String baiduResult = restTemplate.getForObject(uri, String.class);
		try {
			Map<String, Object> re = objectMapper.readValue(baiduResult,
					Map.class);
			Map<String, Object> result = (Map<String, Object>) re.get("result");
			List<Map<String, Object>> elements = (List<Map<String, Object>>) result
					.get("elements");
			Map<String, Object> disMap=(Map<String, Object>) elements.get(0).get("distance");
			Integer meters=(Integer) disMap.get("value");
			return meters/1000+0.0;
		} catch (Exception e) {
			return -1.0;
		}
	}

	public String calculateLatLng(String addr, String city) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("ak", ak);
		map.add("output", output);
		map.add("address", addr);
		map.add("city", city);
		String baiduResult = restTemplate.postForObject(url_getAdd, map,
				String.class);
		try {
			Map<String, Object> reMap = objectMapper.readValue(baiduResult,
					Map.class);
			Map<String, Object> resultMap = (Map<String, Object>) reMap
					.get("result");
			Map<String, Object> locationMap = (Map<String, Object>) resultMap
					.get("location");
			return ((double) locationMap.get("lat")) + ","
					+ ((double) locationMap.get("lng"));
		} catch (Exception e) {
			return null;
		}
	}

}
