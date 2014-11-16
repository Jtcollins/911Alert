package com.annarestech.alert911.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Call {
	private static final String LATLON_TEMPLATE = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
	private String latlong;
	private int zipCode;
	private String details;
	private int callerID;
	private Location loc;
	
	Call(float lat, float longitude, String hundredBlock)	{
		latlong = LATLON_TEMPLATE + lat + "," + longitude;
		details = hundredBlock;
		String rawString = "";
		BufferedReader contentURL;
		try {
			URL u = new URL(latlong);
			contentURL = new BufferedReader(new InputStreamReader(u.openStream()));
			String curr;
			
			while ((curr = contentURL.readLine()) != null)	{
				rawString += curr;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JsonParser parse = new JsonParser();
		JsonElement elem = parse.parse(rawString);
		//JsonArray searchArray = elem.getAsJsonArray();
		JsonObject searchResults = elem.getAsJsonObject();
		JsonArray searchArray = (JsonArray) (searchResults.get("results"));
		JsonObject addressObject =  (JsonObject) searchArray.get(0);
		JsonArray addressArray = (JsonArray) addressObject.get("address_components");
		for(int i = 0; i < addressArray.size(); i++)	{
			if(((JsonElement) ((JsonObject) addressArray.get(i)).get("types")).toString().equals("[\"postal_code\"]"))	{
				zipCode = Integer.parseInt(((JsonElement) ((JsonObject) addressArray.get(i)).get("short_name")).toString().substring(1, 6));
			}	
			
		}
		
		
	}
	
	
}
