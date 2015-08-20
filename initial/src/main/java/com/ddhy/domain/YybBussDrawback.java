package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the yyb_buss_drawback database table.
 * 
 */
@Entity
@Table(name="yyb_buss_drawback")
public class YybBussDrawback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_applytime")
	private Timestamp yybApplytime;

    @Lob()
	@Column(name="yyb_desc")
	private String yybDesc;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_endtime")
	private Date yybEndtime;

	@Column(name="yyb_orderno")
	private String yybOrderno;

	@Column(name="yyb_phone")
	private String yybPhone;

    @Lob()
	@Column(name="yyb_reason")
	private String yybReason;

	@Column(name="yyb_status")
	private String yybStatus;

	@Column(name="yyb_tradeno")
	private String yybTradeno;

	@Column(name="yyb_username")
	private String yybUsername;

    public YybBussDrawback() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public Timestamp getYybApplytime() {
		return this.yybApplytime;
	}

	public void setYybApplytime(Timestamp yybApplytime) {
		this.yybApplytime = yybApplytime;
	}

	public String getYybDesc() {
		return this.yybDesc;
	}

	public void setYybDesc(String yybDesc) {
		this.yybDesc = yybDesc;
	}

	public Date getYybEndtime() {
		return this.yybEndtime;
	}

	public void setYybEndtime(Date yybEndtime) {
		this.yybEndtime = yybEndtime;
	}

	public String getYybOrderno() {
		return this.yybOrderno;
	}

	public void setYybOrderno(String yybOrderno) {
		this.yybOrderno = yybOrderno;
	}

	public String getYybPhone() {
		return this.yybPhone;
	}

	public void setYybPhone(String yybPhone) {
		this.yybPhone = yybPhone;
	}

	public String getYybReason() {
		return this.yybReason;
	}

	public void setYybReason(String yybReason) {
		this.yybReason = yybReason;
	}

	public String getYybStatus() {
		return this.yybStatus;
	}

	public void setYybStatus(String yybStatus) {
		this.yybStatus = yybStatus;
	}

	public String getYybTradeno() {
		return this.yybTradeno;
	}

	public void setYybTradeno(String yybTradeno) {
		this.yybTradeno = yybTradeno;
	}

	public String getYybUsername() {
		return this.yybUsername;
	}

	public void setYybUsername(String yybUsername) {
		this.yybUsername = yybUsername;
	}

}