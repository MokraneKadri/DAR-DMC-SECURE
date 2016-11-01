package fr.upmc.dar.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Event {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	protected User creator;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<User> candidates;
	
	@Column
	private String eventName;

	@Column
	private String eventDescription;
	
	@Column
	private String eventDate;
	
	@Column
	private String eventTheme;
	
	@Column
	private String eventPlace;
	
	@Column
	private String eventAdresse;
	
	
	public Event() {
		super();
	}
	
	public Event(	
			Integer id,
			String eventName, 
			String eventDescription,
			String eventDate, 
			String eventTheme, 
			String eventPlace, 
			String eventAdresse) 
	{
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.eventTheme = eventTheme;
		this.eventPlace = eventPlace;
		this.eventAdresse = eventAdresse;
	}
	
	public Event(	
			User owner,
			String eventName, 
			String eventDescription,
			String eventDate, 
			String eventTheme, 
			String eventPlace, 
			String eventAdresse) 
	{
		this.creator = owner;
		this.eventName = eventName;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<User> candidates) {
		this.candidates = candidates;
	}
	
}
