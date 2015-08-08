package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the yyb_user_account database table.
 * 
 */
@Entity
@Table(name="yyb_user_account")
public class YybUserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_accountbalance")
	private BigDecimal yybAccountbalance=new BigDecimal(0);

	@Column(name="yyb_address")
	private String yybAddress;

    @Temporal( TemporalType.DATE)
	@Column(name="yyb_checkdate")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yybCheckdate;

	@Column(name="yyb_city")
	private String yybCity;

	@Column(name="yyb_county")
	private String yybCounty;

	@Column(name="yyb_idcard")
	private String yybIdcard;

	@Column(name="yyb_lastlogindate")
	private Timestamp yybLastlogindate=new Timestamp(new Date().getTime());

	@Column(name="yyb_loginnum")
	private int yybLoginnum;

	@Column(name="yyb_password")
	private String yybPassword;

	@Column(name="yyb_paypassword")
	private String yybPaypassword;

	@Column(name="yyb_phone")
	private String yybPhone;

	@Column(name="yyb_province")
	private String yybProvince;

    @Temporal( TemporalType.DATE)
	@Column(name="yyb_regdate")
	private Date yybRegdate=new Date();

	@Column(name="yyb_sex")
	private String yybSex="男";

	@Column(name="yyb_token")
	private String yybToken;

	@Column(name="yyb_username")
	private String yybUsername;

	@Column(name="yyb_userphoto")
	private String yybUserphoto;

	@Column(name="yyb_userstatus")
	private String yybUserstatus;

	@Column(name="yyb_usertype")
	private String yybUsertype;
	
	@Column(name="yyb_baiduid")
	private String yybBaiduid;
	

    public String getYybBaiduid() {
		return yybBaiduid;
	}

	public void setYybBaiduid(String yybBaiduid) {
		this.yybBaiduid = yybBaiduid;
	}

	public YybUserAccount() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public BigDecimal getYybAccountbalance() {
		return this.yybAccountbalance;
	}

	public void setYybAccountbalance(BigDecimal yybAccountbalance) {
		this.yybAccountbalance = yybAccountbalance;
	}

	public String getYybAddress() {
		return this.yybAddress;
	}

	public void setYybAddress(String yybAddress) {
		this.yybAddress = yybAddress;
	}

	public Date getYybCheckdate() {
		return this.yybCheckdate;
	}

	public void setYybCheckdate(Date yybCheckdate) {
		this.yybCheckdate = yybCheckdate;
	}

	public String getYybCity() {
		return this.yybCity;
	}

	public void setYybCity(String yybCity) {
		this.yybCity = yybCity;
	}

	public String getYybCounty() {
		return this.yybCounty;
	}

	public void setYybCounty(String yybCounty) {
		this.yybCounty = yybCounty;
	}

	public String getYybIdcard() {
		return this.yybIdcard;
	}

	public void setYybIdcard(String yybIdcard) {
		this.yybIdcard = yybIdcard;
	}

	public Timestamp getYybLastlogindate() {
		return this.yybLastlogindate;
	}

	public void setYybLastlogindate(Timestamp yybLastlogindate) {
		this.yybLastlogindate = yybLastlogindate;
	}

	public int getYybLoginnum() {
		return this.yybLoginnum;
	}

	public void setYybLoginnum(int yybLoginnum) {
		this.yybLoginnum = yybLoginnum;
	}

	public String getYybPassword() {
		return this.yybPassword;
	}

	public void setYybPassword(String yybPassword) {
		this.yybPassword = yybPassword;
	}

	public String getYybPaypassword() {
		return this.yybPaypassword;
	}

	public void setYybPaypassword(String yybPaypassword) {
		this.yybPaypassword = yybPaypassword;
	}

	public String getYybPhone() {
		return this.yybPhone;
	}

	public void setYybPhone(String yybPhone) {
		this.yybPhone = yybPhone;
	}

	public String getYybProvince() {
		return this.yybProvince;
	}

	public void setYybProvince(String yybProvince) {
		this.yybProvince = yybProvince;
	}

	public Date getYybRegdate() {
		return this.yybRegdate;
	}

	public void setYybRegdate(Date yybRegdate) {
		this.yybRegdate = yybRegdate;
	}

	public String getYybSex() {
		return this.yybSex;
	}

	public void setYybSex(String yybSex) {
		this.yybSex = yybSex;
	}

	public String getYybToken() {
		return this.yybToken;
	}

	public void setYybToken(String yybToken) {
		this.yybToken = yybToken;
	}

	public String getYybUsername() {
		return this.yybUsername;
	}

	public void setYybUsername(String yybUsername) {
		this.yybUsername = yybUsername;
	}

	public String getYybUserphoto() {
		return this.yybUserphoto;
	}

	public void setYybUserphoto(String yybUserphoto) {
		this.yybUserphoto = yybUserphoto;
	}

	public String getYybUserstatus() {
		return this.yybUserstatus;
	}

	public void setYybUserstatus(String yybUserstatus) {
		this.yybUserstatus = yybUserstatus;
	}

	public String getYybUsertype() {
		return this.yybUsertype;
	}

	public void setYybUsertype(String yybUsertype) {
		this.yybUsertype = yybUsertype;
	}

}