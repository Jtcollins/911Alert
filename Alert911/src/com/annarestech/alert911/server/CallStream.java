package com.annarestech.alert911.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;
import com.socrata.api.Soda2Consumer;

public class CallStream implements Runnable {
	private static final String APP_TOKEN = "7Vkm81ywNzQErCppqcBjKahPe";
	private static final String SECRET_TOKEN = "___LIy9AOTIsdVFOcZC_KP1a4FIjPV6U69yr";
	private static final String LATLON_TEMPLATE = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
	private HashSet<String> keywords;
	public CityDepartment city;
	public String stream;
	
	CallStream(Runnable run, CityDepartment city, String stream, HashSet<String> keys)	{
		this.city = city;
		keywords = keys;
		run();
	}
	
	
	public void crimeLooper()	{
		Iterator<String> iterWord = keywords.iterator();
		String currWord;
		String rawString = "";
		BufferedReader contentURL;
		for(int i = 0; i < keywords.size(); i++)	{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			currWord = iterWord.next();
			Date dateNow;
			Date dateCrime;
			//TODO: Double check date is in correct format
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			try {
				URL u = new URL(this.stream + currWord);
				contentURL = new BufferedReader(new InputStreamReader(u.openStream()));
			String curr;
			
				while ((curr = contentURL.readLine()) != null)	{
					rawString += curr;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try{
				JsonParser parse = new JsonParser();
				//JsonReader read = new JsonReader();
				//read.setLenient(true);
				JsonElement elem = parse.parse(rawString);
				JsonArray searchResults = elem.getAsJsonArray();
				
				for(int j = 0; j < searchResults.size(); j++)	{
					dateNow = new Date();
					try {
						dateCrime = sdf.parse(searchResults.get(j).getAsJsonObject().get("date_reported").getAsString());
						if(dateNow.getTime() - dateCrime.getTime() < 36000)	{
							Call curr = new Call(city, searchResults.get(j).getAsJsonObject().get("latitude").getAsFloat(), searchResults.get(j).getAsJsonObject().get("longitude").getAsFloat(), searchResults.get(j).getAsJsonObject().get("hundred_block_location").getAsString());
						}
					}catch (ParseException e) {
							e.printStackTrace();
					}
				}
			} catch(Exception e)	{
				System.out.println("Malform");
				continue;
			}
		}
	}
		/*
		Soda2Consumer callData = Soda2Consumer.newConsumer("https://sandbox.demo.socrata.com",
				"testuser@gmail.com",
                "OpenData",
                "D8Atrg62F2j017ZTdkMpuZ9vY");
           */
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("crime looper is looping");
		crimeLooper();
		System.out.println("crime looper is looping");
	}

}
