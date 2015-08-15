package com.ddhy.web;

import java.io.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;

import javax.servlet.http.HttpSession;

import com.ddhy.domain.*;
import com.ddhy.service.*;
import com.ddhy.util.*;
import com.ddhy.repository.*;
import com.wordnik.swagger.annotations.*;
@Api(description = "Operations with Landlords", produces = "application/json", value = "user")
@RestController
public class UserController {
	private static Map<String, String> checkMap=new HashMap<String, String>();
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
	@Autowired
	SMSService smsService;
	
	@RequestMapping("/custom/checknum")
	@ApiOperation(value = "Create new Landlord", notes = "Creates new Landlord")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Fields are with validation errors"),
            @ApiResponse(code = 201, message = "") })
	public YybResult getCheckNum(String phone,HttpSession session){
		YybResult result=new YybResult();
		Random random=new Random();
		int n=4;
		StringBuilder sBuilder=new StringBuilder();
		for(int i=0;i<n;i++){
			sBuilder.append(random.nextInt(10));
		}
		String check=sBuilder.toString();
		String msg="您的验证码为："+check;
		smsService.sendOnce(phone,msg);
		session.setAttribute("check", check);
		return result;
	}

	@RequestMapping("/custom/login")
	public YybResult cusLogin(String phone, String password) {

		YybResult result = new YybResult();
		YybUserAccount customer = userServiceIntf.cusLogin(phone, password);
		if (customer == null){
			result.setErrMsg("用户名密码不正确");
			result.setStatus(1);
			return result;
		}
		customer.setYybToken(UUIDGenerator.getUUID());
		customerRepository.save(customer);
		result.setData(customer);
		return result;
	}
	@RequestMapping("/custom/logout")
	public YybResult cusLogout(Integer yybId) {
		YybResult result = new YybResult();
		YybUserAccount yybUserAccount=customerRepository.findOne(yybId);
		if(yybUserAccount==null){
			//TODO what
		}else{
			yybUserAccount.setYybToken(UUIDGenerator.getUUID());
			customerRepository.save(yybUserAccount);
		}
		return result;
	}
	
	@RequestMapping("/custom/addbank")
	public YybResult addBank(YybUserBank yybUserBank){
		YybResult result=new YybResult();
		customerBankRepository.save(yybUserBank);
		return result;
	}
	@RequestMapping("/custom/getbanks")
	public YybResult getBanks(YybUserBank yybUserBank){
		YybResult result=new YybResult();
		List<YybUserBank> banks=customerBankRepository.findByUserId(yybUserBank.getYybUserid());
		result.setData(banks);
		return result;
	}
	@RequestMapping("/custom/deletebank")
	public YybResult deleteBank(YybUserBank yybUserBank){
		YybResult result=new YybResult();
		customerBankRepository.delete(yybUserBank);
		return result;
	}
	@RequestMapping("/driver/login")
	public YybResult drvLogin(String phone, String password) {
		//
		YybResult result = new YybResult();
		YybDriverAccount driver = userServiceIntf.drvLogin(phone, password);
		if (driver == null){
			result.setErrMsg("用户名密码不正确");
			result.setStatus(1);
			return result;
		}
		driver.setYybToken(UUIDGenerator.getUUID());
		driverRepository.save(driver);
		List<YybDriverCar> cars=driverCarRepository.findByDriverId(driver.getYybId());
		Map<String, Object> reMap=new HashMap<String, Object>();
		reMap.put("cars",cars );
		reMap.put("driver", driver);
		result.setData(reMap);
		return result;
	}
	@RequestMapping("/driver/logout")
	public YybResult drvLogout(Integer yybId) {
		YybResult result = new YybResult();
		YybDriverAccount driver=driverRepository.findOne(yybId);
		if(driver==null){
			//TODO 
		}else{
			driver.setYybToken(UUIDGenerator.getUUID());
			driverRepository.save(driver);
		}
		return result;
	}

	@RequestMapping("/custom/register")
	public YybResult cusRegister(YybUserAccount customer,String check,HttpSession session) {
		YybResult result = new YybResult();
		String sessionCheck=(String)session.getAttribute("check");
		if(!sessionCheck.equals(check)) {
			result.setErrMsg("验证码不正确");
			result.setStatus(1);
			return result;
		}
		YybUserAccount cus = userServiceIntf.cusRegister(customer);
		if (cus == null){
			result.setErrMsg("手机号已注册");
			result.setStatus(1);
		}
		result.setData(cus);
		return result;
	}
	@RequestMapping("/driver/addinfo")
	public YybResult drvInfo(YybDriverAccount driver) {
		YybResult result = new YybResult();
		YybDriverAccount drv = driverRepository.findOne(driver.getYybId());
		if (drv == null){
			result.setStatus(1);
			return result;
		}
		
		driverRepository.save(drv);
		return result;
	}

	@RequestMapping("/driver/register")
	public YybResult drvRegister(YybDriverAccount driver,String check,HttpSession session) {
		YybResult result = new YybResult();
		String sessionCheck=(String)session.getAttribute("check");
		if(!sessionCheck.equals(check)) {
			result.setErrMsg("验证码不正确");
			result.setStatus(1);
			return result;
		}
		YybDriverAccount drv = userServiceIntf.drvRegister(driver);
		if (drv == null){
			result.setErrMsg("手机号已注册");
			result.setStatus(1);
			return result;
		}
		result.setData(drv);
		return result;
	}

	@RequestMapping(value="/custom/uploadpic" ,method=RequestMethod.POST)
	public YybResult uploadCustomerPic(YybUserAccount customer,
			@RequestParam("file") MultipartFile file) {
//		String basedir="resources/cuspic/";
		String basedir="";

//		File file2=new File(basedir);
//		if(!file2.isDirectory()){
//			file2.mkdirs();
//		}
		String name=basedir+customer.getYybId()+"_"+file.getOriginalFilename(); 
		YybResult result=new YybResult();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			YybUserAccount userAccount=customerRepository.findOne(customer.getYybId());
			userAccount.setYybUserphoto(name);
			customerRepository.save(userAccount);
			result.setData(name);
		} else {
			result.setErrMsg("文件内容为空");
			result.setStatus(2);
		}
		return result;
	}

	@RequestMapping(value="/driver/uploadpic",method=RequestMethod.POST)
	public YybResult uploadDriverPic(YybDriverAccount driver,@RequestParam("file") MultipartFile file) {
		String basedir="resources/drvpic/";
		String name=basedir+driver.getYybId()+"_"+file.getOriginalFilename(); 
		YybResult result=new YybResult();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			YybDriverAccount driverAccount=driverRepository.findOne(driver.getYybId());
			driverAccount.setYybDriverphoto(name);
			driverRepository.save(driverAccount);
			result.setData(name);
			
		} else {
			result.setErrMsg("文件内容为空");
			result.setStatus(2);
		}
		return result;
	}

	@RequestMapping(value="/driver/uploadlicence",method=RequestMethod.POST)
	public YybResult uploadDriverLicencePic(YybDriverPaper paper,@RequestParam("file") MultipartFile file) {
		String basedir="resources/lcnpic/";
		String name=basedir+paper.getYybDriverid()+"lic_"+file.getOriginalFilename(); 
		YybResult result=new YybResult();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			paper.setYybPaperurl(name);
			driverPaperRepository.save(paper);
			result.setData(name);
		} else {
			result.setErrMsg("文件内容为空");
			result.setStatus(2);
		}
		return result;
	}

	@RequestMapping("/driver/addcar")
	YybResult driverAddCar(YybDriverCar yybDriverCar){
		YybResult result=new YybResult();
		YybDriverAccount driver=driverRepository.findOne(yybDriverCar.getYybDriverid());
		if(driver==null){
			result.setErrMsg("driverid is not valid");
			result.setStatus(1);
		}else{
			driverCarRepository.save(yybDriverCar);
		}
		return result;
	}
	@RequestMapping(value="/driver/uploadcarpic",method=RequestMethod.POST)
	public YybResult uploadDriverCarPic(YybDriverCar driveCar,@RequestParam("file") MultipartFile file) {
		String basedir="resources/carpic/";
		String name=basedir+driveCar.getYybDriverid()+"_"+driveCar.getYybId()+file.getOriginalFilename(); 
		YybResult result=new YybResult();
		if (!file.isEmpty()) {
			name=genefile(name, file);
			YybDriverCar car=driverCarRepository.findOne(driveCar.getYybId());
			car.setYybCarurl(name);
			driverCarRepository.save(car);
			result.setData(name);
		} else {
			result.setErrMsg("文件内容为空");
			result.setStatus(2);		}
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
			return file2.getName();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	

}
