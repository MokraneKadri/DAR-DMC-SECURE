package fr.upmc.dar.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Basic
	private String firstName;
	
	@Basic
	private String lastName;
	
	
	@Basic
	private String userName;
	
	@Basic
	private String eMail;
	

	@Basic
	private String password;
	
	@Basic
	private String etablissement;
	
	@Basic
	private String cursus;

	
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User( String firstName, String lastName,String userName, String eMail, String password, String etablissement,
			String cursus) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName=userName;
		this.eMail = eMail;
		this.password = password;
		this.etablissement = etablissement;
		this.cursus = cursus;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	
	
	
	
	
	
}
