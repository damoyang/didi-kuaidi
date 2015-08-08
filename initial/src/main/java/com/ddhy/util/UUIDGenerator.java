package com.ddhy.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/** 
 * <b>类描述：UUID生成器</b><br/>  

 *
 */
public class UUIDGenerator {
	private static Random random = new Random();
	public static String getUUID() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    }  
	/*
	 *获取订单编号函数 modify 2015-3-11   待修改生成订单的规则
	 * */
	public static String getDingDanBH() {  
        UUID uuid = UUID.randomUUID();  
        String str = uuid.toString();  
        // 去掉"-"符号  
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);  
        return temp;  
    }  
	/*
	 *获取支付编号函数 modify 2015-3-11   待修改生成交易记录表的交易编号的规则
	 * */	
	public static String getNextJiaoYiOrderId(Integer uid){
		Date dt=new Date();
		String dateString = DateUtils.Date2yyyyMMddHHmm(dt);
		Integer rand = random.nextInt(500);
		return String.format("JY_%d_%s_%d", uid, dateString, rand);
	}
	public static void main(String[] args) {
		String str = getDingDanBH();
		System.out.println(str);
	}
}
