package com.ddhy.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ddhy.util.SignUtils;

//移动支付
@Service
public class AliService {
	public AliService() {
	}
	// 商户PID
	public static final String PARTNER = "2088711804425039";
	// 商户收款账号
	public static final String SELLER = "yybzhifubao@163.com";
	// 商户私钥，pkcs8格式
	// public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALIV07fQwjPYczHCrRjfbbdQgF8sy34OUiG2jS5RrudKOVSkL7fH1TIBOkqCrdx2Rqz8a785F9M0izKbtscqFDjAQeV8D9Ng82OI7BA9K2a5t3+eWNArfV28CVBD5wJSO1IHOaXf6W0p/WB1Uzxj4Q9qASaw/BJwWtxfP45EQ3efAgMBAAECgYAHXo/cU2815gceUAgJt3U62MH3LktddApCCuPcvtSWDaRBtGmlGUfhQdu/qZi4Psy969QpnZs2yj3YYkk2BK/Bd7rhahbCsS1WU+8PG2b4fN8rqO0iAmGGogjCCUJIecKAE4rw8cr0SvTeGNillDY96MIHw77rCekRLcAmQBaZgQJBAOX4xHwv5Zh4qf4id7QOFlb9Wfqz8oXUAjtnjzibvwZBts8DptSx7bE4XP0lc+LyZ7AcODajAgiR+Ty7MjtEpJkCQQDGPbGFhZz8lTPCKTbjKQeHAdZWiunGjhNtDhz7yHTBf9oVMElbpq//1YcVXwR1PoCNWGwLdqFRf1hL7PMLjuj3AkB7TO/8LaoKH9f0/AH2Nf9gUKInusdzXYJ82z98+HRpJF6hi7GtJGDuveuNhsElkxZo5Bh3otp6QdnB6BQ8lscxAkEAkQcHPSBvpdVdhTjzn6IfkKRGcw+zUQAVtB20FHsk936aRBAMStGaRnanGXpm34M3NrCucoB6Kg4YwYr6j8UgJwJATExiSrHKRFmHvSdIFm/5/YZv2yXDibq+cApz79w5AujIrGi1zBSSpabV+pZJP7ipTNR3zS1JnUXJ0grNDyWLDg==";
	//siyao
	public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALCGDx6p0yHBNycAB36Rwl3NkgQv5+GybRUOsKyse3nBYT8WT34iMyZNt6ZCuR6GtqTMJ51D2H7y6i8sSjM4iwRyRQavRJdrgFH/zzNP9VGL2hPuaacC6GQLGVLuNNpmMWXHM/4vK+lKpvdfKZI99kjhom3BdCKnNZc4CT4QTUrJAgMBAAECgYEAlmhTv7vR8yPvu2JSAG1v4H9FWB39hRiDy3pt3WNF/lNgaeG3r/CyBoQX5ClcFH+SZk6xvlVNxbVzBujE/GnEQITSXIHwVgAw2Mma3r7wnwD6fImy7OTS2LOPObI3079hSvJbe4l75h69dvTbl7+dQtIgkauUv5TSoHpIhbgEXEUCQQDpIUeczWQrRmaBwML50hLmZg5jIF/IEgWYb3VNyLXyJZCNnO9vEWVhM077ics8h3artaKGcMMON0Y6WJ+XjtGbAkEAwdcxnPU1YoOWYnuHVmbiRYV9mJoYPri6ZETKkliFaKYr3QhhiVX2rjosv/FzeLJ7IJiqZ7uGd24D1vqWy3l9awJAb6IF/qnDa418jGlXc/UKI9V+0iywcMdgcov8qdCQf3FJdOZPKOo1l4PQMFbdQRDE3JhPSP06CdaiBFJnqVU0PQJBAKd3b1mXT6Po44f6CRbQlDRT/ApXNxMZLY9rD0gYSkn7JnIzx3CLYC9t+zNpOsXQDShAPRiZnraXyaF1pcntEDkCQBxE0tuvXnt8CcU9u2iCBKiDqu2yTp3z/V8ixdSDskyokzC7Jf2GHte4lSEZIuv3QmR8PmSTTRu/7RMtyOjFhYM=";

	
	// 支付宝公钥
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	public String AliOrder(String subject,String body,String price,String orderNum) {
		// 订单
		String orderInfo = getOrderInfo(subject, body, price,orderNum);
		// 对订单做RSA 签名
		String sign = sign(orderInfo);
		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		return payInfo;
	}

	public String getOrderInfo(String subject, String body, String price,String orderNum) {

		// 签约合作者身份ID
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";

		// 签约卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + orderNum + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + price + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + "http://112.126.83.199/didi-1/buss/alipaynotifyurl"
				+ "\"";

		// 服务接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"30m\"";

		// extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
		// orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
		orderInfo += "&return_url=\"m.alipay.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}

	/**
	 * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
	 * 
	 */
	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);

		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	/**
	 * get the sign type we use. 获取签名方式
	 * 
	 */
	public String getSignType() {
		return "sign_type=\"RSA\"";
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}
}
