package fr.upmc.dar.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Group {

	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Basic
	private String groupName;
	
	@Basic
	private int creatorId;
	
	@Basic
	private String creatorName;

	@Basic
	private String groupDescription;

	
	
	
	
	
	
	
	
	
	
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Group(Integer id, String groupName, int creatorId, String creatorName, String groupDescription) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.groupDescription = groupDescription;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	
	
	
}
