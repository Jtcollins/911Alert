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
import com.twilio.sdk.TwilioRestException;
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
	
	/**
	 * Initializes connection with Twilio service
	 * @param account provided by twilio specific to client
	 * @param auth provided by twilio specific to client
	 */
	TextService(String account, String auth)	{
		TwilioRestClient client = new TwilioRestClient(account, auth);
		fromNum = "+15107275968";
		// Build the parameters 
		 params = new ArrayList<NameValuePair>();  
		 params.add(new BasicNameValuePair("From", fromNum));    
		 
		 messageFactory = client.getAccount().getMessageFactory();
	}
	
	/**
	 * Will text every user in the Userbase within specified location.
	 * @param loc the instance of location of specific zip
	 * @param message
	 * @return bool, whether message was sent successfully
	 */
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
	
	/**
	 * Texts all users in provided Userbase
	 * @param uB
	 * @param message
	 * @return true when texting is completed.
	 */
	public boolean textAll(UserBase uB, String message)	{
		for(int i = 0; i < uB.uBase.size(); i++)	{
			User curr = uB.uBase.get(i);
			textNum(curr.phoneNumber, message);
		}
		return true;

	}
	
	/**
	 * Sends a welcome SMS to new users.
	 * @param number number to sms
	 * @return bool whether text was successful.
	 */
	public boolean newUser(String number)	{
		String welcome = "Thank you for signing up, you will now be alerted";
		return textNum(number, welcome);
	}
	
	/**
	 * Texts a specific number
	 * @param number
	 * @param message
	 * @return whether text was successful
	 */
	public boolean textNum(String number, String message)	{
		try {
			params.add(new BasicNameValuePair("To", number));
			params.add(new BasicNameValuePair("Body", message));
			Message m = messageFactory.create(params);
			params.clear();
			params.add(new BasicNameValuePair("From", fromNum));
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
