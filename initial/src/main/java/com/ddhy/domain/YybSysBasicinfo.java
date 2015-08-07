package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the yyb_sys_basicinfo database table.
 * 
 */
@Entity
@Table(name="yyb_sys_basicinfo")
public class YybSysBasicinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_resname")
	private String yybResname;

    @Lob()
	@Column(name="yyb_resvalue")
	private String yybResvalue;

    public YybSysBasicinfo() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public String getYybResname() {
		return this.yybResname;
	}

	public void setYybResname(String yybResname) {
		this.yybResname = yybResname;
	}

	public String getYybResvalue() {
		return this.yybResvalue;
	}

	public void setYybResvalue(String yybResvalue) {
		this.yybResvalue = yybResvalue;
	}

}