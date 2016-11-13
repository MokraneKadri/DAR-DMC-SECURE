package fr.upmc.dar.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Proxy;

import fr.upmc.dar.tools.PasswordEncryptor;

@Entity
@Proxy(lazy = false)
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String userName;

	@Column(unique = true)
	private String eMail;

	@Column
	private String password;

	@Column
	private String street;
	@Column
	private int zip;
	@Column
	

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}


	@Column
	private String etablissement;

	@Column
	private String cursus;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User( String fname,String lname, String userName, String eMail, String password,String street,int zip,String city ,String etablissement,
			String cursus) {
		super();

		this.firstName = fname;
		this.lastName = lname;
		this.userName=userName;
		this.eMail = eMail;
		this.password = PasswordEncryptor.encryptPassword(password);
		this.city=city;
		this.zip=zip;
		this.street=street;
		this.etablissement = etablissement;
		this.cursus = cursus;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public String getCursus() {
		return cursus;
	}

	public void setCursus(String cursus) {
		this.cursus = cursus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public boolean equals (User other){
		return this.getId()==other.getId();
	}
	
	private String city;
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getZip() {
		return zip;
	}



}
