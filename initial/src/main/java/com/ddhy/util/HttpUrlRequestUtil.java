package com.ddhy.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class HttpUrlRequestUtil {
	public static String request(String url){
		try {
			URLEncoder.encode(url, "UTF-8");
			URL urlRequest = new URL(url);
			System.out.println("url="+url);
			HttpURLConnection connection = (HttpURLConnection) urlRequest.openConnection();
			System.out.println("connection:" + connection.toString());
			connection.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			System.out.println("reader:");
			String line;
			StringBuffer res = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				line = line.replace("?", "");
				res.append(line);
			}
			reader.close();
			connection.disconnect();
			return res.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
	public static String postRequest(String url,String post){
		try {
			URL postUrl = new URL(url);  
			// 打开连接  
			HttpURLConnection connection = (HttpURLConnection) postUrl  
			.openConnection();   
			// 设置是否向connection输出，因为这个是post请求，参数要放在  
			// http正文内，因此需要设为true  
			connection.setDoOutput(true);  
			// Read from the connection. Default is true.  
			connection.setDoInput(true);  
			// Set the post method. Default is GET  
			connection.setRequestMethod("POST");    
			// Post 请求不能使用缓存  
			connection.setUseCaches(false);    
			// URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。  
			// connection.setFollowRedirects(true);    
			// URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数  
			connection.setInstanceFollowRedirects(true);  
			// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的  
			// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode  
			// 进行编码  
			connection.setRequestProperty("Content-Type",  
			"application/x-www-form-urlencoded");  
			// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，  
			// 要注意的是connection.getOutputStream会隐含的进行connect。  
			connection.connect();  
			DataOutputStream out = new DataOutputStream(connection  
			.getOutputStream());  
			// The URL-encoded contend  
			// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致  
			// String content = "" + "&activatecode=" + URLEncoder.encode("久酷博客", "utf-8");
			// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
			out.writeBytes(post);   
			out.flush();  
			out.close(); // flush and close  
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//设置编码,否则中文乱码  
			String line;
			StringBuffer res = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				res.append(line);
			}
			reader.close();
			connection.disconnect();
			return res.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

}
