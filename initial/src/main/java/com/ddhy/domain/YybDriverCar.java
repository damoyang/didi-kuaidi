package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * The persistent class for the yyb_driver_car database table.
 * 
 */
@Entity
@Table(name="yyb_driver_car")
public class YybDriverCar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_carbrand")
	private String yybCarbrand;

    @Temporal( TemporalType.DATE)
	@Column(name="yyb_carbuy")
    @DateTimeFormat(pattern="yyyy-MM-dd-HH:mm:ss")
	private Date yybCarbuy;
    
    
	@Column(name="yyb_carheight")
	private BigDecimal yybCarheight;

	@Column(name="yyb_carlength")
	private BigDecimal yybCarlength;

	@Column(name="yyb_carlicense")
	private String yybCarlicense;

    @Temporal( TemporalType.DATE)
	@Column(name="yyb_carmot")
    @DateTimeFormat(pattern="yyyy-MM-dd-HH:mm:ss")
	private Date yybCarmot;
    

	@Column(name="yyb_caroil")
	private BigDecimal yybCaroil;

	@Column(name="yyb_carratedweight")
	private BigDecimal yybCarratedweight;

    @Lob()
	@Column(name="yyb_carscope")
	private String yybCarscope;

	@Column(name="yyb_cartcategory")
	private String yybCartcategory;

	@Column(name="yyb_cartype")
	private String yybCartype;

	@Column(name="yyb_carurl")
	private String yybCarurl;

	@Column(name="yyb_carvolume")
	private BigDecimal yybCarvolume;

	@Column(name="yyb_carweight")
	private BigDecimal yybCarweight;

	@Column(name="yyb_carwidth")
	private BigDecimal yybCarwidth;

	@Column(name="yyb_driverid")
	private int yybDriverid;

    @Temporal( TemporalType.DATE)
	@Column(name="yyb_vehiclelicensedate")
    @DateTimeFormat(pattern="yyyy-MM-dd-HH:mm:ss")
	private Date yybVehiclelicensedate;
    
    public YybDriverCar() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public String getYybCarbrand() {
		return this.yybCarbrand;
	}

	public void setYybCarbrand(String yybCarbrand) {
		this.yybCarbrand = yybCarbrand;
	}

	public Date getYybCarbuy() {
		return this.yybCarbuy;
	}

	public void setYybCarbuy(Date yybCarbuy) {
		this.yybCarbuy = yybCarbuy;
	}

	public BigDecimal getYybCarheight() {
		return this.yybCarheight;
	}

	public void setYybCarheight(BigDecimal yybCarheight) {
		this.yybCarheight = yybCarheight;
	}

	public BigDecimal getYybCarlength() {
		return this.yybCarlength;
	}

	public void setYybCarlength(BigDecimal yybCarlength) {
		this.yybCarlength = yybCarlength;
	}

	public String getYybCarlicense() {
		return this.yybCarlicense;
	}

	public void setYybCarlicense(String yybCarlicense) {
		this.yybCarlicense = yybCarlicense;
	}

	public Date getYybCarmot() {
		return this.yybCarmot;
	}

	public void setYybCarmot(Date yybCarmot) {
		this.yybCarmot = yybCarmot;
	}

	public BigDecimal getYybCaroil() {
		return this.yybCaroil;
	}

	public void setYybCaroil(BigDecimal yybCaroil) {
		this.yybCaroil = yybCaroil;
	}

	public BigDecimal getYybCarratedweight() {
		return this.yybCarratedweight;
	}

	public void setYybCarratedweight(BigDecimal yybCarratedweight) {
		this.yybCarratedweight = yybCarratedweight;
	}

	public String getYybCarscope() {
		return this.yybCarscope;
	}

	public void setYybCarscope(String yybCarscope) {
		this.yybCarscope = yybCarscope;
	}

	public String getYybCartcategory() {
		return this.yybCartcategory;
	}

	public void setYybCartcategory(String yybCartcategory) {
		this.yybCartcategory = yybCartcategory;
	}

	public String getYybCartype() {
		return this.yybCartype;
	}

	public void setYybCartype(String yybCartype) {
		this.yybCartype = yybCartype;
	}

	public String getYybCarurl() {
		return this.yybCarurl;
	}

	public void setYybCarurl(String yybCarurl) {
		this.yybCarurl = yybCarurl;
	}

	public BigDecimal getYybCarvolume() {
		return this.yybCarvolume;
	}

	public void setYybCarvolume(BigDecimal yybCarvolume) {
		this.yybCarvolume = yybCarvolume;
	}

	public BigDecimal getYybCarweight() {
		return this.yybCarweight;
	}

	public void setYybCarweight(BigDecimal yybCarweight) {
		this.yybCarweight = yybCarweight;
	}

	public BigDecimal getYybCarwidth() {
		return this.yybCarwidth;
	}

	public void setYybCarwidth(BigDecimal yybCarwidth) {
		this.yybCarwidth = yybCarwidth;
	}

	public int getYybDriverid() {
		return this.yybDriverid;
	}

	public void setYybDriverid(int yybDriverid) {
		this.yybDriverid = yybDriverid;
	}

	public Date getYybVehiclelicensedate() {
		return this.yybVehiclelicensedate;
	}

	public void setYybVehiclelicensedate(Date yybVehiclelicensedate) {
		this.yybVehiclelicensedate = yybVehiclelicensedate;
	}




}