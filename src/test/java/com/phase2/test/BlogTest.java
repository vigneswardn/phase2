package com.phase2.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class BlogTest {

	public BlogTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
    public void testGetAllBlogs() throws Exception {
        WebConversation     conversation = new WebConversation();
        WebRequest  request = new GetMethodWebRequest( 
            "http://104.197.74.201:8080/phase2/blogger/blog/getAllBlogs" );
        WebResponse response = conversation.getResponse( request );
         assertTrue("No Blogs Found",response.getText().length() > 0 );
    }

	@Test
    public void testGetBlogs() throws Exception {
		 WebConversation     conversation = new WebConversation();
	        WebRequest  request = new GetMethodWebRequest( 
	            "http://104.197.74.201:8080/phase2/blogger/blog/getAllBlogs" );
	        WebResponse response = conversation.getResponse( request );
	         assertTrue("No Blogs Found",response.getText().length() > 0 );
    }

	@Test
    public void testSearchBlogs() throws Exception {
		 WebConversation     conversation = new WebConversation();
	        WebRequest  request = new GetMethodWebRequest( 
	            "http://104.197.74.201:8080/phase2/blogger/blog/getAllBlogs" );
	        WebResponse response = conversation.getResponse( request );
	         assertTrue("No Blogs Found",response.getText().length() > 0 );
    }
	
}
