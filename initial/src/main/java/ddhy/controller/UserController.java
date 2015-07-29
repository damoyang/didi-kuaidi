package ddhy.controller;

import java.io.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ddhy.model.Result;
import ddhy.model.YybDriverAccount;
import ddhy.model.YybUserAccount;
import ddhy.service.UserServiceIntf;

@RestController
public class UserController {
	@Autowired
	UserServiceIntf userServiceIntf;

	@RequestMapping("/greeting")
	public String greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		return "hello";
	}

	@RequestMapping("/custom/login")
	public Result cusLogin(String phone, String password) {
		//
		//
		Result result = new Result();
		YybUserAccount customer = userServiceIntf.cusLogin(phone, password);
		if (customer == null)
			result.setSuccess(false);
		result.setData(customer);
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
		String name=basedir+customer.getYybId()+"_"+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			genefile(name, file);
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
			genefile(name, file);
			result.setData(name);
		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping(value="/driver/uploadlicence",method=RequestMethod.POST)
	public Result uploadDriverLicencePic(YybDriverAccount driver,@RequestParam("file") MultipartFile file) {
		String basedir="resources/drvpic/";
		String name=basedir+driver.getYybId()+"lic_"+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			genefile(name, file);
			result.setData(name);

		} else {
			result.setSuccess(false);
		}
		return result;
	}

	@RequestMapping(value="/driver/uploadcarlicence",method=RequestMethod.POST)
	public Result uploadDriverCarLecencePic(YybDriverAccount driver,@RequestParam("file") MultipartFile file) {
		String basedir="resources/drvpic/";
		String name=basedir+driver.getYybId()+"carl_"+file.getOriginalFilename(); 
		Result result=new Result();
		if (!file.isEmpty()) {
			genefile(name, file);
			result.setData(name);

		} else {
			result.setSuccess(false);
		}
		return result;
	}
	void genefile(String name,MultipartFile file){
		try {
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
