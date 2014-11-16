package com.annarestech.alert911.server;

public class CityDepartment {
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	
	CityDepartment(String account, String auth)	{
		
		TextService tService = new TextService(ACCOUNT_SID, AUTH_TOKEN);
	}
}
