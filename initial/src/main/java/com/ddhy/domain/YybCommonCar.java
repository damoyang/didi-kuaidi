package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the yyb_common_car database table.
 * 
 */
@Entity
@Table(name="yyb_common_car")
public class YybCommonCar implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_carbrand")
	private String yybCarbrand;

	@Column(name="yyb_carheight")
	private BigDecimal yybCarheight;

	@Column(name="yyb_carlength")
	private BigDecimal yybCarlength;

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

    public YybCommonCar() {
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

}