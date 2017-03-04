package com.phase2.biz;

import com.phase2.api.bizInterface.Register;
import com.phase2.api.dto.Users;
import com.phase2.api.exception.DuplicateUserNameException;
import com.phase2.api.exception.RegisterException;
import com.phase2.api.exception.UserNotFoundException;
import com.phase2.data.UserDAO;
import com.phase2.data.UserDAOImpl;


public class RegisterImpl implements Register {

	private UserDAO userDAO;
	public RegisterImpl() {
		userDAO = new UserDAOImpl();
	}

	public void addUser(Users user) throws DuplicateUserNameException, RegisterException {
		userDAO.create(user);
	}

	public Users updateUser(Users user) throws UserNotFoundException, RegisterException {
		user = userDAO.update(user);
		return user;
	}

}
