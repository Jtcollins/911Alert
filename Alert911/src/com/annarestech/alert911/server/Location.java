package com.annarestech.alert911.server;

public class Location {
	private int zipCode;
	private UserBase uBase;
	private CityDepartment cDep;
	
	Location(CityDepartment dep, int zip)	{
		this.zipCode = zip;
		cDep = dep;
		uBase = new UserBase(cDep);
	}
}
