package com.annarestech.alert911.server;


import java.net.URL;
import com.socrata.api.Soda2Consumer;

public class CallStream {
	
	CallStream(URL stream)	{
		Soda2Consumer callData = Soda2Consumer.newConsumer("https://sandbox.demo.socrata.com",
				"testuser@gmail.com",
                "OpenData",
                "D8Atrg62F2j017ZTdkMpuZ9vY");
	}

}
