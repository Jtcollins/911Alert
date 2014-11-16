package com.annarestech.alert911.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;


public class CityDepartment {
	public static final String ACCOUNT_SID = "AC2178642fee43f41a62ce3016b6f34187";
	public static final String AUTH_TOKEN = "aea844879d5df20922876ada64eb7390";
	
	private Hashtable<Integer, Location> locTable;
	private CallStream callStream;
	private TextService tServ;
	private UserBase uBase;
	public String Dep;
	
	public CityDepartment(String Dept, String account, String auth, String urlStream)	{
		
		URL stream;
		try {
			stream = new URL(urlStream);
			callStream = new CallStream(stream);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		tServ = new TextService(ACCOUNT_SID, AUTH_TOKEN);
		locTable = new Hashtable();
		
	}
	
	public boolean testTexts(String phone, String mess)	{
		tServ.textNum(phone, mess);
		return false;
	}
	
	public User addUser(String name, String phone, String zip)	{
		User user = new User(name, phone, zip);
		return user;
	}
	
}
