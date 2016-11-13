package fr.upmc.dar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class Friends {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@OneToOne
	protected User user1;

	@OneToOne
	protected User user2;


	public Friends(){
		super();
	}

	public Friends(User one,User two){
		this.user1=one;
		this.user2=two;
	}

	public int getId(){
		return id;
	}

	public User getUser1(){
		return user1;
	}

	public User getUser2(){
		return user2;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}
