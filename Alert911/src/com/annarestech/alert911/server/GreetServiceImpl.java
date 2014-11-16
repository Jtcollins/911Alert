package com.annarestech.alert911.server;

import com.annarestech.alert911.client.GreetService;
import com.annarestech.alert911.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.twilio.sdk.*;
import com.twilio.*;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetServiceImpl extends RemoteServiceServlet implements
		GreetService {
	private User user;
	private final static CityDepartment city = new CityDepartment("Seattle","", "", "https://data.seattle.gov/resource/7ais-f98f.json?"); //TODO: Fix this
	
	public String greetServer(String name, String phoneToServer, String zipToServer) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(name)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		} else if(!FieldVerifier.isValidPhone(phoneToServer))	{
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Phone number must be exactly 10 characters long");
		} else if(!FieldVerifier.isValidZip(zipToServer))	{
			// If the input is not valid, throw an IllegalArgumentException back to
						// the client.
						throw new IllegalArgumentException(
								"Zip code must be exactly 5 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		user = new User(name, phoneToServer, zipToServer);
		
		// Escape data from the client to avoid cross-site script vulnerabilities.
		name = escapeHtml(name);
		//TODO: prevent xxs with other data.
		userAgent = escapeHtml(userAgent);

		//return "Hello, " + input + "!<br><br>I am running " + serverInfo
		//		+ ".<br><br>It looks like you are using:<br>" + userAgent;

		if(city.addUser(user))	{
			return "User Added!";
		} else	{
			return "Error adding user, please refresh and try again.";
		}
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
