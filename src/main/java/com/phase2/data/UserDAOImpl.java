package com.phase2.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
		Query query = em.createNativeQuery("select a.* from Users a where userName =:userName",Users.class);
		query.setParameter("userName", user.getUserName());
		List<Users> users = (List<Users>) query.getResultList();
		Users returnUser = null;
		if(users != null && users.get(0)!=null) {
			String passwordFromUI = user.getPassword();
			String passwordFromDB = users.get(0).getPassword();
			if(passwordFromUI.equals(passwordFromDB)) {
				returnUser  = users.get(0);
			}
		}
		return returnUser;
	}

	@Override
	public Users readById(Users user) {
		EntityManager em = factory.createEntityManager();
		user = em.find(Users.class, user.getUserId());
		em.close();
		return user;
	}



	

}
