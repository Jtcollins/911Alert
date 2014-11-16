package com.annarestech.alert911.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Hashtable;

import com.twilio.*;


public class CityDepartment {
	public static final String ACCOUNT_SID = "AC2178642fee43f41a62ce3016b6f34187";
	public static final String AUTH_TOKEN = "aea844879d5df20922876ada64eb7390";
	public static final String SEATTLE_DATA = "https://data.seattle.gov/resource/7ais-f98f.json?offense_type=";
	
	private Hashtable<String, Location> locTable;
	private HashSet<String> keywords;
	private CallStream callStream;
	private TextService tServ;
	private UserBase uBase;
	public String Dep;
	
	public CityDepartment(String Dept, String account, String auth, String urlStream)	{
		
		URL stream;
		keywords = new HashSet<String>();
		keywords.add("WEAPON-DISCHARGE");
		keywords.add("THREATS-KILL");
		keywords.add("THREATS-WEAPON");
		//Thread crimeLoop = new Thread((Runnable) new CallStream(SEATTLE_DATA, keywords));
		//crimeLoop.start();

		tServ = new TextService(ACCOUNT_SID, AUTH_TOKEN);
		locTable = new Hashtable();
		uBase = new UserBase(this);
	}
	
	public boolean testTexts(String phone, String mess)	{
		tServ.textNum(phone, mess);
		return false;
	}
	
	public boolean addUser(String name, String phone, String zip)	{
		User user = new User(name, phone, zip);
		return addUser(user);
	}
	
	public boolean addUser(User u)	{
		uBase.addUser(u);
		if(locTable.containsKey(u.getZip()))	{
			Location curr = locTable.get(u.getZip());
			curr.addUser(u);
		} else	{
			Location loc = new Location(this, u.getZip());
			locTable.put(u.getZip(), loc);
			loc.addUser(u);
		}
		System.out.println("User added to Location and City");
		return true;
	}
	
}
