package com.ddhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddhy.domain.YybDriverAccount;
import com.ddhy.domain.YybUserAccount;
import com.ddhy.repository.CustomerRepository;
import com.ddhy.repository.DriverRepository;

@Service
public class UserServiceImpl implements UserServiceIntf {
	@Autowired
	CustomerRepository customerDaoIntf;
	@Autowired
	DriverRepository driverDaoIntf;
	

	@Override
	public YybUserAccount cusLogin(String fakeName, String password) {
		YybUserAccount customer = customerDaoIntf.findByName(fakeName);
		if (customer == null)
			return null;
		if (customer.getYybPassword().equals(password)) {
			return customer;
		}
		return null;
	}

	@Override
	public YybDriverAccount drvLogin(String fakeName, String password) {
		YybDriverAccount driver = driverDaoIntf.findByName(fakeName);
		if (driver == null)
			return null;
		if (driver.getYybPassword().equals(password)) {
			return driver;
		}
		return null;
	}

	@Override
	public YybUserAccount cusRegister(YybUserAccount cus) {
		if(cus.getYybPhone()==null||cus.getYybPassword()==null) return null;
		if(customerDaoIntf.findByName(cus.getYybPhone())!=null) return null;
		return customerDaoIntf.save(cus);
	}

	@Override
	public YybDriverAccount drvRegister(YybDriverAccount drv) {
		if(drv.getYybPhone()==null||drv.getYybPassword()==null) return null;
		if(driverDaoIntf.findByName(drv.getYybPhone())!=null) return null;
		return driverDaoIntf.save(drv);
	}

	@Override
	public Boolean uploadPic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean uploadLicencePic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean uploadCarLicencePic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkCusToken(String token, int userid) {
		if(true)
			return true; //TODO  待验证
		// 根据token 和 userid 验证授权是否有效  modify by yuyajie 2015-8-20
		List<YybUserAccount> userAccount = customerDaoIntf.checkToken(token);
		if(userAccount == null || userAccount.size() > 1)
		{
			return false;
		}
		return true;
	}

}
