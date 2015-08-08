package com.ddhy.web;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddhy.domain.Result;
import com.ddhy.domain.YybDriverPostion;
import com.ddhy.domain.YybUserPosition;
import com.ddhy.repository.*;

@RestController
public class LocationController {
	@Autowired
	DriverCarPositionRepository driverCarPositionRepository;
	@Autowired
	CustomerPositionRepository customerPositionRepository;
	@RequestMapping("/driver/updatepos")
	public Result updateDriverPosition(YybDriverPostion yybDriverPostion){
		Result result=new Result();
		yybDriverPostion.setYybLastmodifytime(new Timestamp(System.currentTimeMillis()));
		if(yybDriverPostion.getYybDriverid()==0||yybDriverPostion.getYybLat()==null||yybDriverPostion.getYybLong()==null){
			result.setErrMsg("data is incomplete");
		}else{
			driverCarPositionRepository.save(yybDriverPostion);
			//send to LBS
			//send to Cassandra
		}
		return result;		
	}
	@RequestMapping("/custom/updatepos")
	public Result updateCustomPosition(YybUserPosition yybUserPosition){
		Result result=new Result();
		yybUserPosition.setYybLastmodifytime(new Timestamp(System.currentTimeMillis()));
		if(yybUserPosition.getYybUserid()==0||yybUserPosition.getYybLat()==null||yybUserPosition.getYybLong()==null){
			result.setErrMsg("data is incomplete");
		}else{
			customerPositionRepository.save(yybUserPosition);
		}
		return result;
	}
	
	
	
}
