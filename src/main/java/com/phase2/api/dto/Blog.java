package com.phase2.api.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//@Entity
//@XmlRootElement
public class Blog {

	public Blog() {
		super();
	}

	public Blog(String title, String content, Date createDate, Date modifiedDate, String createdBy,
			boolean isFavourite, String[] tags, int userId) {
		super();
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.isFavourite = isFavourite;
		this.tags = tags;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer blogId;
	
	//@ManyToMany(mappedBy="blogs",fetch=FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
	//private Set<Users> users;
	private int userId;
	private String title;
	private String content;
	private Date createDate;
	private Date modifiedDate;
	private String createdBy;
	private boolean isFavourite;
	private String[] tags;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
	private Set<Comments> comments = new HashSet<Comments>();
	
	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

/*	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setIsFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Set<Comments> getComments() {
		if(comments == null) {
			comments = new HashSet<Comments>();
		}
		return comments;
	}

	public void setComments(Set<Comments> comments) {
		this.comments = comments;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("Blog ID: "+this.blogId+" ");
		strBuilder.append("Blog Name : " +this.title);
		return strBuilder.toString();
	} 
	
}
