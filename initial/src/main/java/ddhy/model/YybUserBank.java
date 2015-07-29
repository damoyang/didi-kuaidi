package ddhy.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the yyb_user_bank database table.
 * 
 */
@Entity
@Table(name="yyb_user_bank")
public class YybUserBank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_banknum")
	private String yybBanknum;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_bindingdate")
	private Date yybBindingdate;

	@Column(name="yyb_cardtype")
	private String yybCardtype;

	@Column(name="yyb_ownbank")
	private String yybOwnbank;

	@Column(name="yyb_owner")
	private String yybOwner;

	@Column(name="yyb_status")
	private String yybStatus;

	@Column(name="yyb_userid")
	private int yybUserid;

    public YybUserBank() {
    }

	public int getYybId() {
		return this.yybId;
	}

	public void setYybId(int yybId) {
		this.yybId = yybId;
	}

	public String getYybBanknum() {
		return this.yybBanknum;
	}

	public void setYybBanknum(String yybBanknum) {
		this.yybBanknum = yybBanknum;
	}

	public Date getYybBindingdate() {
		return this.yybBindingdate;
	}

	public void setYybBindingdate(Date yybBindingdate) {
		this.yybBindingdate = yybBindingdate;
	}

	public String getYybCardtype() {
		return this.yybCardtype;
	}

	public void setYybCardtype(String yybCardtype) {
		this.yybCardtype = yybCardtype;
	}

	public String getYybOwnbank() {
		return this.yybOwnbank;
	}

	public void setYybOwnbank(String yybOwnbank) {
		this.yybOwnbank = yybOwnbank;
	}

	public String getYybOwner() {
		return this.yybOwner;
	}

	public void setYybOwner(String yybOwner) {
		this.yybOwner = yybOwner;
	}

	public String getYybStatus() {
		return this.yybStatus;
	}

	public void setYybStatus(String yybStatus) {
		this.yybStatus = yybStatus;
	}

	public int getYybUserid() {
		return this.yybUserid;
	}

	public void setYybUserid(int yybUserid) {
		this.yybUserid = yybUserid;
	}

}