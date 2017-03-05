package com.phase2.biz;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Decoder;

import com.phase2.api.bizInterface.Register;
import com.phase2.api.dto.Users;
import com.phase2.api.exception.DuplicateUserNameException;
import com.phase2.api.exception.RegisterException;
import com.phase2.api.exception.UserNotFoundException;
import com.phase2.data.UserDAO;
import com.phase2.data.UserDAOImpl;
import com.phase2.util.BlogUtil;


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

	@Override
	public Map<String,Object> validateUser(String authString) throws UserNotFoundException, RegisterException {
		String[] authParts = authString.split("\\s+");
		String authInfo = authParts[1];	
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(authInfo);
		String decodedAuth = new String(bytes);
		
		System.out.println(decodedAuth);
		Users user = new Users();
		if(decodedAuth != null) {
			String [] userInfo = decodedAuth.split(":");
			user.setUserName(userInfo[0]);
			user.setPassword(userInfo[1]);
		}
		Users userObj = userDAO.validate(user);
		String token = null;
		if(userObj != null) {
	        String secret = "our_secret";
	        Date date = new Date();
	        Long expire = (date.getTime()/1000) + 300;
	       
	        try {
	        	token = BlogUtil.getBinaryToken(secret, expire);
			} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("TOKEN", token);
		resultMap.put("EXPIRY", 1000);
		resultMap.put("User", userObj);
		
		return resultMap;
	}

}
