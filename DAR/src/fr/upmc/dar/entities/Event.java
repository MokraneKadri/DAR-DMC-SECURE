package fr.upmc.dar.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	
	@ManyToMany//(cascade = CascadeType.ALL)
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
	protected Date date;
	
	@Column 
	protected String hour;
	
	@Column
	protected String theme;
	
	@Column
	protected Integer places;
	
	@Column
	protected String address;
	
	@Column
	String place;
	
	@Column
	String placeType;
	
	@ManyToOne
	protected Business business;
	
	@Column
	protected Date timestamp;
	
	@ManyToOne
	protected University university;
	
	public Event() {
		super();
	}
	
	public Event(	
			User owner,
			String name, 
			String privacy,
			String description,
			String date,
			String hour,
			String theme, 
			String places,
			String address,
			String placeType,
			String place,
			Business business,
			University university) throws ParseException
	{
		this.creator = owner;
		this.name = name;
		this.privacy = EventVisibility.stringToEventVisibility(privacy);
		this.description = description;
		this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		this.hour = hour;
		this.theme = theme;
		this.places = Integer.valueOf(places);
		this.address = address;
		this.candidates = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.placeType = placeType;
		this.place = place;
		this.business = business;
		this.timestamp = new Date();
		this.university = university;
	}
	
	public Event(	
			User owner,
			String name, 
			String privacy,
			String description,
			String date,
			String hour,
			String theme, 
			String places,
			String address,
			String placeType,
			String place,
			University university) throws ParseException 
	{
		this.creator = owner;
		this.name = name;
		this.privacy = EventVisibility.stringToEventVisibility(privacy);
		this.description = description;
		this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		this.hour = hour;
		this.theme = theme;
		this.places = Integer.valueOf(places);
		this.address = address;
		this.candidates = new ArrayList<>();
		this.comments = new ArrayList<>();
		this.placeType = placeType;
		this.place = place;
		this.business = null;
		this.timestamp = new Date();
		this.university = university;
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
	
	public void addCandidate(User candidate) {
		this.candidates.add(candidate);
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

	public Date getDate() {
		return date;
	}
	
	public String getDateToString() {
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDate(String date) throws ParseException {
		this.date = new SimpleDateFormat("dd-MM-yyyy").parse(date);
	}
	
	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
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
	
	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = Integer.valueOf(places);
	}
	
	public void setPlaces(Integer places) {
		this.places = places;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
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
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	@Override
	public JSONObject toJSONObject() throws JSONException {
		JSONObject json = new JSONObject();
		
		json.put("name", name);
		json.put("privacy", EventVisibility.eventVisibilityToString(privacy));
		json.put("description", description);
		json.put("date", getDateToString());
		json.put("places", places);
		json.put("theme", theme);
		json.put("address", address);
		json.put("creator", creator.getUserName());
		json.put("comments", comments.size());
		json.put("id", id);
		json.put("place_type", placeType);
		json.put("place_name", place);
		json.put("timestamp", new SimpleDateFormat("dd-MM-yyyy").format(timestamp));
		json.put("is_yelp", isYelpEvent());
		json.put("hour", hour);
		
		return json;
	}
	
}
