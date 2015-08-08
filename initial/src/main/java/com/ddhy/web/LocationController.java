package com.ddhy.web;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddhy.domain.Result;
import com.ddhy.domain.YybDriverAccount;
import com.ddhy.domain.YybDriverPostion;
import com.ddhy.domain.YybUserPosition;
import com.ddhy.repository.*;
import com.ddhy.service.BaiduMapService;
import com.mysql.jdbc.Driver;

@RestController
public class LocationController {
	@Autowired
	DriverCarPositionRepository driverCarPositionRepository;
	@Autowired
	CustomerPositionRepository customerPositionRepository;
	@Autowired
	DriverRepository driverRepository;
	@Autowired
	BaiduMapService baiduMapService;
	@RequestMapping("/driver/updatepos")
	public Result updateDriverPosition(YybDriverPostion yybDriverPostion){
		Result result=new Result();
		yybDriverPostion.setYybLastmodifytime(new Timestamp(System.currentTimeMillis()));
		if(yybDriverPostion.getYybDriverid()==0||yybDriverPostion.getYybLat()==null||yybDriverPostion.getYybLong()==null){
			result.setErrMsg("data is incomplete");
		}else{
			YybDriverAccount driver=driverRepository.findOne(yybDriverPostion.getYybDriverid());
			if(driver==null){
				result.setErrMsg("driver id is incomplete");
			}else if(driver.getYybBaiduid()==null){
				String baiduid=baiduMapService.addPOI(yybDriverPostion.getYybLat(), yybDriverPostion.getYybLong());
				driver.setYybBaiduid(baiduid);
				driverRepository.save(driver);
			}else{
				baiduMapService.updatePOI(driver.getYybBaiduid(), yybDriverPostion.getYybLat(),yybDriverPostion.getYybLong());
			}
			driverCarPositionRepository.save(yybDriverPostion);
			
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
