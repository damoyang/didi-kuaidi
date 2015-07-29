package ddhy.model;

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
	private BigDecimal yybLat;

	@Column(name="yyb_long")
	private BigDecimal yybLong;

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

	public BigDecimal getYybLat() {
		return this.yybLat;
	}

	public void setYybLat(BigDecimal yybLat) {
		this.yybLat = yybLat;
	}

	public BigDecimal getYybLong() {
		return this.yybLong;
	}

	public void setYybLong(BigDecimal yybLong) {
		this.yybLong = yybLong;
	}

}