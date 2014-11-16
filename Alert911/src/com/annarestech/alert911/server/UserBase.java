package com.annarestech.alert911.server;

import java.io.File;
import java.util.ArrayList;

public class UserBase {
	
	public ArrayList<User> uBase;
	private CityDepartment city;
	//public File t;
	
	UserBase(CityDepartment city)	{
		this.city = city;
		uBase = new ArrayList<User>();
	}
	
	public boolean addUser(String name, String phone, String zip)	{
		User curr = new User(name, phone, zip);
		return false;
	}
	
	public boolean addUser(User u)	{
		return uBase.add(u);
	}
}
