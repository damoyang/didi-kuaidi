package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the yyb_driver_postion database table.
 * 
 */
@Entity
@Table(name="yyb_driver_postion")
public class YybDriverPostion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_driverid")
	private int yybDriverid;

	@Column(name="yyb_lastmodifytime")
	private Timestamp yybLastmodifytime;

	@Column(name="yyb_lat")
	private Double yybLat;

	@Column(name="yyb_long")
	private Double yybLong;

    public YybDriverPostion() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public int getYybDriverid() {
		return this.yybDriverid;
	}

	public void setYybDriverid(int yybDriverid) {
		this.yybDriverid = yybDriverid;
	}

	public Timestamp getYybLastmodifytime() {
		return this.yybLastmodifytime;
	}

	public void setYybLastmodifytime(Timestamp yybLastmodifytime) {
		this.yybLastmodifytime = yybLastmodifytime;
	}

	public Double getYybLat() {
		return yybLat;
	}

	public void setYybLat(Double yybLat) {
		this.yybLat = yybLat;
	}

	public Double getYybLong() {
		return yybLong;
	}

	public void setYybLong(Double yybLong) {
		this.yybLong = yybLong;
	}

}