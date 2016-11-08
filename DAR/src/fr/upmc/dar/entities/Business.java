package fr.upmc.dar.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Business {

	@Id
	@Column(unique=true)
	private String id;
	@Column
	private String name;
	@Column
	private String street;
	@Column
	private String zipCode;
	@Column
	private String city;
	@Column
	private String phone;
	@Column
	private String longitude ;
	@Column
	private String latitude;
	@Column
	private String establishmentWebsite;
	@Column
	private String op0;
	@Column
	private String cl0;
	@Column
	private String op1;
	@Column
	private String cl1;
	@Column
	private String op2;
	@Column
	private String cl2;
	@Column
	private String op3;
	@Column
	private String cl3;
	@Column
	private String op4;
	@Column
	private String cl4;
	@Column
	private String op5;
	@Column
	private String cl5;
	@Column
	private String op6;
	@Column
	private String cl6;


	public String getOp0() {
		return op0;
	}

	public void setOp0(String op0) {
		this.op0 = op0;
	}

	public String getCl0() {
		return cl0;
	}

	public void setCl0(String cl0) {
		this.cl0 = cl0;
	}

	public String getOp1() {
		return op1;
	}

	public void setOp1(String op1) {
		this.op1 = op1;
	}

	public String getCl1() {
		return cl1;
	}

	public void setCl1(String cl1) {
		this.cl1 = cl1;
	}

	public String getOp2() {
		return op2;
	}

	public void setOp2(String op2) {
		this.op2 = op2;
	}

	public String getCl2() {
		return cl2;
	}

	public void setCl2(String cl2) {
		this.cl2 = cl2;
	}

	public String getOp3() {
		return op3;
	}

	public void setOp3(String op3) {
		this.op3 = op3;
	}

	public String getCl3() {
		return cl3;
	}

	public void setCl3(String cl3) {
		this.cl3 = cl3;
	}

	public String getOp4() {
		return op4;
	}

	public void setOp4(String op4) {
		this.op4 = op4;
	}

	public String getCl4() {
		return cl4;
	}

	public void setCl4(String cl4) {
		this.cl4 = cl4;
	}

	public String getOp5() {
		return op5;
	}

	public void setOp5(String op5) {
		this.op5 = op5;
	}

	public String getCl5() {
		return cl5;
	}

	public void setCl5(String cl5) {
		this.cl5 = cl5;
	}

	public String getOp6() {
		return op6;
	}

	public void setOp6(String op6) {
		this.op6 = op6;
	}

	public String getCl6() {
		return cl6;
	}

	public void setCl6(String cl6) {
		this.cl6 = cl6;
	}

	public Business() {
		super();
	}

	public Business(String id,String name, String street, String zipCode, String city,String phone, String longitude, String latitude,
			String establishmentWebsite) {
		this.id=id;
		this.name = name;
		this.phone=phone;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.establishmentWebsite = establishmentWebsite;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getEstablishmentWebsite() {
		return establishmentWebsite;
	}

	public void setEstablishmentWebsite(String establishmentWebsite) {
		this.establishmentWebsite = establishmentWebsite;
	}
	
	public String normalizeHour(String hour){
		if(hour==null){
			return "?";
		}
		return hour.substring(0,2)+"h"+hour.substring(2,hour.length());
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", name=" + name + ", street=" + street + ", zipCode=" + zipCode + ", city="
				+ city + ", phone=" + phone + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", establishmentWebsite=" + establishmentWebsite + ", op0=" + op0 + ", cl0=" + cl0 + ", op1=" + op1
				+ ", cl1=" + cl1 + ", op2=" + op2 + ", cl2=" + cl2 + ", op3=" + op3 + ", cl3=" + cl3 + ", op4=" + op4
				+ ", cl4=" + cl4 + ", op5=" + op5 + ", cl5=" + cl5 + ", op6=" + op6 + ", cl6=" + cl6 + "]";
	}

}
