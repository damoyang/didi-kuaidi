package com.ddhy.web;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ddhy.domain.YybResult;
import com.ddhy.domain.YybDriverAccount;
import com.ddhy.domain.YybDriverPostion;
import com.ddhy.domain.YybUserPosition;
import com.ddhy.repository.*;
import com.ddhy.service.BaiduMapService;
import com.mysql.jdbc.Driver;
import com.wordnik.swagger.annotations.Api;
@Api(description = "location info", produces = "application/json", value = "location")
@RequestMapping(method=RequestMethod.GET)
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
	public YybResult updateDriverPosition(YybDriverPostion yybDriverPostion){
		YybResult result=new YybResult();
		yybDriverPostion.setYybLastmodifytime(new Timestamp(System.currentTimeMillis()));
		if(yybDriverPostion.getYybDriverid()==0||yybDriverPostion.getYybLat()==null||yybDriverPostion.getYybLong()==null){
			result.setErrMsg("参数不完整");
			result.setStatus(2);
			return result;
		}else{
			YybDriverPostion tmp=driverCarPositionRepository.findByUserId(yybDriverPostion.getYybDriverid());
			if(tmp!=null){
				yybDriverPostion.setYybId(tmp.getYybId());
			}
			YybDriverAccount driver=driverRepository.findOne(yybDriverPostion.getYybDriverid());
			if(driver==null){
				result.setErrMsg("没有此用户");
				result.setStatus(1);
				return result;
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
	public YybResult updateCustomPosition(YybUserPosition yybUserPosition){
		YybResult result=new YybResult();
		yybUserPosition.setYybLastmodifytime(new Timestamp(System.currentTimeMillis()));
		if(yybUserPosition.getYybUserid()==0||yybUserPosition.getYybLat()==null||yybUserPosition.getYybLong()==null){
			result.setErrMsg("参数不完整");
			result.setStatus(2);
			return result;
		}else{
			customerPositionRepository.save(yybUserPosition);
		}
		return result;
	}
	
	
	
	
}
