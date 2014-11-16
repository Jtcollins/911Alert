package com.annarestech.alert911.server;

public class User {

	private String zipCode;
	private String phoneNumber;
	private String emailAddress;
	private String name;
	
	public User(String name, String phone, String zip) {
		this.name = name;
		this.phoneNumber = phone;
		this.zipCode = zip;
	}
	
	
	
}
