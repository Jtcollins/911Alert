package com.annarestech.alert911.server;

import java.util.*; 

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.util.*; 
import com.twilio.sdk.*; 
import com.twilio.sdk.resource.factory.*; 
import com.twilio.sdk.resource.instance.*; 
import com.twilio.sdk.resource.list.*; 
import com.google.gwt.user.client.Random;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.TwilioRestResponse;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.AvailablePhoneNumber;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Conference;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.instance.Participant;
import com.twilio.sdk.resource.list.AccountList;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;
import com.twilio.sdk.resource.list.ParticipantList;
import com.twilio.sdk.verbs.TwiMLException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class TextService {
	private MessageFactory messageFactory;
	private List<NameValuePair> params;
	private String fromNum;
	
	
	TextService(String account, String auth)	{
		TwilioRestClient client = new TwilioRestClient(account, auth);
		fromNum = "+15107275968";
		// Build the parameters 
		 params = new ArrayList<NameValuePair>();  
		 params.add(new BasicNameValuePair("From", fromNum));    
		 
		 messageFactory = client.getAccount().getMessageFactory();
	}
	
	public boolean textAllinZip(Location loc, String message)	{
		try	{
			 Message mess = messageFactory.create(params); 
			 System.out.println(mess.getSid()); 
			 messageFactory.create(params);
			 return true;
		 } catch(Exception e)	{
			 System.out.println("Message Failed Creation");
			 return false;
		 }
	}
	
	public boolean textNum(String number, String message)	{
		try {
			params.add(new BasicNameValuePair("To", number));
			params.add(new BasicNameValuePair("Body", message));
			Message m = messageFactory.create(params);
			return true;
		} catch (TwilioRestException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	/**
	 * 
	 * @param phone
	 * @return a random integer that will be used to confirm the User with the number.
	 */
	public int textConfirmation(String phone)	{
		int rand = Random.nextInt(1000000);
		try	{
			 Message message = messageFactory.create(params); 
			 System.out.println(message.getSid()); 
		 } catch(Exception e)	{
			 System.out.println("Message Failed Creation");
		 }
		return rand;
	}
	
}
