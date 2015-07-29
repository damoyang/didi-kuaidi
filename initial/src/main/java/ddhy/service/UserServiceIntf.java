package ddhy.service;

import ddhy.model.YybDriverAccount;
import ddhy.model.YybUserAccount;

public interface UserServiceIntf {
	public YybUserAccount cusLogin(String fakeName,String password);
	public YybDriverAccount drvLogin(String fakeName,String password);
	public YybUserAccount cusRegister(YybUserAccount cus);
	public YybDriverAccount drvRegister(YybDriverAccount drv);
	public Boolean uploadPic();
	public Boolean uploadLicencePic();
	public Boolean uploadCarLicencePic();
}
