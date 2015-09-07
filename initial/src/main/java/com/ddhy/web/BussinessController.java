package com.ddhy.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ddhy.domain.YybResult;
import com.ddhy.domain.YybBussOrder;
import com.ddhy.domain.YybBussTraderecord;
import com.ddhy.domain.YybCommonCartype;
import com.ddhy.domain.YybSysBasicinfo;
import com.ddhy.repository.*;
import com.ddhy.service.ActiveSenderService;
import com.ddhy.service.AliService;
import com.ddhy.service.BaiduMapService;
import com.ddhy.service.GrabService;
import com.ddhy.service.UserServiceIntf;
import com.ddhy.util.AlipayNotify;
import com.wordnik.swagger.annotations.Api;

@Api(description = "orderlist and pay info", produces = "application/json", value = "business")
@RequestMapping(method=RequestMethod.GET)
@RestController
public class BussinessController {
	private static Double oilPrice_s;// 油价
	private static HashMap<Integer, Double> monthNoBusy;
	@Autowired
	AliService aliService;
	@Autowired
	ActiveSenderService jmsService;
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	TradeRepository tradeRepository;
	@Autowired
	GrabService grabService;
	@Autowired
	UserServiceIntf userService;
	@Autowired
	BaiduMapService baiduMapService;
	@Autowired
	SysBasicinfoRepository sysBasicinfoRepository;
	@Autowired
	CommonCartypeRepository commonCartypeRepository;
	
	public Double getOilPrice() {
		List<YybSysBasicinfo> rBasicinfos = sysBasicinfoRepository
				.findByName("油价");
		if (rBasicinfos == null || rBasicinfos.size() == 0)
			return null;
		else {
			YybSysBasicinfo oneBasicinfo = rBasicinfos.get(0);
			return Double.parseDouble(oneBasicinfo.getYybResvalue());
		}
	}

	/**
	 * TODO 根据货物重量，获取货车油耗
	 * */
	private double getCaroil(double goodsweight) {
		double caroil = 20.0;
		Long carweight = Math.round(goodsweight);
		List<YybCommonCartype> carByWeight = commonCartypeRepository
				.findCarByWeight(carweight.intValue());
		if (carByWeight != null && carByWeight.size() > 0) {
			caroil = carByWeight.get(0).getYybCaroil().doubleValue();
		}
		return caroil;
	}

	/**
	 * TODO 根据车辆类型，获取高速收费标准
	 * */
	private double getRoadscale(double goodsweight) {
		double roadpay = 1.0;
		Long carweight = Math.round(goodsweight);
		List<YybCommonCartype> carByWeight = commonCartypeRepository
				.findCarByWeight(carweight.intValue());
		if (carByWeight != null && carByWeight.size() > 0) {
			roadpay = carByWeight.get(0).getYybRoadpay().doubleValue();
		}
		return roadpay;
	}

	public HashMap<Integer, Double> getNoBusyMap() {
		HashMap<Integer, Double> reMap = new HashMap<>();
		List<YybSysBasicinfo> rBasicinfos = sysBasicinfoRepository
				.findByName("淡季");
		List<YybSysBasicinfo> rrBasicinfos = sysBasicinfoRepository
				.findByName("公司抽成");
		if (rBasicinfos == null || rBasicinfos.size() == 0
				|| rrBasicinfos == null || rrBasicinfos.size() == 0)
			return null;
		else {
			YybSysBasicinfo oneBasicinfo = rBasicinfos.get(0);
			YybSysBasicinfo twoBasicinfo = rrBasicinfos.get(0);
			String[] args1 = oneBasicinfo.getYybResvalue().split("\\|");
			String[] args2 = twoBasicinfo.getYybResvalue().split("\\|");
			Double rDouble = 1.0;
			for (String rate : args2) {
				String month = rate.substring(0, rate.indexOf(','));
				if (month != null && month.equals("淡季")) {
					rDouble = Double.parseDouble(rate.substring(rate
							.indexOf(',') + 1));
				}
			}
			for (String month : args1) {
				reMap.put(Integer.parseInt(month), rDouble);
			}
			return reMap;
		}
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType, String sortColumn) {
		Sort sort = null;
		if ("desc".equals(sortType) && sortColumn != null) {
			sort = new Sort(Direction.DESC, sortColumn);
		} else if ("asc".equals(sortType) && sortColumn != null) {
			sort = new Sort(Direction.ASC, sortColumn);
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建分页请求.
	 */
	// TODO 构造分页的配置 包括排序的方式
	private PageRequest buildPageRequest(int pageNumber, int pagzSize,
			String sortType) {
		Sort sort = null;
		/*
		 * if ("auto".equals(sortType)) { sort = new Sort(Direction.DESC, "id");
		 * } else if ("title".equals(sortType)) { sort = new Sort(Direction.ASC,
		 * "title"); }
		 */

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 标志下单状态为已下单(即为－－已支付)
	 * 
	 */
	@RequestMapping("/busi/orderconfirm")
	YybResult orderConfirm(String yybOrderno) {
		YybResult result = new YybResult();
		YybBussOrder trueOrder = orderRepository.findByOrderNo(yybOrderno);
		if (trueOrder == null) {
			result.setErrMsg("订单号不正确");
			result.setStatus(1);
			return result;
		} else if (!trueOrder.getYybOrderstatus().equals("未支付")) {
			result.setErrMsg("订单状态异常");
			result.setStatus(1);
			return result;
		}
		trueOrder.setYybOrderstatus("已下单");
		trueOrder.setYybOrdertime(new Timestamp(System.currentTimeMillis()));
		orderRepository.save(trueOrder);
		// TODO baiduMap
		/*
		 * if(jmsService!=null) { jmsService.pushToDriver("22", trueOrder); }
		 */
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("orderno", trueOrder.getYybOrderno());
		reMap.put("orderstatus", trueOrder.getYybOrderstatus());
		reMap.put("ordertime", trueOrder.getYybOrdertime());
		result.setData(reMap);
		return result;
	}

	/**
	 * 
	 */

	/**
	 * 用户发出送货交易请求， 在数据库中要存储交易请求 并且向该城市队列发送消息 下单
	 */
	@RequestMapping("/busi/orderrequest")
	YybResult orderRequest(YybBussOrder order) {
		String type = "";
		YybResult result = new YybResult();
		// 生成订单
		// TODO 判断 公里数 不能为空，车型 载重 体积 不能为空 ，
		if (order.getYybCarcategory() == null
				|| order.getYybGoodsweight() == null
				|| order.getYybGoodstype() == null
				|| order.getYybGoodsaddress() == null
				|| order.getYybGoodscity() == null
				|| order.getYybTargetaddress() == null
				|| order.getYybTargetcity() == null) {
			result.setStatus(1);
			result.setSuccess(false);
			result.setErrMsg("参数错误，请检查［起始地点｜车辆类型｜货物类型｜货物重量｜货物体积］");
			return result;
		}
		//未支付
		order.init();
		// 计算地理位置信息 百度地图支持 城市 详细地址 二维检索
		String origin = baiduMapService.calculateLatLng(order.getYybGoodsaddress(), order.getYybGoodscity());
		String destination = baiduMapService.calculateLatLng(order.getYybTargetaddress(), order.getYybTargetcity());
		if(origin==null||destination==null){
			result.setErrMsg("地理位置无法检索");
			result.setStatus(1);
			return result;
		}
		//公里数
		double dis=baiduMapService.calculateDistance(origin, destination);
		if(dis<0){
			result.setErrMsg("地理位置无法检索");
			result.setStatus(1);
			return result;
		}
		order.setYybMileage(new BigDecimal(dis));
		order.setYybOrigin(origin);
		order.setYybDestination(destination);
		// 此处需要根据订单中车辆的类型计算 油费和总费用 计算方式
		// 油费＝公里数 ＊ 油价 ＊ 油耗
		// 公里数＝装货位置和目标位置定总距离
		// 司机费用＝ 油费
		// 公司抽成＝ 油费 ＊ 0.5 淡季 公司抽成＝ 油费 ＊ 0.25 (暂时这么定，后续可以调整)
		// 高速费用＝ 公里数＊车类型高速收费标准 ［注意这里定高速费用暂时不计入总费用中］
		// 总费用＝ 油费 ＋ 司机费用 ＋ 公司抽成
		// 公里数＝ order.getYybMileage();
		// 油价 取资源中数据， 油耗根据根据车辆类型获取油耗 ，公司抽成：根据当前月份判断淡季和旺季确定抽成
		Date startTime = order.getYybStarttime();// 根据装货时间获取月份
		
		int month = 1;
		if (startTime != null) {
			month = startTime.getMonth();
		}
		double rate = 0.5; // 公司抽成比例 默认 0.5
		double oilPricePre = 7.0; // 油价 默认为 7 从数据库中获取最新油价
		if (oilPrice_s == null) {
			oilPrice_s = getOilPrice();
		}
		oilPricePre = oilPrice_s;
		double mileage = 0; // 公里数
		double feescale = 0.0; // 高速的收费标准 根据选择的车型和载重获取收费标准
		if (order.getYybGoodstype() != null
				&& order.getYybGoodstype().equals("非绿色")) {
			feescale = getRoadscale(order.getYybGoodsweight().doubleValue());
		}
		// TODO 从汽车类型表中根据吨位获取汽车油耗
		double carOil = 20.0; // 汽车的油耗（满载） 根据order中选择的车型获取油耗
		carOil = getCaroil(order.getYybGoodsweight().doubleValue()); // 获取配置的油耗
		double oilPrice = 0.0; // 计算的油费
		double drierPrice = 0.0;// 司机工资
		double roadPrice = 0.0;// 高速费用

		if (order.getYybMileage() != null) {
			mileage = order.getYybMileage().doubleValue(); // 获取计算的公里数
		} else {
			// TODO

		}
		oilPrice = oilPricePre * mileage * (carOil/100);
		drierPrice = oilPrice; // 司机费用 ＝ 油费
		roadPrice = feescale * mileage;// 高速费
		if (monthNoBusy == null) {
			monthNoBusy = getNoBusyMap();
		}
		if (monthNoBusy != null && monthNoBusy.containsKey(month)) // 淡季
		{
			rate = monthNoBusy.get(month);
		}
		double gainPrice = 0.0; // 公司盈利
		gainPrice = oilPricePre * rate;
		double totalPrice = 0.0; // 总共支付费用
		totalPrice = oilPrice + drierPrice + gainPrice;
		order.setYybCalcmoney(new BigDecimal(totalPrice));
		order.setYybDrivermoney(new BigDecimal(drierPrice));
		order.setYybOthermoney(new BigDecimal(roadPrice));
		order = orderRepository.save(order);
		// 生成账单
		YybBussTraderecord yybBussTraderecord = new YybBussTraderecord(
				order.getYybOrderno(), type);
		yybBussTraderecord.init();
		yybBussTraderecord = tradeRepository.save(yybBussTraderecord);
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("order", order);
		reMap.put("trade", yybBussTraderecord);
		result.setData(reMap);
		return result;
	}

	/**
	 * 填写订单并保存 modify by yuyajie 2015-8-20 返回错误 代码 106 表示是传入参数为空 107 表示
	 * 传入的用户id为空 用户发出送货交易请求， 在数据库中要存储交易请求 并且向该城市队列发送消息 下单
	 */
	@RequestMapping("/busi/saveorder")
	YybResult orderSave(String token, YybBussOrder order) {
		YybResult result = new YybResult();
		// 验证一下token是否生效
		if (order == null) {
			result.setSuccess(false);
			result.setStatus(106);
			result.setErrMsg("订单参数为空！");
			return result;
		}
		if (order.getYybUserid() <= 0) {
			result.setSuccess(false);
			result.setErrMsg("userid参数不正确");
			result.setStatus(107);
			return result;
		}
		userService.checkCusToken(token, order.getYybUserid());
		String type = "";
		// 生成订单
		order.init();
		order = orderRepository.save(order);
		// 生成账单
		YybBussTraderecord yybBussTraderecord = new YybBussTraderecord(
				order.getYybOrderno(), type);
		yybBussTraderecord.init();
		yybBussTraderecord = tradeRepository.save(yybBussTraderecord);
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("order", order);
		reMap.put("trade", yybBussTraderecord);
		result.setData(reMap);
		return result;
	}

	/**
	 * 推送 current all
	 * 
	 * 
	 */
	@RequestMapping("/busi/orderlist")
	YybResult orderlist() {
		YybResult result = new YybResult();
		// TODO more choise
		List<YybBussOrder> lists = orderRepository.findByOrderStatus("已支付");
		// TODO more
		result.setData(lists);
		return result;
	}

	/**
	 * 推送 分页加载 modify by yuyajie 2015-8-20 current by page
	 * 
	 * 
	 */
	@RequestMapping("/busi/orderlistbypage")
	YybResult orderlistbypage(Integer page, Integer rows) {

		YybResult result = new YybResult();
		// TODO more choise
		Pageable pageable = buildPageRequest(page, rows, "desc", "yybOrdertime");
		List<YybBussOrder> lists = orderRepository.findByOrderStatusByPage(
				"已支付", pageable);
		int count = orderRepository.countSendOrder("已支付");
		// TODO more
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("orderlist", lists);
		reMap.put("ordercount", count);
		result.setData(reMap);
		return result;
	}

	/**
	 * 历史订单 current all
	 * 
	 * 
	 */
	@RequestMapping("/busi/historyorderlist")
	YybResult orderlistHis(Integer yybId) {
		YybResult result = new YybResult();
		// TODO more choise
		List<YybBussOrder> lists = orderRepository.findByDriverId(yybId);
		// TODO more
		result.setData(lists);
		return result;
	}

	/**
	 * 历史订单分页显示 current by page in rows driverid 司机id page 当前页数 rows 每页行数
	 * 
	 */
	@RequestMapping("/busi/historyorderlistbypage")
	YybResult orderlistHisByPage(Integer driverid, Integer page, Integer rows) {
		YybResult result = new YybResult();
		// TODO more choise
		Pageable pageable = buildPageRequest(page, rows, "desc", "yybOrdertime");
		List<YybBussOrder> lists = orderRepository.findByDriverIdPages(
				driverid, pageable);
		int count = orderRepository.countByDriverIdPages(driverid);
		// TODO more
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("orderlist", lists);
		reMap.put("ordercount", count);
		result.setData(reMap);
		return result;
	}

	/**
	 * 我的未支付订单分页显示 modify by yuyajie current by page
	 * 
	 * 
	 */
	@RequestMapping("/busi/customnopayorderlistbypage")
	YybResult orderlistCusNoPayByPage(Integer userid, Integer page, Integer rows) {

		YybResult result = new YybResult();
		// TODO more choise
		Pageable pageable = buildPageRequest(page, rows, "desc", "yybOrdertime");
		List<YybBussOrder> lists = orderRepository.findNoPayByUserIdPages(
				userid, pageable);
		int count = orderRepository.countByUserIdPages(userid);
		// TODO more
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("orderlist", lists);
		reMap.put("ordercount", count);
		result.setData(reMap);
		return result;
	}

	/**
	 * 我的订单分页显示 modify by yuyajie current by page
	 * 
	 * 
	 */
	@RequestMapping("/busi/customorderlistbypage")
	YybResult orderlistCusByPage(Integer userid, Integer page, Integer rows) {

		YybResult result = new YybResult();
		// TODO more choise
		Pageable pageable = buildPageRequest(page, rows, "desc", "yybOrdertime");
		List<YybBussOrder> lists = orderRepository.findByUserIdPages(userid,
				pageable);
		int count = orderRepository.countByUserIdPages(userid);
		// TODO more
		Map<String, Object> reMap = new HashMap<>();
		reMap.put("orderlist", lists);
		reMap.put("ordercount", count);
		result.setData(reMap);
		return result;
	}

	/**
	 * 我的订单 current all
	 * 
	 * 
	 */
	@RequestMapping("/busi/customorderlist")
	YybResult orderlistCus(Integer yybId) {
		YybResult result = new YybResult();
		// TODO more choise
		List<YybBussOrder> lists = orderRepository.findByUserId(yybId);
		// TODO more
		result.setData(lists);
		return result;
	}

	/**
	 * 推送抢单
	 * 
	 * 
	 */
	@RequestMapping("/busi/orderinfo")
	YybResult orderByGrab(YybBussOrder yybBussOrder) {
		YybResult result = new YybResult();
		YybBussOrder trueOrder = orderRepository.findByOrderNo(yybBussOrder
				.getYybOrderno());
		if (yybBussOrder.getYybOrderno() == null || trueOrder == null) {
			result.setErrMsg("订单号不对");
			result.setStatus(2);
			return result;
		} else if (trueOrder.getYybOrderstatus().equals("已抢单")) {
			result.setData(trueOrder);
			return result;
		} else {
			result.setErrMsg("未被抢单");
			result.setStatus(1);
			return result;
		}
	}

	/**
	 * 抢单
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("/busi/orderresponse")
	YybResult orderResponse(YybBussOrder order) {
		YybResult result = new YybResult();
		if (order.getYybOrderno() == null || order.getYybDriverid() == 0) {
			result.setErrMsg("数据不完整");
			result.setStatus(2);
			return result;
		}
		if (grabService.grabOrder(order.getYybOrderno())) {
			YybBussOrder trueOrder = orderRepository.findByOrderNo(order
					.getYybOrderno());
			if (trueOrder.getYybDriverid() != 0) {// some one has grab it
				result.setErrMsg("订单已经被抢");
				result.setStatus(1);
				return result;
			}
			trueOrder.setYybDriverid(order.getYybDriverid());
			trueOrder.setYybCarlicense(order.getYybCarlicense());
			trueOrder.setYybDrivername(order.getYybDrivername());
			trueOrder.setYybOrderstatus("已抢单");
			orderRepository.saveAndFlush(trueOrder);
			grabService.clearOrder(order.getYybOrderno());
			result.setData(trueOrder);
			// TODO
			return result;
		} else {
			result.setErrMsg("订单已经被抢");
			result.setStatus(1);
			return result;
		}
	}

	/**
	 * 支付成功
	 * 
	 */
	@RequestMapping("/busi/paysuc")
	YybResult paySuccess(String orderNo) {
		YybResult result = new YybResult();
		YybBussOrder yybOrder = orderRepository.findByOrderNo(orderNo);
		// update state
		yybOrder.setYybOrderstatus("已支付");

		yybOrder.setYybPaytime(new Timestamp(System.currentTimeMillis()));
		orderRepository.save(yybOrder);
		// send to each car
		return result;
	}

	/**
	 * TODO 支付宝支付成功回调函数
	 */
	@RequestMapping("/buss/alireturn")
	public YybResult alipayReturnUrl(HttpServletRequest request)
			throws UnsupportedEncodingException {
		YybResult result = new YybResult();
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 商户订单号
			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");
			// 金额
			String total_fee = new String(request.getParameter("total_fee")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 验证结果
			boolean verify_result = AlipayNotify.verify(params);
			verify_result = true;// 测试将其赋为true
			if (verify_result) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED")
						|| trade_status.equals("TRADE_SUCCESS")) {
					Map<String, Object> reMap = new HashMap<>();
					YybBussOrder order =orderRepository.findByOrderNo(out_trade_no);
					order.setYybOrderstatus("已支付");
					reMap.put("orderno", out_trade_no);
					reMap.put("ordermoney", total_fee);
					result.setData(reMap);
					result.setSuccess(true);
					result.setMsg("支付成功！");
					// result = orderConfirm(out_trade_no);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.setStatus(1);
			result.setSuccess(false);
			result.setErrMsg("支付失败！");
		}
		return result;
	}
	
	/**
	 * 
	 * 支付宝支付完成 支付宝服务器做通知接口
	 */
	@RequestMapping(value="/buss/alipaynotifyurl",method=RequestMethod.POST)
	public YybResult alipayNotifyUrl(HttpServletRequest request)
			throws UnsupportedEncodingException {
		YybResult result = new YybResult();
		try {
			Map<String, String> params = new HashMap<String, String>();
			Map<String, String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter
					.hasNext();) {
				String name = iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			// 商户订单号
			String out_trade_no = new String(request.getParameter(
					"out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 交易状态
			String trade_status = new String(request.getParameter(
					"trade_status").getBytes("ISO-8859-1"), "UTF-8");
			// 金额
			String total_fee = new String(request.getParameter("total_fee")
					.getBytes("ISO-8859-1"), "UTF-8");
			// 验证结果
			boolean verify_result = AlipayNotify.verify(params);
			verify_result = true;// 测试将其赋为true
			if (verify_result) {// 验证成功
				if (trade_status.equals("TRADE_FINISHED")
						|| trade_status.equals("TRADE_SUCCESS")) {
					Map<String, Object> reMap = new HashMap<>();
					YybBussOrder order=orderRepository.findByOrderNo(out_trade_no);
					order.setYybOrderstatus("已支付");
					order.setYybPaytime(new Date());
					orderRepository.save(order);
					reMap.put("orderno", out_trade_no);
					reMap.put("ordermoney", total_fee);
					result.setData(reMap);
					result.setSuccess(true);
					result.setMsg("支付成功！");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.setStatus(1);
			result.setSuccess(false);
			result.setErrMsg("支付失败！");
		}
		return result;
	}
	@RequestMapping("/buss/getaliorder")
	public YybResult getAliOrder(YybBussOrder order){
		YybResult result=new YybResult();
		YybBussOrder trueOrder=orderRepository.findByOrderNo(order.getYybOrderno());
		if(trueOrder==null){
			result.setErrMsg("订单号有误");
			result.setStatus(1);
			return result;
		}
		String aliOrder=aliService.AliOrder("测试", "测试", "0.01", trueOrder.getYybOrderno());
		result.setData(aliOrder);
		return result;
	}
	@RequestMapping("/buss/cancelorder")
	public YybResult cancelOrder(YybBussOrder order){
		YybResult result=new YybResult();
		YybBussOrder trueOrder=orderRepository.findByOrderNo(order.getYybOrderno());
		if(trueOrder==null){
			result.setErrMsg("订单号有误");
			result.setStatus(1);
			return result;
		}
		trueOrder.setYybOrderstatus("已取消");
		orderRepository.save(trueOrder);
		return result;
	}
}
