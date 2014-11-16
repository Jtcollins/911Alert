package com.annarestech.alert911.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;


public class CityDepartment {
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	
	private Hashtable<Integer, Location> locTable;
	private CallStream callStream;
	
	CityDepartment(String account, String auth, String urlStream)	{
		
		URL stream;
		try {
			stream = new URL(urlStream);
			callStream = new CallStream(stream);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TextService tService = new TextService(ACCOUNT_SID, AUTH_TOKEN);
		locTable = new Hashtable();
		
		
	}
}
