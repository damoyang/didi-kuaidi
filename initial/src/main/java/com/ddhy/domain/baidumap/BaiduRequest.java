package com.ddhy.domain.baidumap;

public class BaiduRequest {
	public String ak="77b25ae37a0e5d7be217a49dec46f773";
	public String geotable_id="116591";
	public int coord_type=1;
	public double latitude;
	public double longitude;
	public String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAk() {
		return ak;
	}
	public void setAk(String ak) {
		this.ak = ak;
	}
	public String getGeotable_id() {
		return geotable_id;
	}
	public void setGeotable_id(String geotable_id) {
		this.geotable_id = geotable_id;
	}
	public int getCoord_type() {
		return coord_type;
	}
	public void setCoord_type(int coord_type) {
		this.coord_type = coord_type;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public BaiduRequest(String id,Double lat,Double lng){
		this.id=id;
		this.latitude=lat;
		this.longitude=lng;
	}
	

}
