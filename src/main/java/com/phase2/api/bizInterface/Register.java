package com.phase2.api.bizInterface;

import java.util.Map;

import com.phase2.api.dto.Users;
import com.phase2.api.exception.DuplicateUserNameException;
import com.phase2.api.exception.InvalidUserDetailsException;
import com.phase2.api.exception.RegisterException;
import com.phase2.api.exception.UserNotFoundException;

public interface Register {

	public void addUser(Users user) throws InvalidUserDetailsException, DuplicateUserNameException, RegisterException;

	public Users updateUser(Users user) throws UserNotFoundException, RegisterException;
	
	public Map<String, Object> validateUser(String authString) throws UserNotFoundException, RegisterException;

	public Users getUserById(Users user) throws UserNotFoundException, RegisterException;
}
