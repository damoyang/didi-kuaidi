package com.ddhy.util;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088711804425039";
	
	// 收款支付宝账号
	public static String seller_email = "yybzhifubao@163.com";
	// 商户的私钥
	public static String key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALCGDx6p0yHBNycAB36Rwl3NkgQv5+GybRUOsKyse3nBYT8WT34iMyZNt6ZCuR6GtqTMJ51D2H7y6i8sSjM4iwRyRQavRJdrgFH/zzNP9VGL2hPuaacC6GQLGVLuNNpmMWXHM/4vK+lKpvdfKZI99kjhom3BdCKnNZc4CT4QTUrJAgMBAAECgYEAlmhTv7vR8yPvu2JSAG1v4H9FWB39hRiDy3pt3WNF/lNgaeG3r/CyBoQX5ClcFH+SZk6xvlVNxbVzBujE/GnEQITSXIHwVgAw2Mma3r7wnwD6fImy7OTS2LOPObI3079hSvJbe4l75h69dvTbl7+dQtIgkauUv5TSoHpIhbgEXEUCQQDpIUeczWQrRmaBwML50hLmZg5jIF/IEgWYb3VNyLXyJZCNnO9vEWVhM077ics8h3artaKGcMMON0Y6WJ+XjtGbAkEAwdcxnPU1YoOWYnuHVmbiRYV9mJoYPri6ZETKkliFaKYr3QhhiVX2rjosv/FzeLJ7IJiqZ7uGd24D1vqWy3l9awJAb6IF/qnDa418jGlXc/UKI9V+0iywcMdgcov8qdCQf3FJdOZPKOo1l4PQMFbdQRDE3JhPSP06CdaiBFJnqVU0PQJBAKd3b1mXT6Po44f6CRbQlDRT/ApXNxMZLY9rD0gYSkn7JnIzx3CLYC9t+zNpOsXQDShAPRiZnraXyaF1pcntEDkCQBxE0tuvXnt8CcU9u2iCBKiDqu2yTp3z/V8ixdSDskyokzC7Jf2GHte4lSEZIuv3QmR8PmSTTRu/7RMtyOjFhYM=";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "/home/bae/app";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "MD5";

}
