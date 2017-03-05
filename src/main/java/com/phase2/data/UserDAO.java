package com.phase2.data;

import com.phase2.api.dto.Users;

public interface UserDAO {

	public void create(Users user);
	
	public Users update(Users user);
	
	public Users validate(Users user);
	
}
