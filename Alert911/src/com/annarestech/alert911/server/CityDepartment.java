package com.annarestech.alert911.server;

import java.net.MalformedURLException;import com.google.appengine.api.ThreadManager;
import java.util.concurrent.atomic.AtomicLong;
import java.net.URL;
import java.util.HashSet;
import java.util.Hashtable;
import com.google.appengine.api.backends.BackendService;
import com.google.appengine.api.backends.BackendServiceFactory;

import com.twilio.*;

/**
 * 
 * @author jessecollins90
 *
 */
public class CityDepartment {
	public static final String ACCOUNT_SID = "AC2178642fee43f41a62ce3016b6f34187";
	public static final String AUTH_TOKEN = "aea844879d5df20922876ada64eb7390";
	public static final String SEATTLE_DATA = "https://data.seattle.gov/resource/7ais-f98f.json?offense_type=";
	
	private Hashtable<String, Location> locTable;
	private HashSet<String> keywords;
	private CallStream callStream;
	public static TextService tServ;
	public static UserBase uBase;
	public String Dep;
	public CityDepartment c;
	AtomicLong counter = new AtomicLong();
	
	/**
	 * 
	 * @param Dept
	 * @param account
	 * @param auth
	 * @param urlStream
	 */
	public CityDepartment(String Dept, String account, String auth, String urlStream)	{
		
		URL stream;
		this.c = this;
		keywords = new HashSet<String>();
		//Example keywords, will set this up so individual cities can choose their own.
		keywords.add("WEAPON-DISCHARGE");
		keywords.add("THREATS-KILL");
		keywords.add("THREATS-WEAPON");
		//Thread crimeLoop = new Thread((Runnable) new CallStream(SEATTLE_DATA, keywords));
		//crimeLoop.start();Thread thread = ThreadManager.createBackgroundThread(new Runnable() {

		tServ = new TextService(ACCOUNT_SID, AUTH_TOKEN);
		locTable = new Hashtable();
		uBase = new UserBase(this);
		thread.start();
	}
	

	/**
	 * Reiterative thread that allows server to poll Socrata's API for new call logs.
	 */
	Thread thread = ThreadManager.createBackgroundThread(new Runnable() {
		  public void run() {
		    try {
		      while (true) {
		        counter.incrementAndGet();
		        callStream = new CallStream(this, c, SEATTLE_DATA, keywords);
		        Thread.sleep(10);
		      }
		    } catch (InterruptedException ex) {
		      throw new RuntimeException("Interrupted in loop:", ex);
		    }
		  }
		});
	
	public boolean testTexts(String phone, String mess)	{
		tServ.textNum(phone, mess);
		return false;
	}
	
	public boolean addUser(String name, String phone, String zip)	{
		User user = new User(name, phone, zip);
		return addUser(user);
	}
	
	// Returns hashtable contaning all Location instances within this CityDepartment instance.
	public Hashtable<String, Location> getLocT()	{
		return locTable;
	}
	
	/**
	 * Add a user to this Userbase for city.
	 * Also adds the user to their userbase in their respective zipcode.
	 * @param u user that is to be added
	 * @return true after user added
	 */
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
		tServ.newUser(u.phoneNumber);
		return true;
	}
	
}
