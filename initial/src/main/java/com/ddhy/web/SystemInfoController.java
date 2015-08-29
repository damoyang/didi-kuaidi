package com.ddhy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.ddhy.domain.YybResult;
import com.ddhy.domain.YybSysBasicinfo;
import com.ddhy.repository.*;
import com.wordnik.swagger.annotations.Api;
@Api(description = "system info", produces = "application/json", value = "sysinfo")
@RequestMapping(method=RequestMethod.GET)
@RestController
public class SystemInfoController {
	String spliter=",";
	String spliterb="|";
	@Autowired
	SysBasicinfoRepository sysBasicinfoRepository;
	@RequestMapping("/sysinfo/add")
	YybResult addSysInfo(String resname,String resvalue){
		YybResult result=new YybResult();
		String[] values=resvalue.split(spliter);
		List<YybSysBasicinfo> resources=sysBasicinfoRepository.findByName(resname);
		YybSysBasicinfo resource;
		if(resources==null||resources.size()==0){
			resource=new YybSysBasicinfo();
			resource.setYybResname(resname);
			resource.setYybResvalue("");
		}else if (resources.size()>1) {
			result.setErrMsg("数据库错误|多条重复记录");
			result.setStatus(3);
			return result;
		}else{
			resource=resources.get(0);
		}
		String[] valuesb=resource.getYybResvalue().split("\\"+spliterb);
		List<String> newvalues=new ArrayList<>();
		List<String> values1=Arrays.asList(values);
		List<String> values2=Arrays.asList(valuesb);
		newvalues.addAll(values1);
		newvalues.removeAll(values2);
		newvalues.addAll(values2);
		StringBuilder tmp=new StringBuilder();
		for(String value:newvalues){
			tmp.append(value).append(spliterb);
		}
		resource.setYybResvalue(tmp.substring(0, tmp.length()-1));
		sysBasicinfoRepository.save(resource);
		return result;
	}
	@RequestMapping("/sysinfo/search")
	YybResult searchResourceName(String resname){
		YybResult result=new YybResult();
		List<YybSysBasicinfo> resources=sysBasicinfoRepository.findByName(resname);
		YybSysBasicinfo resource;
		if(resources==null){
			result.setErrMsg("没有数据");
			result.setStatus(1);
			return result;
		}else if (resources.size()>1) {
			result.setErrMsg("数据库错误|多条重复记录");
			result.setStatus(3);
			return result;
		}else{
			resource=resources.get(0);
		}
		String[] values=resource.getYybResvalue().split("\\"+spliterb);
		StringBuilder tmp=new StringBuilder();
		for(String value:values){
			tmp.append(value).append(spliter);
		}
		resource.setYybResvalue(tmp.substring(0, tmp.length()-1));
		result.setData(resource);
		return result;
	}
	@RequestMapping("/sysinfo/update")
	YybResult updateResourceName(YybSysBasicinfo yybSysBasicinfo){
		YybResult result=new YybResult();
		String[] values=yybSysBasicinfo.getYybResvalue().split(spliter);
		StringBuilder tmp=new StringBuilder();
		for(String value:values){
			tmp.append(value).append(spliterb);
		}
		yybSysBasicinfo.setYybResvalue(tmp.substring(0, tmp.length()-1));
		sysBasicinfoRepository.save(yybSysBasicinfo);
		return result;
	}

}
