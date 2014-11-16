package com.annarestech.alert911;

import com.annarestech.alert911.server.CityDepartment;
import com.annarestech.alert911.server.User;
import com.annarestech.alert911.server.*;

public class MainTester {
	
	public static boolean testSingleText(CityDepartment c, String phone, String mess)	{
		c.testTexts(phone, mess);
		return false;
	}
	
	public boolean testMultiText(CityDepartment c)	{
		return false;
	}
	
	public boolean testLocationText(CityDepartment c, String zipCode)	{
		return false;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CityDepartment city = new CityDepartment("Seattle","", "", "http://www.google.com");
		String myPhone = "+15104093175";
		User me = new User("Jesse", myPhone, "98101");
		testSingleText(city, myPhone, "It works!!");
	}

}
