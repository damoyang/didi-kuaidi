package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.ddhy.util.UUIDGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;


/**
 * The persistent class for the yyb_buss_order database table.
 * 
 */
@Entity
@Table(name="yyb_buss_order")
public class YybBussOrder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

    @Temporal( TemporalType.DATE)
	@Column(name="yyb_arrivetime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybArrivetime;

	@Column(name="yyb_calcmoney")
	private BigDecimal yybCalcmoney;

	@Column(name="yyb_carcategory")
	private String yybCarcategory;

	@Column(name="yyb_carlicense")
	private String yybCarlicense;

	@Column(name="yyb_county")
	private String yybCounty;

	@Column(name="yyb_driverid")
	private int yybDriverid;

	@Column(name="yyb_drivermoney")
	private BigDecimal yybDrivermoney;

	@Column(name="yyb_drivername")
	private String yybDrivername;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_endtime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybEndtime;

	@Column(name="yyb_goodsaddress")
	private String yybGoodsaddress;

	@Column(name="yyb_goodscity")
	private String yybGoodscity;

	@Column(name="yyb_goodsprovince")
	private String yybGoodsprovince;

	@Column(name="yyb_goodstype")
	private String yybGoodstype;

	@Column(name="yyb_goodsvolume")
	private BigDecimal yybGoodsvolume;

	@Column(name="yyb_goodsweight")
	private BigDecimal yybGoodsweight;

	@Column(name="yyb_insurance")
	private String yybInsurance;

	@Column(name="yyb_insurancemoney")
	private BigDecimal yybInsurancemoney;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_judgetime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybJudgetime;

	@Column(name="yyb_mileage")
	private BigDecimal yybMileage;

	@Column(name="yyb_orderno")
	private String yybOrderno;
  
	@Column(name="yyb_orderstatus")
	private String yybOrderstatus;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_ordertime")
	private Date yybOrdertime;

	@Column(name="yyb_ordertype")
	private String yybOrdertype;

	@Column(name="yyb_othermoney")
	private BigDecimal yybOthermoney;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_paytime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybPaytime;

	@Column(name="yyb_realmoney")
	private BigDecimal yybRealmoney;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_realstarttime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybRealstarttime;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_starttime")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybStarttime;

	@Column(name="yyb_targetaddress")
	private String yybTargetaddress;

	@Column(name="yyb_targetcity")
	private String yybTargetcity;

	@Column(name="yyb_targetcounty")
	private String yybTargetcounty;

	@Column(name="yyb_targetprovince")
	private String yybTargetprovince;

	@Column(name="yyb_tradetype")
	private String yybTradetype;

	@Column(name="yyb_userid")
	private int yybUserid;

	@Column(name="yyb_username")
	private String yybUsername;

    public YybBussOrder() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public Date getYybArrivetime() {
		return this.yybArrivetime;
	}

	public void setYybArrivetime(Date yybArrivetime) {
		this.yybArrivetime = yybArrivetime;
	}

	public BigDecimal getYybCalcmoney() {
		return this.yybCalcmoney;
	}

	public void setYybCalcmoney(BigDecimal yybCalcmoney) {
		this.yybCalcmoney = yybCalcmoney;
	}

	public String getYybCarcategory() {
		return this.yybCarcategory;
	}

	public void setYybCarcategory(String yybCarcategory) {
		this.yybCarcategory = yybCarcategory;
	}

	public String getYybCarlicense() {
		return this.yybCarlicense;
	}

	public void setYybCarlicense(String yybCarlicense) {
		this.yybCarlicense = yybCarlicense;
	}

	public String getYybCounty() {
		return this.yybCounty;
	}

	public void setYybCounty(String yybCounty) {
		this.yybCounty = yybCounty;
	}

	public int getYybDriverid() {
		return this.yybDriverid;
	}

	public void setYybDriverid(int yybDriverid) {
		this.yybDriverid = yybDriverid;
	}

	public BigDecimal getYybDrivermoney() {
		return this.yybDrivermoney;
	}

	public void setYybDrivermoney(BigDecimal yybDrivermoney) {
		this.yybDrivermoney = yybDrivermoney;
	}

	public String getYybDrivername() {
		return this.yybDrivername;
	}

	public void setYybDrivername(String yybDrivername) {
		this.yybDrivername = yybDrivername;
	}

	public Date getYybEndtime() {
		return this.yybEndtime;
	}

	public void setYybEndtime(Date yybEndtime) {
		this.yybEndtime = yybEndtime;
	}

	public String getYybGoodsaddress() {
		return this.yybGoodsaddress;
	}

	public void setYybGoodsaddress(String yybGoodsaddress) {
		this.yybGoodsaddress = yybGoodsaddress;
	}

	public String getYybGoodscity() {
		return this.yybGoodscity;
	}

	public void setYybGoodscity(String yybGoodscity) {
		this.yybGoodscity = yybGoodscity;
	}

	public String getYybGoodsprovince() {
		return this.yybGoodsprovince;
	}

	public void setYybGoodsprovince(String yybGoodsprovince) {
		this.yybGoodsprovince = yybGoodsprovince;
	}

	public String getYybGoodstype() {
		return this.yybGoodstype;
	}

	public void setYybGoodstype(String yybGoodstype) {
		this.yybGoodstype = yybGoodstype;
	}

	public BigDecimal getYybGoodsvolume() {
		return this.yybGoodsvolume;
	}

	public void setYybGoodsvolume(BigDecimal yybGoodsvolume) {
		this.yybGoodsvolume = yybGoodsvolume;
	}

	public BigDecimal getYybGoodsweight() {
		return this.yybGoodsweight;
	}

	public void setYybGoodsweight(BigDecimal yybGoodsweight) {
		this.yybGoodsweight = yybGoodsweight;
	}

	public String getYybInsurance() {
		return this.yybInsurance;
	}

	public void setYybInsurance(String yybInsurance) {
		this.yybInsurance = yybInsurance;
	}

	public BigDecimal getYybInsurancemoney() {
		return this.yybInsurancemoney;
	}

	public void setYybInsurancemoney(BigDecimal yybInsurancemoney) {
		this.yybInsurancemoney = yybInsurancemoney;
	}

	public Date getYybJudgetime() {
		return this.yybJudgetime;
	}

	public void setYybJudgetime(Date yybJudgetime) {
		this.yybJudgetime = yybJudgetime;
	}

	public BigDecimal getYybMileage() {
		return this.yybMileage;
	}

	public void setYybMileage(BigDecimal yybMileage) {
		this.yybMileage = yybMileage;
	}

	public String getYybOrderno() {
		return this.yybOrderno;
	}

	public void setYybOrderno(String yybOrderno) {
		this.yybOrderno = yybOrderno;
	}

	public String getYybOrderstatus() {
		return this.yybOrderstatus;
	}

	public void setYybOrderstatus(String yybOrderstatus) {
		this.yybOrderstatus = yybOrderstatus;
	}

	public Date getYybOrdertime() {
		return this.yybOrdertime;
	}

	public void setYybOrdertime(Date yybOrdertime) {
		this.yybOrdertime = yybOrdertime;
	}

	public String getYybOrdertype() {
		return this.yybOrdertype;
	}

	public void setYybOrdertype(String yybOrdertype) {
		this.yybOrdertype = yybOrdertype;
	}

	public BigDecimal getYybOthermoney() {
		return this.yybOthermoney;
	}

	public void setYybOthermoney(BigDecimal yybOthermoney) {
		this.yybOthermoney = yybOthermoney;
	}

	public Date getYybPaytime() {
		return this.yybPaytime;
	}

	public void setYybPaytime(Date yybPaytime) {
		this.yybPaytime = yybPaytime;
	}

	public BigDecimal getYybRealmoney() {
		return this.yybRealmoney;
	}

	public void setYybRealmoney(BigDecimal yybRealmoney) {
		this.yybRealmoney = yybRealmoney;
	}

	public Date getYybRealstarttime() {
		return this.yybRealstarttime;
	}

	public void setYybRealstarttime(Date yybRealstarttime) {
		this.yybRealstarttime = yybRealstarttime;
	}

	public Date getYybStarttime() {
		return this.yybStarttime;
	}

	public void setYybStarttime(Date yybStarttime) {
		this.yybStarttime = yybStarttime;
	}

	public String getYybTargetaddress() {
		return this.yybTargetaddress;
	}

	public void setYybTargetaddress(String yybTargetaddress) {
		this.yybTargetaddress = yybTargetaddress;
	}

	public String getYybTargetcity() {
		return this.yybTargetcity;
	}

	public void setYybTargetcity(String yybTargetcity) {
		this.yybTargetcity = yybTargetcity;
	}

	public String getYybTargetcounty() {
		return this.yybTargetcounty;
	}

	public void setYybTargetcounty(String yybTargetcounty) {
		this.yybTargetcounty = yybTargetcounty;
	}

	public String getYybTargetprovince() {
		return this.yybTargetprovince;
	}

	public void setYybTargetprovince(String yybTargetprovince) {
		this.yybTargetprovince = yybTargetprovince;
	}

	public String getYybTradetype() {
		return this.yybTradetype;
	}

	public void setYybTradetype(String yybTradetype) {
		this.yybTradetype = yybTradetype;
	}

	public int getYybUserid() {
		return this.yybUserid;
	}

	public void setYybUserid(int yybUserid) {
		this.yybUserid = yybUserid;
	}

	public String getYybUsername() {
		return this.yybUsername;
	}

	public void setYybUsername(String yybUsername) {
		this.yybUsername = yybUsername;
	}
	public void init(){
		yybOrderstatus="未确认";
		yybOrdertime=new Date();
		yybOrderno=UUIDGenerator.getDingDanBH();
		Random random=new Random();
		yybCalcmoney=new BigDecimal(random.nextInt(500)+500);
	}

}