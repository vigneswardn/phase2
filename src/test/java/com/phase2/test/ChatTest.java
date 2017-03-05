package com.phase2.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class ChatTest {

	public ChatTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
    public void testGetAllChats() throws Exception {
        WebConversation     conversation = new WebConversation();
        WebRequest  request = new GetMethodWebRequest( 
            "http://104.197.74.201:8080/phase2/blogger/chat/getChats" );
        WebResponse response = conversation.getResponse( request );
         assertTrue("No Chats Found",response.getText().length() > 0 );
    }

}
