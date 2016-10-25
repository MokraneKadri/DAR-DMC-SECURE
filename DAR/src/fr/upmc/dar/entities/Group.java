package fr.upmc.dar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="groups")
public class Group {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne 
	protected User owner;
	
	@OneToMany
	protected List<User> members;
	
	@Column
	private String groupName;
	
	@Column
	private String groupDescription;
	
	public Group() {
		super();
	}

	public Group(Integer id, User owner, String groupName, String groupDescription) {
		super();
		this.id = id;
		this.owner = owner;
		this.members = new ArrayList<>();
		this.members.add(owner);
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}
	
	public Group(User owner, String groupName, String groupDescription) {
		super();
		this.owner = owner;
		this.members = new ArrayList<>();
		this.members.add(owner);
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}
		
}
