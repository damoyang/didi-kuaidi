package ddhy.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the yyb_buss_traderecord database table.
 * 
 */
@Entity
@Table(name="yyb_buss_traderecord")
public class YybBussTraderecord implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_ordernum")
	private String yybOrdernum;

	@Column(name="yyb_trademoney")
	private BigDecimal yybTrademoney;

	@Column(name="yyb_tradeno")
	private String yybTradeno;

	@Column(name="yyb_tradestatus")
	private String yybTradestatus;

	@Column(name="yyb_tradetime")
	private Timestamp yybTradetime;

	@Column(name="yyb_tradetype")
	private String yybTradetype;

    public YybBussTraderecord() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public String getYybOrdernum() {
		return this.yybOrdernum;
	}

	public void setYybOrdernum(String yybOrdernum) {
		this.yybOrdernum = yybOrdernum;
	}

	public BigDecimal getYybTrademoney() {
		return this.yybTrademoney;
	}

	public void setYybTrademoney(BigDecimal yybTrademoney) {
		this.yybTrademoney = yybTrademoney;
	}

	public String getYybTradeno() {
		return this.yybTradeno;
	}

	public void setYybTradeno(String yybTradeno) {
		this.yybTradeno = yybTradeno;
	}

	public String getYybTradestatus() {
		return this.yybTradestatus;
	}

	public void setYybTradestatus(String yybTradestatus) {
		this.yybTradestatus = yybTradestatus;
	}

	public Timestamp getYybTradetime() {
		return this.yybTradetime;
	}

	public void setYybTradetime(Timestamp yybTradetime) {
		this.yybTradetime = yybTradetime;
	}

	public String getYybTradetype() {
		return this.yybTradetype;
	}

	public void setYybTradetype(String yybTradetype) {
		this.yybTradetype = yybTradetype;
	}

}