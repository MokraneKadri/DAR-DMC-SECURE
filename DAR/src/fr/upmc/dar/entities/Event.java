package fr.upmc.dar.entities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Basic
	private String eventName;
	
	@Basic
	private int creatorId;
	
	@Basic
	private String creatorName;

	@Basic
	private String eventDescription;
	
	@Basic
	private String eventDate;
	
	@Basic
	private String eventTheme;
	
	
	@Basic
	private String eventPlace;
	@Basic
	private String eventAdresse;
	
	
	
	
	
	
	
	
	
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(Integer id, String eventName, int creatorId, String creatorName, String eventDescription,
			String eventDate, String eventTheme, String eventPlace, String eventAdresse) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTheme = eventTheme;
		this.eventPlace = eventPlace;
		this.eventAdresse = eventAdresse;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTheme() {
		return eventTheme;
	}
	public void setEventTheme(String eventTheme) {
		this.eventTheme = eventTheme;
	}
	public String getEventPlace() {
		return eventPlace;
	}
	public void setEventPlace(String eventPlace) {
		this.eventPlace = eventPlace;
	}
	public String getEventAdresse() {
		return eventAdresse;
	}
	public void setEventAdresse(String eventAdresse) {
		this.eventAdresse = eventAdresse;
	}
	
	
	
	
	
	
	
	
	
	
}
