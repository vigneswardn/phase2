package com.phase2.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Base64;

import com.phase2.api.dto.Users;

public class BlogUtil {

	public BlogUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static String generateToken(Users user) {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[100];
		random.nextBytes(bytes);
		String token = bytes.toString();
		System.out.println("Token for User : "+user.getUserName()+" is :"+token);
		return token;
	}
	
    // generate token
    public static String getBinaryToken(String secret, Long expire) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        
        String urlData = secret + expire.toString();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(urlData.getBytes());
        String token = Base64.encodeBase64(messageDigest).toString();
        System.out.println(token);
        return token;
        
    }
	

    // main method
 /*   public static void main(String[] args) throws Exception{
        
        String secret = "your_secret";
        String path = "/path/to/your/file.jpg";
        
        Date date = new Date();
        // expiry time (e.g. 300 seconds)
        Long expire = (date.getTime()/1000) + 300;
        String token = "" ;
        
        try {
            String md5 = getBinaryToken(path, secret, expire);

            // final secured URL with token and expire variables
            String url = "http://demo-1.kxcdn.com" + path + "?token=" + md5 + "&expire=" + expire.toString() ;
            System.out.println("genrated url : " + url);
        } catch (Exception e){
            e.printStackTrace();
        }

    }*/
	

}
