package fr.upmc.dar.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class University {
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
	private String longitude ;
	@Column
	private String latitude;
	
	@Column(columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public University() {
		super();
	}

	public University(String id, String name, String street, String zipCode, String city, String longitude,
			String latitude) {
		super();
		this.id = id;
		this.name = name;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.date=new Date();
	}

	public University(String id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.date=new Date();
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
	
	@Override
	public String toString() {
		return "University [id=" + id + ", name=" + name + ", street=" + street + ", zipCode=" + zipCode + ", city="
				+ city + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}

	
}
