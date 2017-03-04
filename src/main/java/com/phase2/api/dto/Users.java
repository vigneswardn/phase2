package com.phase2.api.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonBackReference;

@Entity
@XmlRootElement
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Users {
	

	public Users() {
		super();
	}
	
	public Users(int userId, String userName, String firstName, String lastName, String password, String email,
			String phone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.phone = phone;
	}

//	public Users(int userId, Set<Blog> blogs) {
	public Users(int userId) {
		super();
		this.userId = userId;
		//this.blogs = blogs;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phone;
	
//	@JoinTable(name="users_blogs",joinColumns={@JoinColumn(name="userId")},inverseJoinColumns={@JoinColumn(name = "blogId")})
//	@ManyToMany(fetch=FetchType.EAGER)
//	@JsonBackReference
//	Set<Blog> blogs;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

/*	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}

	public Set<Blog> getBlogs() {
		if(blogs == null) {
			blogs = new HashSet<Blog>();
		}
		return blogs;
	}*/


	@Override
    public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(" User ID: "+this.userId+" ");
		strBuilder.append(" User Name : " +this.userName);
		strBuilder.append(" User email : " +this.email);
		strBuilder.append(" User phone : " +this.phone);
		strBuilder.append(" User password : " +this.password);
		//strBuilder.append(" User blog : "+this.blogs);
		
		return strBuilder.toString();
	}

	
	
}
