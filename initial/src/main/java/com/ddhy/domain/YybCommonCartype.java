package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the yyb_common_cartype database table.
 * 
 */
@Entity
@Table(name="yyb_common_cartype")
public class YybCommonCartype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_other")
	private String yybOther;

	@Column(name="yyb_roadpay")
	private BigDecimal yybRoadpay;

	@Column(name="yyb_type")
	private String yybType;

	@Column(name="yyb_weight")
	private int yybWeight;

    public YybCommonCartype() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public String getYybOther() {
		return this.yybOther;
	}

	public void setYybOther(String yybOther) {
		this.yybOther = yybOther;
	}

	public BigDecimal getYybRoadpay() {
		return this.yybRoadpay;
	}

	public void setYybRoadpay(BigDecimal yybRoadpay) {
		this.yybRoadpay = yybRoadpay;
	}

	public String getYybType() {
		return this.yybType;
	}

	public void setYybType(String yybType) {
		this.yybType = yybType;
	}

	public int getYybWeight() {
		return this.yybWeight;
	}

	public void setYybWeight(int yybWeight) {
		this.yybWeight = yybWeight;
	}

}