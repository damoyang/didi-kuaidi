package com.ddhy.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.ddhy.domain.Result;
import com.ddhy.domain.YybSysBasicinfo;
import com.ddhy.service.SysBasicinfoRepository;

@RestController
public class SystemInfoController {
	String spliter=",";
	String spliterb="|";
	@Autowired
	SysBasicinfoRepository sysBasicinfoRepository;
	@RequestMapping("/sysinfo/add")
	Result addSysInfo(String resname,String resvalue){
		Result result=new Result();
		String[] values=resvalue.split(spliter);
		List<YybSysBasicinfo> resources=sysBasicinfoRepository.findByName(resname);
		YybSysBasicinfo resource;
		if(resources==null||resources.size()==0){
			resource=new YybSysBasicinfo();
			resource.setYybResname(resname);
			resource.setYybResvalue("");
		}else if (resources.size()>1) {
			result.setErrMsg("database error!duplicate name");
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
	Result searchResourceName(String resname){
		Result result=new Result();
		List<YybSysBasicinfo> resources=sysBasicinfoRepository.findByName(resname);
		YybSysBasicinfo resource;
		if(resources==null){
			result.setErrMsg("no item find");
			return result;
		}else if (resources.size()>1) {
			result.setErrMsg("database error!duplicate name");
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
	Result updateResourceName(YybSysBasicinfo yybSysBasicinfo){
		Result result=new Result();
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
