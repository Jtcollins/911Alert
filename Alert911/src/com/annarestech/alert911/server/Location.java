package com.annarestech.alert911.server;

public class Location {
	private String zipCode;
	public UserBase uBase;
	private CityDepartment cDep;
	
	Location(CityDepartment dep, String zip)	{
		this.zipCode = zip;
		cDep = dep;
		uBase = new UserBase(cDep);
	}
	
	public boolean addUser(User u)	{
		return uBase.addUser(u);
	}
}
