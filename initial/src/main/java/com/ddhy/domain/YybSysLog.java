package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the yyb_sys_log database table.
 * 
 */
@Entity
@Table(name="yyb_sys_log")
public class YybSysLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private String yybId;

	@Column(name="yyb_addtime")
	private Timestamp yybAddtime;

    @Lob()
	@Column(name="yyb_event")
	private String yybEvent;

	@Column(name="yyb_type")
	private String yybType;

	@Column(name="yyb_userid")
	private int yybUserid;

    public YybSysLog() {
    }

	public String getYybId() {
		return this.yybId;
	}

	public void setYybId(String yybId) {
		this.yybId = yybId;
	}

	public Timestamp getYybAddtime() {
		return this.yybAddtime;
	}

	public void setYybAddtime(Timestamp yybAddtime) {
		this.yybAddtime = yybAddtime;
	}

	public String getYybEvent() {
		return this.yybEvent;
	}

	public void setYybEvent(String yybEvent) {
		this.yybEvent = yybEvent;
	}

	public String getYybType() {
		return this.yybType;
	}

	public void setYybType(String yybType) {
		this.yybType = yybType;
	}

	public int getYybUserid() {
		return this.yybUserid;
	}

	public void setYybUserid(int yybUserid) {
		this.yybUserid = yybUserid;
	}

}