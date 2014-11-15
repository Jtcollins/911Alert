package com.annarestech.alert911.server;

public class Call {
	
	private String zipCode;
	private String details;
	private int callerID;
	
	Call(String zip, String dets, String callerID)	{
		zipCode = zip;
		details = dets;
		this.callerID = Integer.parseInt(callerID);
	}
}
