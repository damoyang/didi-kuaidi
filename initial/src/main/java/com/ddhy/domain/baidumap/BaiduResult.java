package com.ddhy.domain.baidumap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaiduResult {
	String status;
	String id;
	String message;
	

}
