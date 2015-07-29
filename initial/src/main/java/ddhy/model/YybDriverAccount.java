package ddhy.model;

import java.io.Serializable;


import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the yyb_driver_account database table.
 * 
 */
@Entity
@Table(name="yyb_driver_account")
public class YybDriverAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="yyb_id")
	private int yybId;

	@Column(name="yyb_accountbalance")
	private BigDecimal yybAccountbalance=new BigDecimal(0);

	@Column(name="yyb_breachnum")
	private int yybBreachnum;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_checkdate")
	private Date yybCheckdate;

    @Lob()
	@Column(name="yyb_driverdesc")
	private String yybDriverdesc;

	@Column(name="yyb_drivername")
	private String yybDrivername;

	@Column(name="yyb_driverphoto")
	private String yybDriverphoto;

	@Column(name="yyb_driverstatus")
	private String yybDriverstatus;

	@Column(name="yyb_drivertype")
	private String yybDrivertype="个人";

	@Column(name="yyb_drivinglicence")
	private String yybDrivinglicence;

	@Column(name="yyb_lastlogindate")
	private Timestamp yybLastlogindate=new Timestamp(new Date().getTime());

	@Column(name="yyb_loginnum")
	private int yybLoginnum;

	@Column(name="yyb_owncompany")
	private String yybOwncompany;

	@Column(name="yyb_password")
	private String yybPassword;

	@Column(name="yyb_paypassword")
	private String yybPaypassword;

	@Column(name="yyb_phone")
	private String yybPhone;

	@Column(name="yyb_receiveorder")
	private int yybReceiveorder;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="yyb_regdate")
	private Date yybRegdate=new Date();

	@Column(name="yyb_token")
	private String yybToken;

    public YybDriverAccount() {
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

	public int getYybBreachnum() {
		return this.yybBreachnum;
	}

	public void setYybBreachnum(int yybBreachnum) {
		this.yybBreachnum = yybBreachnum;
	}

	public Date getYybCheckdate() {
		return this.yybCheckdate;
	}

	public void setYybCheckdate(Date yybCheckdate) {
		this.yybCheckdate = yybCheckdate;
	}

	public String getYybDriverdesc() {
		return this.yybDriverdesc;
	}

	public void setYybDriverdesc(String yybDriverdesc) {
		this.yybDriverdesc = yybDriverdesc;
	}

	public String getYybDrivername() {
		return this.yybDrivername;
	}

	public void setYybDrivername(String yybDrivername) {
		this.yybDrivername = yybDrivername;
	}

	public String getYybDriverphoto() {
		return this.yybDriverphoto;
	}

	public void setYybDriverphoto(String yybDriverphoto) {
		this.yybDriverphoto = yybDriverphoto;
	}

	public String getYybDriverstatus() {
		return this.yybDriverstatus;
	}

	public void setYybDriverstatus(String yybDriverstatus) {
		this.yybDriverstatus = yybDriverstatus;
	}

	public String getYybDrivertype() {
		return this.yybDrivertype;
	}

	public void setYybDrivertype(String yybDrivertype) {
		this.yybDrivertype = yybDrivertype;
	}

	public String getYybDrivinglicence() {
		return this.yybDrivinglicence;
	}

	public void setYybDrivinglicence(String yybDrivinglicence) {
		this.yybDrivinglicence = yybDrivinglicence;
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

	public String getYybOwncompany() {
		return this.yybOwncompany;
	}

	public void setYybOwncompany(String yybOwncompany) {
		this.yybOwncompany = yybOwncompany;
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

	public int getYybReceiveorder() {
		return this.yybReceiveorder;
	}

	public void setYybReceiveorder(int yybReceiveorder) {
		this.yybReceiveorder = yybReceiveorder;
	}

	public Date getYybRegdate() {
		return this.yybRegdate;
	}

	public void setYybRegdate(Date yybRegdate) {
		this.yybRegdate = yybRegdate;
	}

	public String getYybToken() {
		return this.yybToken;
	}

	public void setYybToken(String yybToken) {
		this.yybToken = yybToken;
	}

}