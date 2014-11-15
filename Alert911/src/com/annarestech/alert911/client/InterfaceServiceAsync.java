package com.annarestech.alert911.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface InterfaceServiceAsync {
	void interfaceServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
