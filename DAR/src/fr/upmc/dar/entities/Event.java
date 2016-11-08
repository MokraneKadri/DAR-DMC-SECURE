package fr.upmc.dar.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.json.JSONException;
import org.json.JSONObject;

import fr.upmc.dar.entities.interfaces.IEntity;
import fr.upmc.dar.enums.EventVisibility;

@Entity
public class Event implements IEntity {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	protected User creator;
	
	@ManyToMany(cascade = CascadeType.ALL)
	protected List<User> candidates;
	
	@OneToMany(cascade = CascadeType.ALL)
	protected List<Comment> comments;
	
	@Column
	protected String name;

	@Column
	protected EventVisibility privacy;
	
	@Column
	protected String description;
	
	@Column
	protected String date;	
	
	@Column
	protected String theme;
	
	@Column
	protected String places;
	
	@Column
	protected String address;
	
	/**
	 * Attention ne pas se planter -> Place = Lieu ici
	 */
	
	@ManyToOne(cascade = CascadeType.ALL)
	protected Place place;
	
	@ManyToOne
	protected Business business;
	
	public Event() {
		super();
	}
	
	public Event(	
			User owner,
			String name, 
			String privacy,
			String description,
			String date, 
			String theme, 
			String places,
			String address,
			Place place,
			Business business) throws Exception 
	{
		this.creator = owner;
		this.name = name;
		switch (privacy) {
		case "public":
			this.privacy = EventVisibility.PUBLIC;
			break;
		case "private":
			this.privacy = EventVisibility.PRIVATE;
			break;
		case "group":
			this.privacy = EventVisibility.GROUP;
			break;
		case "university":
			this.privacy = EventVisibility.INTRA_UNI;
			break;
		default:
			throw new Exception("wrong privacy : " + privacy);
		}
		this.description = description;
		this.date = date;
		this.theme = theme;
		this.places = places;
		this.address = address;
		this.candidates = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.place = place;
	}
	
	public Event(	
			User owner,
			String name, 
			String privacy,
			String description,
			String date, 
			String theme, 
			String places,
			String address,
			Place place) throws Exception 
	{
		this.creator = owner;
		this.name = name;
		switch (privacy) {
		case "public":
			this.privacy = EventVisibility.PUBLIC;
			break;
		case "private":
			this.privacy = EventVisibility.PRIVATE;
			break;
		case "group":
			this.privacy = EventVisibility.GROUP;
			break;
		case "university":
			this.privacy = EventVisibility.INTRA_UNI;
			break;
		default:
			throw new Exception("wrong privacy : " + privacy);
		}
		this.description = description;
		this.date = date;
		this.theme = theme;
		this.places = places;
		this.address = address;
		this.candidates = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.place = place;
		this.business = null;
	}

	
	public Event(	
			User owner,
			String eventName, 
			EventVisibility eventprivacy,
			String eventDescription,
			String eventDate, 
			String eventTheme, 
			String eventPlace,
			String eventAdresse,
			List<Comment> comments) 
	{
		this.creator = owner;
		this.name = eventName;
		this.privacy = eventprivacy;
		this.description = eventDescription;
		this.date = eventDate;
		this.theme = eventTheme;
		this.places = eventPlace;
		this.address = eventAdresse;
		this.comments=comments;
	}
		
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventVisibility getPrivacy() {
		return privacy;
	}

	public void setPrivacy(EventVisibility privacy) {
		this.privacy = privacy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCommentsCounts() {
		return comments.size();
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}
	
	public boolean isYelpEvent() {
		return business != null;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("name", name);
		json.put("privacy", EventVisibility.eventVisibilityToString(privacy));
		json.put("description", description);
		json.put("date", date);
		json.put("places", places);
		json.put("theme", theme);
		json.put("address", address);
		json.put("creator", creator.getUserName());
		json.put("comments", comments.size());
		json.put("id", id);
		
		return json;
	}
	
}
