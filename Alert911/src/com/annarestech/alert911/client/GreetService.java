package com.annarestech.alert911.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetService extends RemoteService {
	String greetServer(String name, String phone, String zip) throws IllegalArgumentException;
	String simulate() throws IllegalArgumentException;
}
