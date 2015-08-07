package com.ddhy.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the yyb_driver_paper database table.
 * 
 */
@Entity
@Table(name="yyb_driver_paper")
public class YybDriverPaper implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_driverid")
	private int yybDriverid;

	@Column(name="yyb_paperstatus")
	private String yybPaperstatus;

	@Column(name="yyb_papertype")
	private String yybPapertype;

	@Column(name="yyb_paperurl")
	private String yybPaperurl;

    public YybDriverPaper() {
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

	public String getYybPaperstatus() {
		return this.yybPaperstatus;
	}

	public void setYybPaperstatus(String yybPaperstatus) {
		this.yybPaperstatus = yybPaperstatus;
	}

	public String getYybPapertype() {
		return this.yybPapertype;
	}

	public void setYybPapertype(String yybPapertype) {
		this.yybPapertype = yybPapertype;
	}

	public String getYybPaperurl() {
		return this.yybPaperurl;
	}

	public void setYybPaperurl(String yybPaperurl) {
		this.yybPaperurl = yybPaperurl;
	}

}