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
	private String open;
	@Column
	private String closed;
	
	
	public Business() {
		super();
	}
	
	public Business(String id,String name, String street, String zipCode, String city,String phone, String longitude, String latitude,
			String establishmentWebsite,String open,String closed) {
		this.id=id;
		this.name = name;
		this.phone=phone;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.establishmentWebsite = establishmentWebsite;
		this.open=open;
		this.closed=closed;
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

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getClosed() {
		return closed;
	}

	public void setClosed(String closed) {
		this.closed = closed;
	}
	
	
}
