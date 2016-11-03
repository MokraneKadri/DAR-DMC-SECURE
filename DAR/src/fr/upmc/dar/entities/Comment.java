package fr.upmc.dar.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {

	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	protected User creator;
	
	private String commentContent;
	
	public Comment(User creator, String commentContent, String commentDate) {
		super();
		this.creator = creator;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
	}

	public Integer getId() {
		return id;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	private String commentDate;
	
	
}
