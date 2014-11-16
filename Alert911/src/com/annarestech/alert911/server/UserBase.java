package com.annarestech.alert911.server;

import java.util.ArrayList;

public class UserBase {
	
	private ArrayList<User> uBase;
	private CityDepartment city;
	
	UserBase(CityDepartment city)	{
		this.city = city;
	}
	
	public boolean addUser(String name, String phone, String zip)	{
		User curr = new User(name, phone, zip);
		return false;
	}
	
}
