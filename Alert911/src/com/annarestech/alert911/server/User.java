package com.annarestech.alert911.server;

public class User {

	public String zipCode;
	public String phoneNumber;
	public String emailAddress;
	public String name;
	
	public User(String name, String phone, String zip) {
		this.name = name;
		this.phoneNumber = phone;
		this.zipCode = zip;
	}
	
	public String getZip()	{
		return this.zipCode;
	}
	
	
	
}
