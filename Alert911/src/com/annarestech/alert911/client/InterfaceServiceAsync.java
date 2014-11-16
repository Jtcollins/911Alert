package com.annarestech.alert911.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>InterfaceService</code>.
 */
public interface InterfaceServiceAsync {
	void interfaceServer(String name, String phone, String zip, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
