package com.phase2.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.phase2.api.dto.Users;

public class UserDAOImpl implements UserDAO {

	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("blogger");
	
	public UserDAOImpl() {
		super();
	}

	@Override
	public void create(Users user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Users update(Users user) {
		EntityManager em = factory.createEntityManager();
		Users existingUser = em.find(Users.class,user.getUserId());
		if(user.getEmail() != null) {
			existingUser.setEmail(user.getEmail());
		}
		if(user.getFirstName() != null) {
			existingUser.setFirstName(user.getFirstName());
		}
		if(user.getLastName() != null) {
			existingUser.setLastName(user.getLastName());
		}
		if(user.getPassword() != null) {
			existingUser.setPassword(user.getPassword());
		}
		if(user.getPhone() != null) {
			existingUser.setPhone(user.getPhone());
		}
		if(user.getUserName() != null) {
			existingUser.setUserName(user.getUserName());
		}
		em.getTransaction().begin();
		existingUser = em.merge(existingUser);
		em.getTransaction().commit();
		em.close();
		return existingUser;
	}

	@Override
	public Users validate(Users user) {
		EntityManager em = factory.createEntityManager();
		Users existingUser = em.find(Users.class,user.getUserId());
		String result = null;
		if(existingUser != null) {
			String passwordFromUI = user.getPassword();
			String passwordFromDB = existingUser.getPassword();
			if(passwordFromUI.equals(passwordFromDB)) {
				result = "success";
			}
		}
		return existingUser;
	}



	

}
