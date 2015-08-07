package com.ddhy.web;

import java.io.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ddhy.domain.Result;
import com.ddhy.domain.YybDriverAccount;
import com.ddhy.domain.YybDriverCar;
import com.ddhy.domain.YybDriverPaper;
import com.ddhy.domain.YybUserAccount;
import com.ddhy.domain.YybUserBank;
import com.ddhy.service.CustomerBankRepository;
import com.ddhy.service.CustomerRepository;
import com.ddhy.service.DriverCarRepository;
import com.ddhy.service.DriverPaperRepository;
import com.ddhy.service.DriverRepository;
import com.ddhy.service.UserServiceIntf;


@RestController
public class UserController {
	@Autowired
	UserServiceIntf userServiceIntf;
	@Autowired 
	DriverRepository driverRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	DriverCarRepository driverCarRepository;
	@Autowired
	CustomerBankRepository customerBankRepository;
	@Autowired
	DriverPaperRepository driverPaperRepository;

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return "hello";
	}

	@RequestMapping("/custom/login")
	public Result cusLogin(String phone, String password) {
		//
		Result result = new Result();
		YybUserAccount customer = userServiceIntf.cusLogin(phone, password);
		if (customer == null)
			result.setSuccess(false);
		result.setData(customer);
		return result;
	}
	@RequestMapping("/custom/logout")
	public Result cusLogout(String yybId) {
		Result result = new Result();
		return result;
	}
	
	@RequestMapping("/custom/addbank")
	public Result addBank(YybUserBank yybUserBank){
		Result result=new Result();
		customerBankRepository.save(yybUserBank);
		return result;
	}
	@RequestMapping("/custom/getbanks")
	public Result getBanks(YybUserBank yybUserBank){
		Result result=new Result();
		customerBankRepository.findByUserId(yybUserBank.getYybUserid());
		return result;
	}
	@RequestMapping("/custom/deletebank")
	public Result deleteBank(YybUserBank yybUserBank){
		Result result=new Result();
		customerBankRepository.delete(yybUserBank);
		return result;
	}
	@RequestMapping("/driver/login")
	public Result drvLogin(String phone, String password) {
		//
		Result result = new Result();
		YybDriverAccount driver = userServiceIntf.drvLogin(phone, password);
		if (driver == null)
			result.setSuccess(false);
		result.setData(driver);
		return result;
	}
	@RequestMapping("/driver/logout")
	public Result drvLogout(String yybId) {
		Result result = new Result();
		return result;
	}

	@RequestMapping("/custom/register")
	public Result cusRegister(YybUserAccount customer) {
		YybUserAccount cus = userServiceIntf.cusRegister(customer);
		Result result = new Result();
		if (cus == null)
			result.setSuccess(false);
		result.setData(cus);
		return result;
	}

	@RequestMapping("/driver/register")
	public Result drvRegister(YybDriverAccount driver) {
		Result result = new Result();
		YybDriverAccount drv = userServiceIntf.drvRegister(driver);
		if (drv == null)
			result.setSuccess(false);
		result.setData(drv);
		return result;
	}

	@RequestMapping(value="/custom/uploadpic" ,method=RequestMethod.POST)
	public Result uploadCustomerPic(YybUserAccount customer,
			@RequestParam("file") MultipartFile file) {
		String basedir="resources/cuspic/";
		File file2=new File(basedir);
		if(!file2.isDirectory()){
			file2.mkdirs();
		}
		String name=basedir+customer.getYybId()+"_"+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			YybUserAccount userAccount=customerRepository.findOne(customer.getYybId());
			userAccount.setYybUserphoto(name);
			customerRepository.save(userAccount);
			result.setData(name);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping(value="/driver/uploadpic",method=RequestMethod.POST)
	public Result uploadDriverPic(YybDriverAccount driver,@RequestParam("file") MultipartFile file) {
		String basedir="resources/drvpic/";
		String name=basedir+driver.getYybId()+"_"+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			YybDriverAccount driverAccount=driverRepository.findOne(driver.getYybId());
			driverAccount.setYybDriverphoto(name);
			driverRepository.save(driverAccount);
			result.setData(name);
			
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping(value="/driver/uploadlicence",method=RequestMethod.POST)
	public Result uploadDriverLicencePic(YybDriverPaper paper,@RequestParam("file") MultipartFile file) {
		String basedir="resources/lcnpic/";
		String name=basedir+paper.getYybDriverid()+"lic_"+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			paper.setYybPaperurl(name);
			driverPaperRepository.save(paper);
			result.setData(name);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping("/driver/addcar")
	Result driverAddCar(YybDriverCar yybDriverCar){
		Result result=new Result();
		YybDriverAccount driver=driverRepository.findOne(yybDriverCar.getYybDriverid());
		if(driver==null){
			result.setErrMsg("driverid is not valid");
		}else{
			driverCarRepository.save(yybDriverCar);
		}
		return result;
	}
	@RequestMapping(value="/driver/uploadcarpic",method=RequestMethod.POST)
	public Result uploadDriverCarPic(YybDriverCar driveCar,@RequestParam("file") MultipartFile file) {
		String basedir="resources/carpic/";
		String name=basedir+driveCar.getYybDriverid()+"_"+driveCar.getYybId()+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			YybDriverCar car=driverCarRepository.findOne(driveCar.getYybId());
			car.setYybCarurl(name);
			driverCarRepository.save(car);
			result.setData(name);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	
	String genefile(String name,MultipartFile file){
		try {
			byte[] bytes = file.getBytes();
			File file2=new File(name);
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(file2));
			stream.write(bytes);
			stream.close();
			return file2.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	

}
