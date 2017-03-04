package com.phase2.api.dto;

import java.util.List;

public class Invites {

	public Invites() {
		super();
	}
	
	private Integer userId;
	
	private List<String> emails;

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}