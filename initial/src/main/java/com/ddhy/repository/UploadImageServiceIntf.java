package com.ddhy.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UploadImageServiceIntf {
	/**
	 * 
	 * @Description：上传图片到服务器，但并未更新数据库记录
	 * @Create Date： 2015年6月11日
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return	ImageUploadResult 
	 * @Why & What is modified：
	 * @version： 0.1
	 */
	public abstract String uploadImage(HttpServletRequest request, HttpServletResponse response);
	
}
