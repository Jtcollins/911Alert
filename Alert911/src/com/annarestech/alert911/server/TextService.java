package com.annarestech.alert911.server;

import java.util.*; 

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.*; 
import com.twilio.sdk.resource.factory.*; 
import com.twilio.sdk.resource.instance.*; 
import com.twilio.sdk.resource.list.*; 

public class TextService {
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	
	TextService()	{
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
		// Build the parameters 
		 List<NameValuePair> params = new ArrayList<NameValuePair>();  
		 params.add(new BasicNameValuePair("From", "+15107275968"));    
	 
		 MessageFactory messageFactory = client.getAccount().getMessageFactory();
		 try	{
			 Message message = messageFactory.create(params); 
			 System.out.println(message.getSid()); 
		 } catch(Exception e)	{
			 System.out.println("Message Failed Creation");
		 }
	}
	
}
