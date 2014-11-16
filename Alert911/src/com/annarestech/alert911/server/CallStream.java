package com.annarestech.alert911.server;

import com.google.gwt.http.client.URL;
import com.socrata.api.Soda2Consumer;

public class CallStream {
	
	CallStream(URL input)	{
		Soda2Consumer callData = Soda2Consumer.newConsumer("https://sandbox.demo.socrata.com",
				"testuser@gmail.com",
                "OpenData",
                "D8Atrg62F2j017ZTdkMpuZ9vY");
	}

}
